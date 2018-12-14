package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayShopBargainRechargeOrder;
import com.joiest.jpf.common.po.PayShopBargainRechargeOrderExample;
import com.joiest.jpf.common.util.ConfigUtil;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopBargainRechargeOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBargainRechargeOrderMapper;
import com.joiest.jpf.dto.GetShopBargainRechargeOrderRequest;
import com.joiest.jpf.dto.GetShopBargainRechargeOrderResponse;
import com.joiest.jpf.entity.ShopBargainRechargeOrderInfo;
import com.joiest.jpf.facade.ShopBargainRechargeOrderServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopBargainRechargeOrderServiceFacadeImpl implements ShopBargainRechargeOrderServiceFacade {

    @Autowired
    private PayShopBargainRechargeOrderMapper payShopBargainRechargeOrderMapper;
    @Autowired
    private PayShopBargainRechargeOrderCustomMapper payShopBargainRechargeOrderCustomMapper;

    /**
     * 获取敬恒订单
     */
    @Override
    public GetShopBargainRechargeOrderResponse getRecords(GetShopBargainRechargeOrderRequest request){

        GetShopBargainRechargeOrderResponse response = new GetShopBargainRechargeOrderResponse();
        List<ShopBargainRechargeOrderInfo> infos = new ArrayList<>();

        PayShopBargainRechargeOrderExample e = new PayShopBargainRechargeOrderExample();
        PayShopBargainRechargeOrderExample.Criteria c = e.createCriteria();

        e.setPageNo(request.getPage());
        e.setPageSize(request.getRows());
        e.setOrderByClause("id DESC");

//        if (StringUtils.isNotBlank(request.getOrderNo())){
//            c.andOrderNoEqualTo(request.getOrderNo());
//        }
        if(StringUtils.isNotBlank(request.getPullOrderNo())){
            c.andPullOrderNoEqualTo(request.getPullOrderNo());
        }
        if(StringUtils.isNotBlank(request.getMerchNo()) && request.getMerchNo()!=null){
            c.andPullMerchNoEqualTo(request.getMerchNo());
        }
        if ( request.getOrderType() != null && StringUtils.isNotBlank(""+request.getOrderType()) ){
            c.andOrderTypeEqualTo(request.getOrderType());
        }
        if ( StringUtils.isNotBlank(request.getForeignOrderNo()) ){
            c.andForeignOrderNoEqualTo(request.getForeignOrderNo());
        }
        if ( StringUtils.isNotBlank(request.getItemName()) ){
            c.andItemNameLike('%'+request.getItemName()+'%');
        }
        if ( request.getFacePrice() != null && StringUtils.isNotBlank(""+request.getFacePrice()) ){
            c.andFacePriceEqualTo(request.getFacePrice());
        }
        if (StringUtils.isNotBlank(request.getAddtimeStart())&& request.getAddtimeStart()!=null) {
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(), DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()) && request.getAddtimeEnd() !=null) {
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(), DateUtils.DATEFORMATLONG));
        }
