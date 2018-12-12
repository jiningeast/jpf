package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopBatchCouponCustomMapper;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopBatchCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.*;
import com.joiest.jpf.dto.ShopBatchRequest;
import com.joiest.jpf.dto.ShopBatchResponse;
import com.joiest.jpf.entity.PayCouponInfo;
import com.joiest.jpf.entity.ShopBatchCouponInfo;
import com.joiest.jpf.entity.ShopBatchInfo;
import com.joiest.jpf.facade.ShopBatchServiceFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShopBatchServiceFacadeImpl implements ShopBatchServiceFacade {

    private static final Logger logger = LogManager.getLogger(ShopBatchServiceFacadeImpl.class);

    @Autowired
    private PayShopBatchMapper payShopBatchMapper;

    @Autowired
    private PayShopBatchCustomMapper payShopBatchCustomMapper;

    @Autowired
    private PayShopCompanyMapper payShopCompanyMapper;

    @Autowired
    private PayShopBatchCouponMapper payShopBatchCouponMapper;

    @Autowired
    private PayShopInterfaceStreamMapper payShopInterfaceStreamMapper;

    @Autowired
    private PayShopCompanyChargeMapper payShopCompanyChargeMapper;

    @Autowired
    private PayShopBatchCouponCustomMapper payShopBatchCouponCustomMapper;
    @Autowired
    private   PayShopCouponOrderMapper payShopCouponOrderMapper;
    @Autowired
    private   PayShopCouponOrderInfoMapper payShopCouponOrderInfoMapper;

    @Override
    public ShopBatchResponse getBatches(ShopBatchRequest shopBatchRequest){
        ShopBatchResponse shopBatchResponse = new ShopBatchResponse();

        PayShopBatchExample e = new PayShopBatchExample();
        PayShopBatchExample.Criteria c = e.createCriteria();
        if (StringUtils.isNotBlank(shopBatchRequest.getId())){
            c.andIdEqualTo(shopBatchRequest.getId());
        }
        if (StringUtils.isNotBlank(shopBatchRequest.getBatchNo())){
            c.andBatchNoEqualTo(shopBatchRequest.getBatchNo());
        }
        if (StringUtils.isNotBlank(shopBatchRequest.getCompanyName())){
            c.andCompanyNameEqualTo(shopBatchRequest.getCompanyName());
        }
        if (StringUtils.isNotBlank(shopBatchRequest.getReceiveName())){
            c.andReceiveNameEqualTo(shopBatchRequest.getReceiveName());
        }
        if (StringUtils.isNotBlank(shopBatchRequest.getSalesName())){
            c.andSalesNameEqualTo(shopBatchRequest.getSalesName());
        }
        if(shopBatchRequest.getStatus()!=null){
            c.andStatusEqualTo(shopBatchRequest.getStatus());
        }
        if(shopBatchRequest.getStatus()==null||(shopBatchRequest.getStatus()!=null&&shopBatchRequest.getStatus()!=4)){
            c.andStatusNotEqualTo((byte)4);
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
            logger.info("商户已停用，无法继续操作");
            return jpfResponseDto;
        }
        PayShopCompanyCharge payShopCompanyCharge = payShopCompanyChargeMapper.selectByPrimaryKey(shopBatchRequest.getContractId());
        if(payShopCompanyCharge==null){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10002");
            jpfResponseDto.setRetMsg("所选合同不存在");
            logger.info("所选合同不存在");
            return jpfResponseDto;
        }
       /* if("0.00".equals(payShopCompanyCharge.getBalance().toString())){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10003");
            jpfResponseDto.setRetMsg("所选合同已经没有余额");
            logger.info("所选合同已经没有余额");
            return jpfResponseDto;
        }*/
        // 添加批次
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
        // 判断该企业有没有这些券的总余额
        if ( payShopCompany.getMoney().compareTo(new BigDecimal(totalMoney)) < 0 ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("商户余额不足");
            return jpfResponseDto;
        }
     /*   if(payShopCompanyCharge.getBalance().compareTo(new BigDecimal(totalMoney+""))<0){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10004");
            jpfResponseDto.setRetMsg("所选合同余额不足");
            return jpfResponseDto;
        }*/
        payShopBatch.setMoney(new BigDecimal(totalMoney));
        payShopBatch.setScale(1.00);
        payShopBatch.setCount(totalCount);
        payShopBatch.setExpireMonth(shopBatchRequest.getExpireMonth());
        payShopBatch.setStatus((byte)0);
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

        //--------此为支持前台系统新增--------
        //订单上保存合同的id 如果走的直接走的后台，前台数据订单关系暂且未维护
        if(StringUtils.isNotBlank(shopBatchRequest.getOrderId())){
            payShopBatch.setOrderId(shopBatchRequest.getOrderId());
        }else{
            payShopBatch.setOrderId("0");
        }
        //根据订单号上的合同号 查询可用的合同
        payShopBatch.setCompanyChargeId(shopBatchRequest.getContractId());
        payShopBatch.setContractNo(shopBatchRequest.getContractNo());
        payShopBatch.setTransferRate(payShopCompanyCharge.getTransferRate());
        //--------此为支持前台系统新增-------- 还应增加保存前台数据的方法，后续在做
        payShopBatchCustomMapper.insertSelective(payShopBatch);
        String batchId = payShopBatch.getId();
        String batchNo = payShopBatch.getBatchNo();
        //此处更新合同的余额 此处删除代码，是因为重复扣款的原因
        /*payShopCompanyCharge.setBalance(ArithmeticUtils.sub(payShopCompanyCharge.getBalance().toString(),payShopBatch.getMoney().toString()));
        payShopCompanyChargeMapper.updateByPrimaryKeySelective(payShopCompanyCharge);*/

        // 添加券
        List<PayShopBatchCoupon> payShopBatchCouponsList = new ArrayList<>();
        for ( Map<String,String> single:list ){
            for ( int i=0; i<Integer.parseInt(single.get("amount")); i++){
                PayShopBatchCoupon payShopBatchCoupon = new PayShopBatchCoupon();
                payShopBatchCoupon.setBatchId(batchId);
                payShopBatchCoupon.setBatchNo(batchNo);
                payShopBatchCoupon.setCompanyId(shopBatchRequest.getCompanyId());
                payShopBatchCoupon.setCompanyName(shopBatchRequest.getCompanyName());
                payShopBatchCoupon.setCouponNo(createCouponNo());
                String activeCode = getRandomString(10);
                while (isActiveCodeExist(activeCode) ){
                    activeCode = getRandomString(10);
                }
                payShopBatchCoupon.setActiveCode(getRandomString(10));
                payShopBatchCoupon.setMoney(Integer.parseInt(single.get("money")));
                // 根据兑换比例把面值兑换成豆
                Double money = new Double(single.get("money"));
//                Double dou = money * payShopBatch.getScale();
                BigDecimal dou = new BigDecimal(money*payShopBatch.getScale());
                //payShopBatchCoupon.setDou( dou.intValue() );
                payShopBatchCoupon.setDou( dou );
                payShopBatchCoupon.setIsActive((byte)0);
                payShopBatchCoupon.setExpireMonth(shopBatchRequest.getExpireMonth());
                payShopBatchCoupon.setIsExpired((byte)0);
                payShopBatchCoupon.setAddtime(new Date());
                payShopBatchCoupon.setOrderId(StringUtils.isNotBlank(shopBatchRequest.getOrderId())?shopBatchRequest.getOrderId():"0");
                payShopBatchCoupon.setStatus((byte)0);
                payShopBatchCouponsList.add(payShopBatchCoupon);

                payShopBatchCouponMapper.insert(payShopBatchCoupon);
            }
        }

        // 扣企业的款
        Double newMoney = new Double(payShopCompany.getMoney().toString()) - Double.parseDouble(String.valueOf(totalMoney));
        BigDecimal newMoneyBD = new BigDecimal(newMoney).setScale(2,BigDecimal.ROUND_DOWN);
        payShopCompany.setMoney(newMoneyBD);
        String moneyCode = ToolUtils.CreateCode(newMoneyBD.toString(),payShopCompany.getId());
        payShopCompany.setMoneyCode(moneyCode);

        payShopCompanyMapper.updateByPrimaryKeySelective(payShopCompany);

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
        titles.add("有效期（月）");
        titles.add("生成时间");

        JSONArray fields = new JSONArray();
        fields.add("activeCode");
        fields.add("money");
        fields.add("dou");
        fields.add("expireMonth");
        fields.add("addtimeFormat");

        String excelPath = ConfigUtil.getValue("EXCEL_PATH");
        JSONObject exExcelResponse = null;
        try {
            logger.info("excelPath"+excelPath);
            exExcelResponse = excelUtils.exportExcel(httpResponse,titles.toString(),fields.toString(),infoList,2,excelPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        payShopBatchUpdate.setStatus((byte)1);
        payShopBatchMapper.updateByPrimaryKeySelective(payShopBatchUpdate);

        // oss接口流水
        PayShopInterfaceStream payShopInterfaceStream = new PayShopInterfaceStream();
        payShopInterfaceStream.setType((byte)0);
        payShopInterfaceStream.setRequestUrl(url);
        payShopInterfaceStream.setRequestContent(ToolUtils.mapToUrl(ossRequestMap));
        payShopInterfaceStream.setResponseContent(ossResponse);
        payShopInterfaceStream.setBatchId(batchId);
        payShopInterfaceStreamMapper.insertSelective(payShopInterfaceStream);

        // 批次及券生成完毕，更新批次状态为“1：生成完毕，待发券”
        //全部执行完毕后，更新前台订单状态
        if(StringUtils.isNotBlank(shopBatchRequest.getOrderId())){
            PayShopCouponOrder payShopCouponOrder =payShopCouponOrderMapper.selectByPrimaryKey(shopBatchRequest.getOrderId());
            payShopCouponOrder.setStatus((byte)1);
            payShopCouponOrderMapper.updateByPrimaryKeySelective(payShopCouponOrder);
        }

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

        return payShopBatchMapper.updateByPrimaryKeySelective(payShopBatch);
    }

    /**
     * 发送邮件
     */
    @Override
    public int sendEmail(String batchId) throws Exception{
        PayShopBatch payShopBatch = payShopBatchMapper.selectByPrimaryKey(batchId);
        PayShopCompany payShopCompany = payShopCompanyMapper.selectByPrimaryKey(payShopBatch.getCompanyId());

        // 发送邮件
        String subject = "欣豆市场批量欣券";
        String sendName = "欣享服务";
        String recipients = payShopCompany.getReceiveEmail();
        String recipientsName = payShopCompany.getReceiveName();
        String filepath = payShopBatch.getOssUrl();
        String filepathArr[] = StringUtils.split(filepath,'/');
        String filename = filepathArr[filepathArr.length-1];    // 文件名.xlsx
        filepath = ConfigUtil.getValue("EXCEL_PATH") + filename;
        // 邮件内容
//        String html = "附件中的压缩包包含为您生成的批量激活码，该压缩包为加密压缩包，密码已发送至贵公司在我平台注册的企业联系人的手机号中，请注意查收";//可以使用标签拼装
        String html = "<p>尊敬的客户，您好：</p>" +
                "<p style='text-index:2em;'>附件打包文件是您的欣券详单，批次号为【" + payShopBatch.getBatchNo() + "】。请您注意查收您在本公司预留的手机号码，并使用手机短信秘钥进行文件解密。</p>" +
                "<p style='text-index:2em; color:red;'>【请您知晓，您应妥善保管欣券详单及手机短信秘钥信息。因泄露信息导致的损失需由您自行承担】</p>" +
                "<p style='text-index:2em;'>此文件为系统自动发送，无需回复。</p>" +
                "<p style='text-index:2em;'>欣享爱生活</p>";
        Boolean sendStatus =  SendMailUtil.sendMultipleEmail(subject,sendName,recipients,recipientsName,filepath,filename,html);
        if ( !sendStatus ){
            // 邮件发送失败
            return -1;
        }

        // 更新email内容字段
        ShopBatchInfo shopBatchInfoUpdate = new ShopBatchInfo();
        shopBatchInfoUpdate.setId(batchId);
        shopBatchInfoUpdate.setReceiveEmail(recipients);
        shopBatchInfoUpdate.setReceiveName(payShopCompany.getReceiveName());
        shopBatchInfoUpdate.setEmailContent(html);
        shopBatchInfoUpdate.setEmailStatus((byte)1);
        shopBatchInfoUpdate.setEmailTime(new Date());
        shopBatchInfoUpdate.setSendType((byte)0);
        shopBatchInfoUpdate.setSendTime(new Date());

        return updateColumnById(shopBatchInfoUpdate);
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

    /**
     * 根据批次号查找批次
     */
    public PayShopBatch getBatchByBatchNo(String batchNo){
        PayShopBatchExample e = new PayShopBatchExample();
        PayShopBatchExample.Criteria c = e.createCriteria();
        c.andBatchNoEqualTo(batchNo);
        List<PayShopBatch> list = payShopBatchMapper.selectByExample(e);
        if ( list.isEmpty() || list == null ){
            return new PayShopBatch();
        }

        return list.get(0);
    }

    @Override
    public PayShopBatch getBatchByOrderId(String orderId) {
        PayShopBatchExample example = new PayShopBatchExample();
        PayShopBatchExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<PayShopBatch> list = payShopBatchMapper.selectByExample(example);
        if (list!=null&&list.size()!=0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 查询所有的订单
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<PayShopCouponOrder> getOrderList(String pageNo, String pageSize) {
        PayShopCouponOrderExample example = new PayShopCouponOrderExample();
        PayShopCouponOrderExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isEmpty(pageNo)){
            example.setPageNo(0);
            example.setPageSize(10);
        }else{
            example.setPageNo(Long.parseLong(pageNo));
            example.setPageSize(Long.parseLong(pageSize));
        }
        //查询已申请，未完成的数据
        criteria.andStatusEqualTo((byte)0);
        return payShopCouponOrderMapper.selectByExample(example);
    }

    /**
     * 查询订单的详细信息
     * @param orderId
     * @return
     */
    @Override
    public List<PayCouponInfo> getOrderInfo(String orderId) {
        List<PayCouponInfo> payCouponInfos = new ArrayList<>();
        PayShopCouponOrderInfoExample example = new PayShopCouponOrderInfoExample();
        PayShopCouponOrderInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<PayShopCouponOrderInfo> payShopCouponOrderInfos = payShopCouponOrderInfoMapper.selectByExample(example);
        for (PayShopCouponOrderInfo payShopCouponOrderInfo: payShopCouponOrderInfos) {
            PayCouponInfo payCouponInfo =new PayCouponInfo();
            payCouponInfo.setMoney(payShopCouponOrderInfo.getPrice().intValue()+"");
            payCouponInfo.setAmount(payShopCouponOrderInfo.getNumber()+"");
            payCouponInfo.setCalculate(payShopCouponOrderInfo.getTotalPrice()+"");
            payCouponInfos.add(payCouponInfo);
        }
        return payCouponInfos;
    }

    @Override
    public void update(PayShopBatch payShopBatch) {
        payShopBatchMapper.updateByPrimaryKeySelective(payShopBatch);
    }
}
