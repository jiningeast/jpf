package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.po.PayChargeCompany;
import com.joiest.jpf.common.po.PayChargeCompanyExample;
import com.joiest.jpf.common.po.PayChargeCompanyMoneyStream;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.ConfigUtil;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayChargeCompanyCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeCompanyChargeMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeCompanyMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeCompanyMoneyStreamMapper;
import com.joiest.jpf.dto.GetChargeCompanyRequest;
import com.joiest.jpf.dto.GetChargeCompanyResponse;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.entity.ChargeOrderInfo;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

public class ChargeCompanyServiceFacadeImpl implements ChargeCompanyServiceFacade {

    @Autowired
    private PayChargeCompanyMapper payChargeCompanyMapper;

    @Autowired
    private PayChargeCompanyCustomMapper payChargeCompanyCustomMapper;

    @Autowired
    private PayChargeCompanyMoneyStreamMapper payChargeCompanyMoneyStreamMapper;

    @Autowired
    private PayChargeCompanyChargeMapper payChargeCompanyChargeMapper;

    /**
     * 获取商户列表
     */
    @Override
    public GetChargeCompanyResponse getRecords(GetChargeCompanyRequest request){
        GetChargeCompanyResponse response = new GetChargeCompanyResponse();

        PayChargeCompanyExample e = new PayChargeCompanyExample();
        PayChargeCompanyExample.Criteria c = e.createCriteria();
        c.andIsDelEqualTo((byte)0);
        if (StringUtils.isNotBlank(""+request.getPage()) && request.getPage() > 0 ){
            e.setPageNo(request.getPage());
        }else{
            e.setPageNo(1);
        }
        if (StringUtils.isNotBlank(""+request.getRows()) && request.getRows() > 0 ){
            e.setPageSize(request.getRows());
        }else{
            e.setPageSize(10);
        }
        if ( request.getMerchNo() != null && StringUtils.isNotBlank(request.getMerchNo()) ){
            c.andMerchNoEqualTo(request.getMerchNo());
        }
        if ( request.getCompanyName() != null && StringUtils.isNotBlank(request.getCompanyName()) ){
            c.andCompanyNameLike("%"+request.getCompanyName()+"%");
        }
        if ( request.getIsFreeze() != null && request.getIsFreeze() == 1 ){
            c.andIsFreezeEqualTo((byte)0);
        }else if ( request.getIsFreeze() != null && request.getIsFreeze() == 2 ){
            c.andIsFreezeEqualTo((byte)1);
        }
        e.setOrderByClause("id DESC");
        List<PayChargeCompany> list =  payChargeCompanyMapper.selectByExample(e);
        response.setCount(payChargeCompanyMapper.countByExample(e));
        List<ChargeCompanyInfo> infos = new ArrayList<>();
        for (PayChargeCompany one:list){
            ChargeCompanyInfo info = new ChargeCompanyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayChargeCompany.class,ChargeCompanyInfo.class,false);
            beanCopier.copy(one,info,null);
            infos.add(info);
        }
        response.setList(infos);
        return response;
    }

    /**
     * 根据商户号获取商户
     * */
    @Override
    public ChargeCompanyInfo getOne(ChargeCompanyInfo companyInfo){
        PayChargeCompanyExample example = new PayChargeCompanyExample();
        PayChargeCompanyExample.Criteria c = example.createCriteria();
        if(companyInfo.getMerchNo() != null ){
            c.andMerchNoEqualTo(companyInfo.getMerchNo());
        }
        List<PayChargeCompany> list = payChargeCompanyMapper.selectByExample(example);
        if( list == null || list.size() <=0  ){
            return null;
        }
        ChargeCompanyInfo info = new ChargeCompanyInfo();
        BeanCopier beanCopier = BeanCopier.create(PayChargeCompany.class,ChargeCompanyInfo.class,false);
        beanCopier.copy(list.get(0),info,null);
        return info;
    }
    /**
     * 根据商户号获取商户
     * */
    @Override
    public ChargeCompanyInfo getRecordByMerchNo(String merchNO){
        PayChargeCompanyExample example = new PayChargeCompanyExample();
        PayChargeCompanyExample.Criteria c = example.createCriteria();
        if(StringUtils.isNotBlank(merchNO)){
            c.andMerchNoEqualTo(merchNO);
        }else{
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "商户号不能为空");
        }

        List<PayChargeCompany> list = payChargeCompanyMapper.selectByExample(example);
        if( list == null || list.size() <=0  ){
            return null;
        }
        ChargeCompanyInfo info = new ChargeCompanyInfo();
        BeanCopier beanCopier = BeanCopier.create(PayChargeCompany.class,ChargeCompanyInfo.class,false);
        beanCopier.copy(list.get(0),info,null);
        return info;
    }
    /**
     * 根据主键id获取商户
     */
    @Override
    public ChargeCompanyInfo getRecordByPrimaryKey(String id){
        PayChargeCompany payChargeCompany = payChargeCompanyMapper.selectByPrimaryKey(id);
        ChargeCompanyInfo chargeCompanyInfo = new ChargeCompanyInfo();
        if ( payChargeCompany != null ){
            BeanCopier beanCopier = BeanCopier.create(PayChargeCompany.class,ChargeCompanyInfo.class,false);
            beanCopier.copy(payChargeCompany,chargeCompanyInfo,null);
        }

        return chargeCompanyInfo;
    }

    /**
     * 添加商户
     */
    @Override
    public int addRecord(ChargeCompanyInfo chargeCompanyInfo){
        PayChargeCompany payChargeCompany = new PayChargeCompany();
        if ( chargeCompanyInfo != null ){
            BeanCopier beanCopier = BeanCopier.create(ChargeCompanyInfo.class,PayChargeCompany.class,false);
            beanCopier.copy(chargeCompanyInfo,payChargeCompany,null);
        }
        String merchNo = "MC"+System.currentTimeMillis()+ToolUtils.getRandomInt(100000,999999);     // 生成商户号
        String privateKey = ToolUtils.createPrivateKey(16);     // 生成秘钥
        payChargeCompany.setMerchNo(merchNo);
        payChargeCompany.setPrivateKey(privateKey);
        payChargeCompany.setMoney(new BigDecimal(0));
        payChargeCompany.setIsDel((byte)0);
        payChargeCompany.setAddtime(new Date());
        payChargeCompanyCustomMapper.insertSelective(payChargeCompany);
        int ret = 0;
        if( StringUtils.isNotBlank(payChargeCompany.getId()) ){
            //初始化金额校验码
            String keyStr = ConfigUtil.getValue("MERCH_VALIDE_CODE");
            BigDecimal money = new BigDecimal("0.00");
            String newCode = ToolUtils.CreateCode(money.toString(),payChargeCompany.getId(),keyStr);
            PayChargeCompany chargeCompany = new PayChargeCompany();
            chargeCompany.setMoneyCode(newCode);
            chargeCompany.setId(payChargeCompany.getId());
            int upCou = payChargeCompanyMapper.updateByPrimaryKeySelective(chargeCompany);
            if( upCou != 1 ){
                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "操作失败");
            }else{
                ret = 1;
            }
        }else{
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "操作异常");
        }

        return ret;
    }

    /**
     * 更新商户
     */
    @Override
    public int updateColumnByPrimaryKey(ChargeCompanyInfo chargeCompanyInfo){
        PayChargeCompany payChargeCompany = new PayChargeCompany();
        if ( chargeCompanyInfo != null ){
            BeanCopier beanCopier = BeanCopier.create(ChargeCompanyInfo.class,PayChargeCompany.class,false);
            beanCopier.copy(chargeCompanyInfo,payChargeCompany,null);
        }

        return payChargeCompanyMapper.updateByPrimaryKeySelective(payChargeCompany);
    }
    /**
     * 公司单条记录
     */
    @Override
    public JpfResponseDto updateCompanyRecord(PayChargeCompany payChargeCompany)
    {
        if ( StringUtils.isBlank(payChargeCompany.getId()))
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        int count = payChargeCompanyMapper.updateByPrimaryKeySelective(payChargeCompany);
        if(count != 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "更新失败");
        }

        return new JpfResponseDto();
    }

    /**
     * 修改用户密码
     */
    @Override
    public JpfResponseDto updatePassword(String merchNo,String oldPass,String newPass)
    {
        if(StringUtils.isNotBlank(merchNo)&& StringUtils.isNotBlank(oldPass)&& StringUtils.isNotBlank(newPass)){
           //修改数据
           PayChargeCompanyExample example=new PayChargeCompanyExample();
           PayChargeCompanyExample.Criteria c=example.createCriteria();
           c.andMerchNoEqualTo(merchNo);
           List<PayChargeCompany> listCom=payChargeCompanyMapper.selectByExample(example);
           if(listCom==null || listCom.isEmpty()){
               throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "此信息不存在");
           }
           PayChargeCompany company=new PayChargeCompany();
           String newpass= Md5Encrypt.md5(newPass);
           company.setPassword(newpass);

           int count=payChargeCompanyMapper.updateByExampleSelective(company,example);

            if(count != 1 ){
                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "更新失败");
            }

        }else{
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "参数错误");
        }

        return new JpfResponseDto();
    }
    /**
     * 充值失败返还商户资金
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public JSONObject returnComfunds(ChargeOrderInfo orderInfo)  throws Exception{

        JSONObject retParam = new JSONObject();
        retParam.put("code","10008");
        retParam.put("info","返还商户金额失败");

        ChargeCompanyInfo companyInfo = new ChargeCompanyInfo();
        companyInfo.setMerchNo(orderInfo.getMerchNo());
        ChargeCompanyInfo chargeCompanyInfo = getOne(companyInfo);
        if(chargeCompanyInfo == null){
            retParam.put("info","订单信息未匹配到商户信息");
            return retParam;
        }
        Boolean newCode = ToolUtils.ValidateCode(chargeCompanyInfo.getMoneyCode(),chargeCompanyInfo.getId(),chargeCompanyInfo.getMoney().toString(), ConfigUtil.getValue("MERCH_VALIDE_CODE"));
        if(newCode.equals(false)){
            retParam.put("info","商户金额校验错误");
            return retParam;
        }
        //退款
        addCompanyMoney(chargeCompanyInfo,orderInfo);
        //增加退款流水
        PayChargeCompany payChargeCompany = payChargeCompanyMapper.selectByPrimaryKey(chargeCompanyInfo.getId());
        addStreamFail(orderInfo,payChargeCompany);
        return retParam;
    }


    /**
     * 查询商户列表
     * @return
     */
    @Override
    public List<PayChargeCompany> getCompanyList() {
        PayChargeCompanyExample example = new PayChargeCompanyExample();
        example.setOrderByClause("id asc");

        //只查询张猛的公司 id:17
        PayChargeCompanyExample.Criteria criteria = example.createCriteria();
//        criteria.andIdEqualTo("17");
        List<String> list = new ArrayList<>();
        list.add("17");
        list.add("18");
        criteria.andIdIn(list);

        return payChargeCompanyMapper.selectByExample(example);
    }

    /**
     * 校正商户余额
     * @param company
     */
    @Override
    public void reviseCompanyCharge(PayChargeCompany company) {
        BigDecimal companyMoney = BigDecimal.valueOf(0);

        PayChargeCompanyChargeExample example = new PayChargeCompanyChargeExample();
        example.setOrderByClause("id asc");
        PayChargeCompanyChargeExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(company.getId());

        //查询某个商家所有充值记录
        List<PayChargeCompanyCharge> payChargeCompanyChargeList = payChargeCompanyChargeMapper.selectByExample(example);

        //根据两次充值记录分段校正余额  先加上充值的钱
        for (int i= 0;i<payChargeCompanyChargeList.size();i++){
            Date beginDate = null;
            Date endDate = null;

            PayChargeCompanyMoneyStreamExample streamExample = new PayChargeCompanyMoneyStreamExample();
            streamExample.setOrderByClause("id asc");
            PayChargeCompanyMoneyStreamExample.Criteria streamCriteria = streamExample.createCriteria();
            streamCriteria.andCompanyIdEqualTo(company.getId());

            beginDate = payChargeCompanyChargeList.get(i).getAddtime();

            if (i < payChargeCompanyChargeList.size() - 1){
                endDate = payChargeCompanyChargeList.get(i+1).getAddtime();
                streamCriteria.andAddtimeBetween(beginDate, endDate);
            }else if(i == payChargeCompanyChargeList.size() - 1){
                endDate = new Date();
                streamCriteria.andAddtimeBetween(beginDate, endDate);
            }

            //先把本次充值的钱加进去
//            companyMoney = companyMoney.add(payChargeCompanyChargeList.get(i).getMoney());


            //查询两次充值时间段内的所有流水(更新流水的钱+更新用户的钱)
            List<PayChargeCompanyMoneyStream> payChargeCompanyMoneyStreamList = payChargeCompanyMoneyStreamMapper.selectByExample(streamExample);

            for (PayChargeCompanyMoneyStream stream : payChargeCompanyMoneyStreamList){
                if (stream.getStatus() == 3){
                    //如果状态是退款 加钱
                    companyMoney = companyMoney.add(stream.getTotalMoney());
                }else if (stream.getStatus() == 2){
                    //如果状态是下单 扣钱
                    companyMoney = companyMoney.subtract(stream.getTotalMoney());
                }else if(stream.getStatus() == 1){
                    //充值 加钱
                    companyMoney = companyMoney.add(payChargeCompanyChargeList.get(i).getMoney());
                }

                stream.setNewMoney(companyMoney);
                payChargeCompanyMoneyStreamMapper.updateByPrimaryKey(stream);
            }


        }

    }


    @Override
    public void addCompanyMoney(ChargeCompanyInfo companyInfo, ChargeOrderInfo orderInfo) throws Exception {
        Map<String,Object> map =new HashMap<>();
        map.put("companyId",companyInfo.getId());
        map.put("addMoney",orderInfo.getTotalMoney());
        map.put("companyKey",ConfigUtil.getValue("MERCH_VALIDE_CODE"));
        int count = payChargeCompanyMapper.updateCompanyMoneyAdd(map);
        if(count!=1){
            throw new Exception("退款失败了"+orderInfo.getOrderNo());
        }
    }

    public void addStreamFail(ChargeOrderInfo chargeOrderInfo,PayChargeCompany companyInfo) {
        PayChargeCompanyMoneyStream streamData = new PayChargeCompanyMoneyStream();
        streamData.setStreamNo("MS"+ToolUtils.createOrderid());//流水号
        streamData.setCompanyId(companyInfo.getId());//商户id
        streamData.setCompanyName(chargeOrderInfo.getCompanyName());//商户名称
        streamData.setMerchNo(chargeOrderInfo.getMerchNo());//商户号
        streamData.setOrderId(chargeOrderInfo.getId());//订单id 可能是消费订单、充值订单、退款订单
        streamData.setOrderNo(chargeOrderInfo.getOrderNo()); // 订单号可能是消费订单、充值订单、退款订单
        streamData.setProductId(chargeOrderInfo.getProductId());//产品Id
        streamData.setProductName(chargeOrderInfo.getProductName());//产品名称

        streamData.setProductValue(chargeOrderInfo.getProductValue()); //产品面值
        streamData.setProductBidPrice(chargeOrderInfo.getProductBidPrice());//产品成本价
        streamData.setProductSalePrice(chargeOrderInfo.getProductPrice());//产品标准售价 默认null
        streamData.setProductInterfacePrice(chargeOrderInfo.getProductBidPrice());//产品接口价同成本价
        streamData.setProductAmount(chargeOrderInfo.getProductAmount());//产品数量
        streamData.setTotalMoney(chargeOrderInfo.getTotalMoney());//总价

        streamData.setInterfaceType(chargeOrderInfo.getInterfaceType());//接口类型 0=欧非 1=威能 默认null
        streamData.setInterfaceOrderNo(chargeOrderInfo.getInterfaceOrderNo());//接口订单号 默认null
        streamData.setStatus((byte)3);//流水类型 1=充值 2=下单 3=退款
        streamData.setStreamType((byte)0);//流水类型 0=收入 1=支出
        streamData.setNewMoney(companyInfo.getMoney());//变动后的余额
        streamData.setMemo("");//流水备注
        streamData.setIsDel((byte)0);//删除标记 0=未删除 1=已删除
        streamData.setAddtime(new Date());
        payChargeCompanyMoneyStreamMapper.insertSelective(streamData);
    }
}
