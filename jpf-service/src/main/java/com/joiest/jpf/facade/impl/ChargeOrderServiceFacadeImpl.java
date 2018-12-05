package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.common.po.PayChargeOrderExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayChargeOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeOrderMapper;
import com.joiest.jpf.dto.ChargeOrderInterfaceRequest;
import com.joiest.jpf.dto.GetChargeOrderRequest;
import com.joiest.jpf.dto.GetChargeOrderResponse;
import com.joiest.jpf.entity.ChargeOrderInfo;
import com.joiest.jpf.facade.ChargeOrderServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChargeOrderServiceFacadeImpl implements ChargeOrderServiceFacade {

    @Autowired
    private PayChargeOrderMapper payChargeOrderMapper;

    @Autowired
    private PayChargeOrderCustomMapper payChargeOrderCustomMapper;
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
        //c.andStatusEqualTo((byte)request.getStatus());
        //c.andInterfaceTypeEqualTo(request.getInterfaceType());
        //c.andPaytimeLessThanOrEqualTo(request.getPaytime());
        c.andInterfaceOrderNoIsNull();

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

}


