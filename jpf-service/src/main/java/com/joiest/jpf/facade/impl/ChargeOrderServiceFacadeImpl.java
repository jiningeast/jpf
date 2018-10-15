package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.common.po.PayChargeOrderExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeOrderMapper;
import com.joiest.jpf.dto.GetChargeOrderRequest;
import com.joiest.jpf.dto.GetChargeOrderResponse;
import com.joiest.jpf.entity.ChargeOrderInfo;
import com.joiest.jpf.facade.ChargeOrderServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ChargeOrderServiceFacadeImpl implements ChargeOrderServiceFacade {

    @Autowired
    private PayChargeOrderMapper payChargeOrderMapper;

    /**
     * 查询单条信息
     * */
    @Override
    public ChargeOrderInfo getOne(PayChargeOrder record){

        PayChargeOrderExample example = new PayChargeOrderExample();
        PayChargeOrderExample.Criteria c = example.createCriteria();
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

}
