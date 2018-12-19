package com.joiest.jpf.facade.impl;

import com.alibaba.fastjson.JSONArray;
import com.joiest.jpf.common.po.PayChargeCompany;
import com.joiest.jpf.common.po.PayChargeCompanyMoneyStream;
import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.common.po.PayChargeOrderExample;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dao.repository.mapper.custom.PayChargeOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeCompanyMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeCompanyMoneyStreamMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeOrderMapper;
import com.joiest.jpf.dto.ChargeOrderInterfaceRequest;
import com.joiest.jpf.dto.GetChargeOrderRequest;
import com.joiest.jpf.dto.GetChargeOrderResponse;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.ChargeCompanyMoneyStreamServiceFacade;
import com.joiest.jpf.facade.ChargeOrderServiceFacade;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

public class ChargeOrderServiceFacadeImpl implements ChargeOrderServiceFacade {
    private static final Logger logger = LogManager.getLogger(ChargeOrderServiceFacadeImpl.class);

    @Autowired
    private PayChargeOrderMapper payChargeOrderMapper;

    @Autowired
    private PayChargeOrderCustomMapper payChargeOrderCustomMapper;

    @Autowired
    private PayChargeCompanyMoneyStreamMapper payChargeCompanyMoneyStreamMapper;

    @Autowired
    private PayChargeCompanyMapper payChargeCompanyMapper;
    /**
     * 获取订单信息
     * 查询单条信息
     * */
    @Override
    public ChargeOrderInfo getOne(PayChargeOrder record){

        PayChargeOrderExample example = new PayChargeOrderExample();
        PayChargeOrderExample.Criteria c = example.createCriteria();
        if( StringUtils.isNotBlank(record.getId()) ){
            c.andIdEqualTo(record.getId());
        }

        if( StringUtils.isNotBlank(record.getForeignOrderNo()) ){
            c.andForeignOrderNoEqualTo(record.getForeignOrderNo());
        }
        if( StringUtils.isNotBlank(record.getOrderNo()) ){
            c.andOrderNoEqualTo(record.getOrderNo());
        }
        if( StringUtils.isNotBlank(record.getMerchNo()) ){
            c.andMerchNoEqualTo(record.getMerchNo());
        }

        List<PayChargeOrder> list = payChargeOrderMapper.selectByExample(example);
        if( list==null || list.isEmpty() ){
            return null;
        }

        ChargeOrderInfo info = new ChargeOrderInfo();
        BeanCopier beanCopier = BeanCopier.create(PayChargeOrder.class,ChargeOrderInfo.class,false);
        beanCopier.copy(list.get(0),info,null);

        return info;
    }

