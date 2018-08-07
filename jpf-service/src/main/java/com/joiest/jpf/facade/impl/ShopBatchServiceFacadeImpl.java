package com.joiest.jpf.facade.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopBatchCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBatchCouponMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBatchMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCompanyMapper;
import com.joiest.jpf.dto.ShopBatchRequest;
import com.joiest.jpf.dto.ShopBatchResponse;
import com.joiest.jpf.entity.CloudInterfaceStreamInfo;
import com.joiest.jpf.entity.ShopBatchCouponInfo;
import com.joiest.jpf.entity.ShopBatchInfo;
import com.joiest.jpf.facade.ShopBatchServiceFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShopBatchServiceFacadeImpl implements ShopBatchServiceFacade {

    @Autowired
    private PayShopBatchMapper payShopBatchMapper;

    @Autowired
    private PayShopBatchCustomMapper payShopBatchCustomMapper;

    @Autowired
    private PayShopCompanyMapper payShopCompanyMapper;

    @Autowired
    private PayShopBatchCouponMapper payShopBatchCouponMapper;

    @Override
    public ShopBatchResponse getBatches(ShopBatchRequest shopBatchRequest){
        ShopBatchResponse shopBatchResponse = new ShopBatchResponse();

        PayShopBatchExample e = new PayShopBatchExample();
        PayShopBatchExample.Criteria c = e.createCriteria();
        if ( shopBatchRequest.getId() != null ){
            c.andIdEqualTo(shopBatchRequest.getId());
        }
        if ( shopBatchRequest.getBatchNo() != null ){
            c.andBatchNoEqualTo(shopBatchRequest.getBatchNo());
        }
        if ( shopBatchRequest.getCompanyName() != null ){
            c.andCompanyNameEqualTo(shopBatchRequest.getCompanyName());
        }
        if ( shopBatchRequest.getReceiveName() != null ){
            c.andReceiveNameEqualTo(shopBatchRequest.getReceiveName());
        }
        if ( shopBatchRequest.getSalesName() != null ){
            c.andSalesNameEqualTo(shopBatchRequest.getSalesName());
        }
        e.setPageSize(shopBatchRequest.getRows());
        e.setPageNo(shopBatchRequest.getPage());
        e.setOrderByClause("id DESC");

        List<PayShopBatch> list = payShopBatchMapper.selectByExample(e);
        shopBatchResponse.setCount(payShopBatchMapper.countByExample(e));
        List<ShopBatchInfo> infos = new ArrayList<>();
        if ( !list.isEmpty() ){
            for (PayShopBatch payShopBatch:list){
                ShopBatchInfo shopBatchInfo = new ShopBatchInfo();
                BeanCopier beanCopier = BeanCopier.create(PayShopBatch.class, ShopBatchInfo.class, false);
                beanCopier.copy(payShopBatch, shopBatchInfo, null);

                infos.add(shopBatchInfo);
            }
        }

        shopBatchResponse.setList(infos);

        return shopBatchResponse;
    }

    /**
     * 新增批次及券
     */
    @Override
    @Transactional
    public JpfResponseDto addBatchCoupon(ShopBatchRequest shopBatchRequest, HttpServletResponse httpResponse){
        // 查询商户信息
        PayShopCompany payShopCompany = payShopCompanyMapper.selectByPrimaryKey(shopBatchRequest.getCompanyId());
        if ( payShopCompany.getStatus() != 1 ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("商户已停用，无法继续操作");
        }

        PayShopBatch payShopBatch = new PayShopBatch();
        payShopBatch.setCompanyId(shopBatchRequest.getCompanyId());
        payShopBatch.setCompanyName(shopBatchRequest.getCompanyName());
        payShopBatch.setBatchNo( createBatchNo() );
        // 计算总价
        String couponsJSON = shopBatchRequest.getCoupons();
        List<Map<String,String>> list = JsonUtils.toObject(couponsJSON,ArrayList.class);
        int totalMoney = 0;
        int totalCount = 0;
        for ( Map<String,String> single:list ){
            Integer singgleTotal = Integer.parseInt( single.get("money") ) * Integer.parseInt( single.get("amount") );
            totalMoney += singgleTotal;

            totalCount += Integer.parseInt(single.get("amount"));
        }
        payShopBatch.setMoney(new BigDecimal(totalMoney));
        payShopBatch.setScale(1.00);
        payShopBatch.setCount(totalCount);
        payShopBatch.setExpireMonth(shopBatchRequest.getExpireMonth());
        payShopBatch.setStatus((byte)1);
        payShopBatch.setActivetedNum(0);
        payShopBatch.setSalesName(payShopCompany.getSaleName());
        payShopBatch.setReceiveName(payShopBatch.getReceiveName());
        payShopBatch.setReceiveEmail(payShopBatch.getReceiveEmail());
        payShopBatch.setReceivePhone(payShopBatch.getReceivePhone());
        payShopBatch.setEmailStatus((byte)0);
        payShopBatch.setSmsStatus((byte)0);
        payShopBatch.setOperatorId(shopBatchRequest.getOperatorId());
        payShopBatch.setOperatorName(shopBatchRequest.getOperatorName());
        payShopBatch.setAddtime(new Date());

        payShopBatchCustomMapper.insertSelective(payShopBatch);
        String batchId = payShopBatch.getId();

        // 添加券
        List<PayShopBatchCoupon> payShopBatchCouponsList = new ArrayList<>();
        for ( Map<String,String> single:list ){
            for ( int i=0; i<Integer.parseInt(single.get("amount")); i++){
                PayShopBatchCoupon payShopBatchCoupon = new PayShopBatchCoupon();
                payShopBatchCoupon.setBatchId(batchId);
                payShopBatchCoupon.setCompanyId(shopBatchRequest.getCompanyId());
                payShopBatchCoupon.setCompanyName(shopBatchRequest.getCompanyName());
                payShopBatchCoupon.setCouponNo(createCouponNo());
                String activeCode = getRandomString(32);
                while ( isActiveCodeExist(activeCode) ){
                    activeCode = getRandomString(32);
                }
                payShopBatchCoupon.setActiveCode(getRandomString(32));
                payShopBatchCoupon.setMoney(Integer.parseInt(single.get("money")));
                // 根据兑换比例把面值兑换成豆
                Double money = new Double(single.get("money"));
                Double dou = money * payShopBatch.getScale();
                payShopBatchCoupon.setDou( dou.intValue() );
                payShopBatchCoupon.setIsActive((byte)0);
                payShopBatchCoupon.setExpireMonth(shopBatchRequest.getExpireMonth());
                payShopBatchCoupon.setAddtime(new Date());
                payShopBatchCouponsList.add(payShopBatchCoupon);

                payShopBatchCouponMapper.insert(payShopBatchCoupon);
            }
        }

        List<ShopBatchCouponInfo> infoList = new ArrayList<>();
        for ( PayShopBatchCoupon payCoupon:payShopBatchCouponsList ){
            ShopBatchCouponInfo info = new ShopBatchCouponInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopBatchCoupon.class, ShopBatchCouponInfo.class, false);
            beanCopier.copy(payCoupon, info, null);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            info.setAddtimeFormat(sdf.format(info.getAddtime()));
            infoList.add(info);
        }

        // 生成excel文件
        ExcelDealUtils excelUtils = new ExcelDealUtils();

        JSONArray titles = new JSONArray();
        titles.add("激活码");
        titles.add("面值");
        titles.add("欣豆");
        titles.add("有效期");
        titles.add("生成时间");

        JSONArray fields = new JSONArray();
        fields.add("activeCode");
        fields.add("money");
        fields.add("dou");
        fields.add("expireMonth");
        fields.add("addtimeFormat");

        String excelPath = ConfigUtil.getValue("EXCEL_PATH");
        JSONObject exExcelResponse = excelUtils.exportExcel(httpResponse,titles.toString(),fields.toString(),infoList,2,excelPath);
        JSONObject data = JSONObject.fromObject( exExcelResponse.get("data"));
        String uploadPath = data.get("localUrl").toString();    // excel文件的本地地址

        // 生成带密码的压缩包
        String password = getRandomString(6);
        String zipPath = new CompressUtil().zip(uploadPath, password);
        if ( zipPath == null ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10002");
            jpfResponseDto.setRetMsg("生成压缩包失败");

            return jpfResponseDto;
        }

        // oss上传压缩包文件
        Map<String,Object> ossRequestMap = new HashMap<>();
        ossRequestMap.put("path",zipPath);

        String url = ConfigUtil.getValue("MANAGE_WEB_URL")+"/oss/upload";
        String ossResponse = OkHttpUtils.postForm(url,ossRequestMap);
        ossResponse = StringUtils.strip(ossResponse,"\"");
        ossResponse = StringUtils.stripEnd(ossResponse,"\"");

        PayShopBatch payShopBatchUpdate = new PayShopBatch();
        payShopBatchUpdate.setId(batchId);
        payShopBatchUpdate.setOssUrl(ossResponse);
        payShopBatchUpdate.setZipPassword(password);
        payShopBatchMapper.updateByPrimaryKeySelective(payShopBatchUpdate);

        return new JpfResponseDto();
    }

    /**
     * 根据主键id获取批次
     */
    @Override
    public ShopBatchInfo getBatchById(String id){
        PayShopBatch payShopBatch = payShopBatchMapper.selectByPrimaryKey(id);
        ShopBatchInfo shopBatchInfo = new ShopBatchInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopBatch.class, ShopBatchInfo.class, false);
        beanCopier.copy(payShopBatch, shopBatchInfo, null);

        return shopBatchInfo;
    }

    /**
     * 根据主键id更新状态
     */
    @Override
    public int updateColumnById(ShopBatchInfo shopBatchInfo){
        PayShopBatch payShopBatch = new PayShopBatch();
        BeanCopier beanCopier = BeanCopier.create(ShopBatchInfo.class, PayShopBatch.class, false);
        beanCopier.copy(shopBatchInfo, payShopBatch, null);
        return payShopBatchMapper.updateByPrimaryKey(payShopBatch);
    }

    /**
     * 发送邮件和短信
     */
    @Override
    public JpfResponseDto sendEmailSms(String batchId) throws Exception{
        PayShopBatch payShopBatch = payShopBatchMapper.selectByPrimaryKey(batchId);
        PayShopCompany payShopCompany = payShopCompanyMapper.selectByPrimaryKey(payShopBatch.getCompanyId());

        // 发送邮件
        String subject = "欣豆市场批量欣券";
        String sendName = "欣享服务";
        String recipients = payShopBatch.getReceiveEmail();
        String recipientsName = payShopBatch.getReceiveName();
        String filepath = payShopBatch.getOssUrl();
        String filepathArr[] = StringUtils.split(filepath,'/');
        String Filename = filepathArr[filepathArr.length-1];    //携带文件类型.xlsx
        String html = "附件中的压缩包包含为您生成的批量激活码，该压缩包为加密压缩包，密码已发送至贵公司在我平台注册的企业联系人的手机号中，请注意查收";//可以使用标签拼装
        Boolean sendStatus =  SendMailUtil.sendMultipleEmail(subject,sendName,recipients,recipientsName,filepath,Filename,html);
        if ( !sendStatus ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10004");
            jpfResponseDto.setRetMsg("发送邮件失败");

            return jpfResponseDto;
        }

        // 发送短信
        String mobile = payShopCompany.getReceivePhone();
        String content = "已将批量欣券发送至您的邮箱，附件压缩包的密码是：" + payShopBatch.getZipPassword();
        String dateTime = new Date().toString();
        Map<String,Object> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("content",content);
        map.put("dateTime",dateTime);

        /*//排序转换
        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);

        String respos = ToolUtils.mapToUrl(treeMap);

        //调用配置文件ConfigUtil.getValue("API_SECRET")

        String selfSign = Md5Encrypt.md5(respos+ com.joiest.jpf.common.util.ConfigUtil.getValue("API_SECRET")).toUpperCase();

        map.put("sign",selfSign);

        String response = OkHttpUtils.postForm(com.joiest.jpf.common.util.ConfigUtil.getValue("CLOUD_API_URL")+"/toolcate/sendSmsApi",map);

        //json---转换代码---
        Map<String,String> responseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, String>>() {});
        String result=responseMap.get("code");

        // 增加==短信接口流水==
        CloudInterfaceStreamInfo cloudInterfaceStreamInfo = new CloudInterfaceStreamInfo();
        cloudInterfaceStreamInfo.setType((byte)0);
        cloudInterfaceStreamInfo.setRequestUrl(ConfigUtil.getValue("CLOUD_API_URL")+"/toolcate/sendSmsApi");
        cloudInterfaceStreamInfo.setRequestContent(respos);
        cloudInterfaceStreamInfo.setResponseContent(result);
        cloudInterfaceStreamInfo.setAddtime(new Date());
        cloudInterfaceStreamServiceFacade.insRecord(cloudInterfaceStreamInfo);

        // 更新批次状态
        ShopBatchInfo shopBatchInfoUpdate = new ShopBatchInfo();
        shopBatchInfoUpdate.setId(batchId);
        shopBatchInfoUpdate.setStatus((byte)2);     // 把状态改为已发券
        updateColumnById(shopBatchInfoUpdate);*/

        return new JpfResponseDto();
    }

    /**
     * 创建唯一的批次号
     */
    public String createBatchNo(){
        UUID uuid = UUID.randomUUID();
        String md5UUID = Md5Encrypt.md5(uuid.toString());

        return "BA" + md5UUID;
    }

    /**
     * 创建唯一的券号
     */
    public String createCouponNo(){
        return "CP" + ToolUtils.getRandomInt(100,999) + System.currentTimeMillis() + ToolUtils.getRandomInt(100,999);
    }

    /**
     * 创建唯一的激活码 a-z,A-Z,0-9
     * @param length    整个字符串长度
     */
    public static String getRandomString(int length) {
        String str = "abcdefghjklmnpqrtuvwxyABCDEFGHJKLMNPQRTUVWXY346789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            sb.append(str.charAt(random.nextInt(50)));
        }
        return sb.toString();
    }

    /**
     * 查询某激活码是否已存在
     * @return true=存在 false=不存在
     */
    public boolean isActiveCodeExist(String activeCode){
        PayShopBatchCouponExample e = new PayShopBatchCouponExample();
        PayShopBatchCouponExample.Criteria c = e.createCriteria();
        c.andActiveCodeEqualTo(activeCode);
        List<PayShopBatchCoupon> list = payShopBatchCouponMapper.selectByExample(e);
        if ( list.isEmpty() ){
            return false;
        }else{
            return true;
        }
    }
}