//        if ( request.getInfoStatus() != null && StringUtils.isNotBlank(""+request.getInfoStatus()) ){
//            c.andInfoStatusEqualTo(request.getInfoStatus());
//        }
        if ( request.getMatchingStatus() != null && StringUtils.isNotBlank(String.valueOf(request.getMatchingStatus())) ){
            c.andMatchingStatusEqualTo(request.getMatchingStatus());
        }
        int count = payShopBargainRechargeOrderMapper.countByExample(e);
        List<PayShopBargainRechargeOrder> list = payShopBargainRechargeOrderMapper.selectByExample(e);
        for (PayShopBargainRechargeOrder one:list){
            ShopBargainRechargeOrderInfo shopBargainRechargeOrderInfo = new ShopBargainRechargeOrderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopBargainRechargeOrder.class, ShopBargainRechargeOrderInfo.class, false);
            beanCopier.copy(one, shopBargainRechargeOrderInfo, null);

            infos.add(shopBargainRechargeOrderInfo);
        }
        response.setList(infos);
        response.setCount(count);

        return response;
    }

    @Override
    public BigDecimal getMoneyTotal(String userId,String startDate) {
        Map<String,Object> param = new HashMap<>();
        param.put("userId",Integer.valueOf(userId));
        if(StringUtils.isNotBlank(startDate)){
            param.put("startDate",DateUtils.getFdate(startDate, DateUtils.DATEFORMATLONG));
        }else{
            param.put("startDate",null);
        }
        return payShopBargainRechargeOrderCustomMapper.getTotalMoney(param);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public   List<PayShopBargainRechargeOrder> pushDataToRedisTask(long querySize) {
        PayShopBargainRechargeOrderExample example = new PayShopBargainRechargeOrderExample();
        PayShopBargainRechargeOrderExample.Criteria criteria = example.createCriteria();
        criteria.andMatchingStatusEqualTo((byte)0);
        example.setPageNo(1);
        if(StringUtils.isNotBlank(ConfigUtil.getValue("ZHANYUNUSERID"))){
            criteria.andUserIdEqualTo(Integer.valueOf(ConfigUtil.getValue("ZHANYUNUSERID")));
        }
        if(StringUtils.isNotBlank(ConfigUtil.getValue("ZHANYUANDATE"))){
            criteria.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(ConfigUtil.getValue("ZHANYUANDATE"), DateUtils.DATEFORMATLONG));
        }
        example.setPageSize(querySize);
        example.setOrderByClause(" id asc ");
        List<PayShopBargainRechargeOrder> payShopBargainRechargeOrders = payShopBargainRechargeOrderMapper.selectByExample(example);
        //停止更新数据
      /*  for (PayShopBargainRechargeOrder payShopBargainRechargeOrder: payShopBargainRechargeOrders) {
            payShopBargainRechargeOrder.setMatchingStatus((byte)1);
        }
        //批量更新数据量
        payShopBargainRechargeOrderCustomMapper.batchUpdatePayShopBro(payShopBargainRechargeOrders);*/
        return payShopBargainRechargeOrders;
    }

    @Override
    public GetShopBargainRechargeOrderResponse getRecordsExcel(GetShopBargainRechargeOrderRequest request) {
        GetShopBargainRechargeOrderResponse response = new GetShopBargainRechargeOrderResponse();
        List<ShopBargainRechargeOrderInfo> infos = new ArrayList<>();

        PayShopBargainRechargeOrderExample e = new PayShopBargainRechargeOrderExample();
        PayShopBargainRechargeOrderExample.Criteria c = e.createCriteria();

        e.setPageNo(request.getPage());
        e.setPageSize(request.getRows());
        e.setOrderByClause("id DESC");

        if (StringUtils.isNotBlank(request.getOrderNo())){
            c.andOrderNoEqualTo(request.getOrderNo());
        }
        if(StringUtils.isNotBlank(request.getPullOrderNo())){
            c.andPullOrderNoEqualTo(request.getPullOrderNo());
        }
        if(StringUtils.isNotBlank(request.getMerchNo()) && request.getMerchNo()!=null){
            c.andPullMerchNoEqualTo(request.getMerchNo());
        }
        if ( request.getOrderType() != null && StringUtils.isNotBlank(""+request.getOrderType()) ){
            c.andOrderTypeEqualTo(request.getOrderType());
        }
        if ( StringUtils.isNotBlank(request.getForeignOrderNo()) ){
            c.andForeignOrderNoEqualTo(request.getForeignOrderNo());
        }
        if ( StringUtils.isNotBlank(request.getItemName()) ){
            c.andItemNameLike('%'+request.getItemName()+'%');
        }
        if ( request.getFacePrice() != null && StringUtils.isNotBlank(""+request.getFacePrice()) ){
            c.andFacePriceEqualTo(request.getFacePrice());
        }
        if (StringUtils.isNotBlank(request.getAddtimeStart())&& request.getAddtimeStart()!=null) {
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(), DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()) && request.getAddtimeEnd() !=null) {
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(), DateUtils.DATEFORMATLONG));
        }
        if ( request.getInfoStatus() != null && StringUtils.isNotBlank(""+request.getInfoStatus()) ){
            c.andInfoStatusEqualTo(request.getInfoStatus());
        }
        if ( request.getMatchingStatus() != null && StringUtils.isNotBlank(""+request.getMatchingStatus()) ){
            c.andMatchingStatusEqualTo(request.getMatchingStatus());
        }
        int count = payShopBargainRechargeOrderMapper.countByExample(e);
        List<PayShopBargainRechargeOrder> list = payShopBargainRechargeOrderMapper.selectByExampleExcel(e);
        for (PayShopBargainRechargeOrder one:list){
            ShopBargainRechargeOrderInfo shopBargainRechargeOrderInfo = new ShopBargainRechargeOrderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopBargainRechargeOrder.class, ShopBargainRechargeOrderInfo.class, false);
            beanCopier.copy(one, shopBargainRechargeOrderInfo, null);

            infos.add(shopBargainRechargeOrderInfo);
        }
        response.setList(infos);
        response.setCount(count);

        return response;
    }
}