    /**
     * 生成订单
     * */
    @Override
    public int placeOrder(ChargeOrderInfo placeOrderInfo){

        PayChargeOrder payChargeOrder = new PayChargeOrder();
        BeanCopier beanCopier = BeanCopier.create(ChargeOrderInfo.class,PayChargeOrder.class,false);
        beanCopier.copy(placeOrderInfo,payChargeOrder,null);

        int count = payChargeOrderCustomMapper.insertSelective(payChargeOrder);
        return Integer.valueOf(payChargeOrder.getId());
    }
    /*
      * 查询订单
     */
    @Override
    public GetChargeOrderResponse getRecords(GetChargeOrderRequest request){

        GetChargeOrderResponse response = new GetChargeOrderResponse();

        PayChargeOrderExample e = new PayChargeOrderExample();
        PayChargeOrderExample.Criteria c = e.createCriteria();
        if ( request.getOrderNo() != null && StringUtils.isNotBlank(request.getOrderNo()) ){
            c.andOrderNoEqualTo(request.getOrderNo());
        }
        if ( request.getForeignOrderNo() != null && StringUtils.isNotBlank(request.getForeignOrderNo()) ){
            c.andForeignOrderNoEqualTo(request.getForeignOrderNo());
        }
        if ( request.getCompanyId() != null && StringUtils.isNotBlank(request.getCompanyId()) ){
            c.andCompanyIdEqualTo(request.getCompanyId());
        }
        if ( request.getCompanyName() != null && StringUtils.isNotBlank(request.getCompanyName()) ){
            c.andCompanyNameEqualTo(request.getCompanyName());
        }
        if ( request.getMerchNo() != null && StringUtils.isNotBlank(request.getMerchNo()) ){
            c.andMerchNoEqualTo(request.getMerchNo());
        }
        if ( request.getChargePhone() != null && StringUtils.isNotBlank(request.getChargePhone()) ){
            c.andChargePhoneEqualTo(request.getChargePhone());
        }
        if ( request.getProductId() != null && StringUtils.isNotBlank(request.getProductId()) ){
            c.andProductIdEqualTo(request.getProductId());
        }
        if ( request.getProductName() != null && StringUtils.isNotBlank(request.getProductName()) ){
            c.andProductNameEqualTo(request.getProductName());
        }
        if ( request.getInterfaceType() != null && StringUtils.isNotBlank(""+request.getInterfaceType()) ){
            c.andInterfaceTypeEqualTo(request.getInterfaceType());
        }
        if ( request.getStatus() != null && StringUtils.isNotBlank(""+request.getStatus()) ){
            c.andStatusEqualTo(request.getStatus());
        }
        if ( request.getInterfaceOrderNo() != null && StringUtils.isNotBlank(""+request.getInterfaceOrderNo())){
            c.andInterfaceOrderNoEqualTo(request.getInterfaceOrderNo());
        }
        if (StringUtils.isNotBlank(request.getAddtimeStart()))
        {
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()))
        {
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }
        e.setPageNo(request.getPage());
        e.setPageSize(request.getRows());
        e.setOrderByClause("id DESC");
        c.andIsDelEqualTo((byte)0);
        //c.andInterfaceOrderNoIsNotNull();
        List<ChargeOrderInfo> infos = new ArrayList<>();
        List<PayChargeOrder> list = payChargeOrderMapper.selectByExample(e);
        int count = payChargeOrderMapper.countByExample(e);
        if ( list != null && !list.isEmpty() ){
            for ( PayChargeOrder one:list ){
                ChargeOrderInfo info = new ChargeOrderInfo();

                BeanCopier beanCopier = BeanCopier.create(PayChargeOrder.class,ChargeOrderInfo.class,false);
                beanCopier.copy(one,info,null);
                infos.add(info);
            }
        }
        response.setList(infos);
        response.setCount(count);

