package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayShopBargainRechargeOrder;
import com.joiest.jpf.common.po.PayShopBargainRechargeOrderExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBargainRechargeOrderMapper;
import com.joiest.jpf.dto.GetShopBargainRechargeOrderRequest;
import com.joiest.jpf.dto.GetShopBargainRechargeOrderResponse;
import com.joiest.jpf.entity.ShopBargainRechargeOrderInfo;
import com.joiest.jpf.facade.ShopBargainRechargeOrderServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ShopBargainRechargeOrderServiceFacadeImpl implements ShopBargainRechargeOrderServiceFacade {

    @Autowired
    private PayShopBargainRechargeOrderMapper payShopBargainRechargeOrderMapper;

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
        if (StringUtils.isNotBlank(request.getOrderNo())){
            c.andOrderNoEqualTo(request.getOrderNo());
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
        if (StringUtils.isNotBlank(request.getAddtimeStart())) {
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(), DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd())) {
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(), DateUtils.DATEFORMATLONG));
        }
        if ( request.getInfoStatus() != null && StringUtils.isNotBlank(""+request.getInfoStatus()) ){
            c.andInfoStatusEqualTo(request.getInfoStatus());
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
}