        return response;
    }

    /*
     * 接口查询订单
     */
    @Override
    public GetChargeOrderResponse getRecordsInterface(ChargeOrderInterfaceRequest request){

        GetChargeOrderResponse response = new GetChargeOrderResponse();

        PayChargeOrderExample e = new PayChargeOrderExample();

        if ( request.getPageSize() !=null && StringUtils.isNotBlank(request.getPageSize()) )
        {
            e.setPageSize(Long.parseLong(request.getPageSize()));
        }

        if ( request.getPage() !=null && StringUtils.isNotBlank(request.getPage()) )
        {
            e.setPageNo(Long.parseLong(request.getPage()));
        }

        PayChargeOrderExample.Criteria c = e.createCriteria();
        if ( request.getOrderNo() != null && StringUtils.isNotBlank(request.getOrderNo()) ){
            c.andOrderNoEqualTo(request.getOrderNo());
        }
        if ( request.getMerchNo() != null && StringUtils.isNotBlank(request.getMerchNo()) ){
            c.andMerchNoEqualTo(request.getMerchNo());
        }
        if ( request.getStatus() != null && StringUtils.isNotBlank(request.getStatus()) ){
            c.andStatusEqualTo(Byte.valueOf(request.getStatus()));
        }
        if(request.getConsumerOrderNo()!=null && StringUtils.isNotBlank(request.getConsumerOrderNo())){
            c.andConsumerOrderNoEqualTo(request.getConsumerOrderNo());
        }
        List<Integer>tel=new ArrayList<>();
        tel.add(0);
        tel.add(1);
        List<Integer> card =new ArrayList<>();
        card.add(3);
        card.add(4);
        if ( request.getProductType() != null && StringUtils.isNotBlank(request.getProductType()) && request.getProductType().equals("tel")){
            c.andProductTypeIn(tel);
        }
        if ( request.getProductType() != null && StringUtils.isNotBlank(request.getProductType()) && request.getProductType().equals("card")){
            c.andProductTypeIn(card);
        }
           // 添加时间搜索
        if (StringUtils.isNotBlank(request.getAddtimeStart()))
        {
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()))
        {
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }

        e.setOrderByClause("id DESC");
        c.andIsDelEqualTo((byte)0);
        List<ChargeOrderInfo> infos = new ArrayList<>();
        List<PayChargeOrder> list = payChargeOrderCustomMapper.selectByExample(e);
        int count = payChargeOrderCustomMapper.countByExample(e);
        if ( list != null && !list.isEmpty() ){
            for ( PayChargeOrder one:list ){
                ChargeOrderInfo info = new ChargeOrderInfo();

                BeanCopier beanCopier = BeanCopier.create(PayChargeOrder.class,ChargeOrderInfo.class,false);
                beanCopier.copy(one,info,null);
                infos.add(info);
            }
        }
        //获取统计数据

        response.setList(infos);
        response.setCount(count);
        return response;
    }

    // 获取统计数据查询
    @Override

    public Map<String, Object> getStatistics(ChargeOrderInterfaceRequest  request){


        PayChargeOrderExample exampleCount = new PayChargeOrderExample();
        PayChargeOrderExample.Criteria c = exampleCount.createCriteria();

        if ( request.getOrderNo() != null && StringUtils.isNotBlank(request.getOrderNo()) ){
            c.andOrderNoEqualTo(request.getOrderNo());
        }
        if ( request.getMerchNo() != null && StringUtils.isNotBlank(request.getMerchNo()) ){
            c.andMerchNoEqualTo(request.getMerchNo());
        }
        if ( request.getStatus() != null && StringUtils.isNotBlank(""+request.getStatus()) ){
            c.andStatusEqualTo(Byte.valueOf(request.getStatus()));
        }
        // 添加时间搜索
        if (StringUtils.isNotBlank(request.getAddtimeStart()))
        {
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()))
        {
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }
        // 汇总统计
        Map<String, Object> map = new HashMap<>();
        //查询统计数据


        /*map.put("allOrdersCount", (long)payOrderMapper.countByExample(e));
        BigDecimal allOrdersMoney = payOrderCustomMapper.selectOrderpriceSum(e);
        allOrdersMoney = allOrdersMoney == null ? new BigDecimal(0) : allOrdersMoney;
        map.put("allOrdersMoney", allOrdersMoney);
        c.andSinglestatusEqualTo((byte)7);
        BigDecimal allRefundMoney = payOrderCustomMapper.selectOrderpriceSum(e);
        allRefundMoney = allRefundMoney == null ? new BigDecimal(0) : allRefundMoney;
        map.put("allRefundMoney", allRefundMoney);*/

        return map;
    }

    /**
     * 更新订单新
     * @param upOrderInfo 要更新的订单信息
     * */
    @Override
    public int upOrderInfo(ChargeOrderInfo upOrderInfo){

        PayChargeOrder payChargeOrder = new PayChargeOrder();

        BeanCopier beanCopier = BeanCopier.create(ChargeOrderInfo.class,PayChargeOrder.class,false);
        beanCopier.copy(upOrderInfo,payChargeOrder,null);

        return payChargeOrderMapper.updateByPrimaryKeySelective(payChargeOrder);
    }

    /**
     * 异常订单处理
     */
    @Override
    public List<ChargeOrderInfo>  getAbnormalOrders(ChargeOrderInfo request)
    {

        PayChargeOrderExample example = new PayChargeOrderExample();
        example.setOrderByClause("addtime ASC");
        //example.setPageNo(1);
        //example.setPageSize(1);

        PayChargeOrderExample.Criteria c =example.createCriteria();
        c.andStatusEqualTo((byte)request.getStatus());
        //c.andInterfaceTypeEqualTo(request.getInterfaceType());
        c.andAddtimeLessThanOrEqualTo(request.getAddtime());
        //c.andInterfaceOrderNoIsNull();
       if( !StringUtils.isBlank(request.getCompanyId())){
           c.andCompanyIdEqualTo(request.getCompanyId());
       }

        List<PayChargeOrder> list = payChargeOrderMapper.selectByExample(example);
        if( list.size() <=0 || list == null){
            return null;
        }
        List<ChargeOrderInfo> infoList = new ArrayList<>();

        for (PayChargeOrder one : list)
        {
            ChargeOrderInfo info = new ChargeOrderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayChargeOrder.class, ChargeOrderInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }

        return infoList;
    }


    /**
     * 所有指定商户异常订单处理
     */
    @Override
    public List<PayChargeOrder>  getAllAbnormalOrders()
    {
        PayChargeOrderExample example = new PayChargeOrderExample();
        example.setOrderByClause("addtime ASC");

        PayChargeOrderExample.Criteria c =example.createCriteria();

        List<PayChargeOrder> list = payChargeOrderMapper.selectByExample(example);
        if( list.size() <=0 || list == null){
            return null;
        }
        return list;
    }


    @Override
    public List<PayChargeOrder> getExcelRecords(GetChargeOrderRequest request){
        PayChargeOrderExample e = new PayChargeOrderExample();
        PayChargeOrderExample.Criteria c = e.createCriteria();
        matchCriteria(request,c);
        e.setOrderByClause("id DESC");
        c.andIsDelEqualTo((byte)0);
        return payChargeOrderMapper.selectByExcelExample(e);
    }

    /**
     * 充值订单查询条件匹配
     * @param request
     * @param c
     */
    private void matchCriteria(GetChargeOrderRequest request,PayChargeOrderExample.Criteria c){
        if(request.getOrderNo() != null && StringUtils.isNotBlank(request.getOrderNo()) ){
            c.andOrderNoEqualTo(request.getOrderNo());
        }
        if(request.getForeignOrderNo() != null && StringUtils.isNotBlank(request.getForeignOrderNo()) ){
            c.andForeignOrderNoEqualTo(request.getForeignOrderNo());
        }
        if(request.getCompanyId() != null && StringUtils.isNotBlank(request.getCompanyId()) ){
            c.andCompanyIdEqualTo(request.getCompanyId());
        }
        if(request.getCompanyName() != null && StringUtils.isNotBlank(request.getCompanyName()) ){
            c.andCompanyNameEqualTo(request.getCompanyName());
        }
        if(request.getMerchNo() != null && StringUtils.isNotBlank(request.getMerchNo()) ){
            c.andMerchNoEqualTo(request.getMerchNo());
        }
        if(request.getChargePhone() != null && StringUtils.isNotBlank(request.getChargePhone()) ){
            c.andChargePhoneEqualTo(request.getChargePhone());
        }
        if(request.getProductId() != null && StringUtils.isNotBlank(request.getProductId()) ){
            c.andProductIdEqualTo(request.getProductId());
        }
        if(request.getProductName() != null && StringUtils.isNotBlank(request.getProductName()) ){
            c.andProductNameEqualTo(request.getProductName());
        }
        if(request.getInterfaceType() != null && StringUtils.isNotBlank("" + request.getInterfaceType()) ){
            c.andInterfaceTypeEqualTo(request.getInterfaceType());
        }
        if(request.getStatus() != null && StringUtils.isNotBlank("" + request.getStatus()) ){
            c.andStatusEqualTo(request.getStatus());
        }
        if(request.getInterfaceOrderNo() != null && StringUtils.isNotBlank("" + request.getInterfaceOrderNo())){
            c.andInterfaceOrderNoEqualTo(request.getInterfaceOrderNo());
        }
        if(StringUtils.isNotBlank(request.getAddtimeStart())){
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if(StringUtils.isNotBlank(request.getAddtimeEnd())){ 
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }
    }
    /**
     * 所有威能异常订单处理
     */
    @Override
    public List<ChargeOrderInfo>  getWeinengAbnormalOrders(ChargeOrderInfo request)
    {

        PayChargeOrderExample example = new PayChargeOrderExample();
        example.setOrderByClause("addtime ASC");
        //example.setPageNo(1);
        //example.setPageSize(1);

        PayChargeOrderExample.Criteria c =example.createCriteria();
        c.andStatusEqualTo(request.getStatus());

        List<PayChargeOrder> list = payChargeOrderMapper.selectByExample(example);
        if( list.size() <=0 || list == null){
            return null;
        }
        List<ChargeOrderInfo> infoList = new ArrayList<>();

        for (PayChargeOrder one : list)
        {
            ChargeOrderInfo info = new ChargeOrderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayChargeOrder.class, ChargeOrderInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }

        return infoList;
    }

    @Override
    public Map<String, String> phoneRechargeWn(Map<String, String> actParam) {
        Map<String, String> resultMap = new HashMap<>();
        //充值接口
        JSONObject requestParam = new JSONObject();
        requestParam.put("mobile",actParam.get("phone"));
        requestParam.put("productId",actParam.get("forProductId"));
        requestParam.put("outOrderId",actParam.get("selfOrder"));

        String wnProduct = null;
        JSONObject responseDeal=null;
        JSONObject actualDeal=null;
        try {
            WnpayUtils wnpayUtils = new WnpayUtils(ConfigUtil.getValue("account"),ConfigUtil.getValue("password"),ConfigUtil.getValue("request_url"));
            wnProduct = wnpayUtils.flowOrder(requestParam);
            responseDeal = JSONObject.fromObject(wnProduct);
            actualDeal = JSONObject.fromObject(responseDeal.get("data").toString());
            resultMap.put("requestUrl",actualDeal.get("requestUrl")==null?"":actualDeal.get("requestUrl").toString());
            resultMap.put("requestParam",actualDeal.get("requestParam")==null?"":actualDeal.get("requestParam").toString());
            resultMap.put("responseParam",actualDeal.get("responseParam")==null?"":actualDeal.get("responseParam").toString());
            resultMap.put("orderid",actualDeal.get("wnorderid")==null?"":actualDeal.get("wnorderid").toString());
        } catch (Exception e) {
            logger.error("self单号"+actParam.get("selfOrder")+"微能的话费直充接口报错了"+e.getMessage(),e);
            responseDeal=new JSONObject();
            responseDeal.put("code","10001`");
        }
        if(responseDeal!=null&&"10000".equals(responseDeal.get("code"))){
            resultMap.put("code","10000");
        }else if(responseDeal!=null&&"10001".equals(responseDeal.get("code"))){
            resultMap.put("code","10001");
        }else{
            resultMap.put("code","10008");
        }
        return resultMap;
    }


    @Override
    public void updateOrder(PayChargeOrder payChargeOrder) {
        payChargeOrderMapper.updateByPrimaryKeySelective(payChargeOrder);
    }

    @Override
    public void sendEmailToManager(List<BalanceOrder> balanceOrders) {

        ExcelDealUtils excelUtils = new ExcelDealUtils();
        JSONArray titles = new JSONArray();
        titles.add("欧非订单号");
        titles.add("欧非订单状态");
        titles.add("欣享订单号");
        titles.add("欣享订单状态");
        titles.add("金额");
        titles.add("充值时间");

        JSONArray fields = new JSONArray();
        fields.add("interfaceOrderNo");
        fields.add("money");
        fields.add("dou");
        fields.add("expireMonth");
        fields.add("addtimeFormat");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PayChargeOrder savePayOrder(Map<String, String> actParam, ChargeCompanyInfo companyInfo, ChargeProductInfo chargeProductInfo)  throws Exception{
        PayChargeOrder order = new PayChargeOrder();
        String orderno = "CH"+ ToolUtils.createOrderid();
        order.setOrderNo(orderno);
        order.setForeignOrderNo(actParam.get("outOrderNo"));
        order.setCompanyId(companyInfo.getId());
        order.setCompanyName(companyInfo.getCompanyName());
        order.setMerchNo(companyInfo.getMerchNo());
        order.setChargePhone(actParam.get("phone")==null?actParam.get("cardNo"):"");
        order.setProductId(chargeProductInfo.getId());
        order.setProductName(chargeProductInfo.getName());
        order.setProductPrice(chargeProductInfo.getSalePrice());
        order.setProductAmount((Integer) 1);
        order.setTotalMoney(chargeProductInfo.getSalePrice());
        order.setNotifyUrl(actParam.get("notifyUrl"));
        order.setRequestParams(JSONObject.fromObject(actParam).toString());
        order.setStatus((byte)0);
        order.setAddtime(new Date());
        order.setProductType(actParam.get("oilType")==null?0:Integer.valueOf(actParam.get("oilType")));
        payChargeOrderCustomMapper.insertSelective(order);
        String orderId= order.getId();
        //扣除商户金额
        subCompanyMoney(companyInfo,chargeProductInfo);
        PayChargeCompany chargeCompany = payChargeCompanyMapper.selectByPrimaryKey(companyInfo.getId());
        // 新增资金流水
        saveMoneyStream(chargeCompany, chargeProductInfo, orderno, orderId);

        return order;
    }

    public void subCompanyMoney(ChargeCompanyInfo companyInfo,ChargeProductInfo chargeProductInfo) throws Exception {
        //调用接口之前，先扣钱 更新商户信息  金额验证
        Map<String,Object> map = new HashMap<>();
        map.put("companyId",companyInfo.getId());
        map.put("subMoney",chargeProductInfo.getSalePrice());
        map.put("companyKey",ConfigUtil.getValue("MERCH_VALIDE_CODE"));
        int count = payChargeCompanyMapper.updateCompanyMoneySub(map);
        if(count==0){
            throw new Exception("余额不足");
        }
    }

    public void saveMoneyStream(PayChargeCompany companyInfo, ChargeProductInfo chargeProductInfo, String orderno, String orderId) {
        PayChargeCompanyMoneyStream stream = new PayChargeCompanyMoneyStream();
        String streamNo = "MS"+System.currentTimeMillis()+ ToolUtils.getRandomInt(100,999);
        stream.setStreamNo(streamNo);
        stream.setCompanyId(companyInfo.getId());
        stream.setCompanyName(companyInfo.getCompanyName());
        stream.setMerchNo(companyInfo.getMerchNo());
        stream.setOrderId(""+orderId);
        stream.setOrderNo(orderno);
        stream.setProductId(chargeProductInfo.getId());
        stream.setProductName(chargeProductInfo.getName());
        stream.setProductValue(chargeProductInfo.getValue());
     /*   if(type.equals((byte)1)){
            info.setProductBidPrice(chargeProductInfo.getWnProductPrice());
        }else{
            info.setProductBidPrice(chargeProductInfo.getOfProductPrice());
        }*/
        stream.setProductSalePrice(chargeProductInfo.getSalePrice());
        stream.setProductInterfacePrice(chargeProductInfo.getBidPrice());
        stream.setProductAmount(1);
        stream.setTotalMoney(chargeProductInfo.getSalePrice());
        //info.setInterfaceType(type);
        //info.setInterfaceOrderNo(map.get("orderid"));
        stream.setStatus((byte)2);
        stream.setStreamType((byte)1);
        stream.setNewMoney(companyInfo.getMoney());
        stream.setIsDel((byte)0);
        stream.setAddtime(new Date());
        payChargeCompanyMoneyStreamMapper.insertSelective(stream);
    }

}


