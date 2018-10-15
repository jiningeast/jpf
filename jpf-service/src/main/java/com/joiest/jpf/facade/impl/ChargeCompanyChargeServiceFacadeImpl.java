package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayChargeCompanyCharge;
import com.joiest.jpf.common.po.PayChargeCompanyChargeExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeCompanyChargeMapper;
import com.joiest.jpf.dto.GetChargeCompanyChargeRequest;
import com.joiest.jpf.dto.GetChargeCompanyChargeResponse;
import com.joiest.jpf.entity.ChargeCompanyChargeInfo;
import com.joiest.jpf.facade.ChargeCompanyChargeServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChargeCompanyChargeServiceFacadeImpl implements ChargeCompanyChargeServiceFacade {

    @Autowired
    private PayChargeCompanyChargeMapper payChargeCompanyChargeMapper;

    /**
     * 获取充值订单
     */
    @Override
    public GetChargeCompanyChargeResponse getRecords(GetChargeCompanyChargeRequest request){
        GetChargeCompanyChargeResponse response = new GetChargeCompanyChargeResponse();

        PayChargeCompanyChargeExample e = new PayChargeCompanyChargeExample();
        PayChargeCompanyChargeExample.Criteria c = e.createCriteria();
        e.setPageNo(request.getPage());
        e.setPageSize(request.getRows());
        if ( request.getCompanyId() != null && StringUtils.isNotBlank(request.getCompanyId())){
            c.andCompanyIdEqualTo(request.getCompanyId());
        }
        if ( request.getCompanyName() != null && StringUtils.isNotBlank(request.getCompanyName()) ){
            c.andCompanyNameLike("%"+request.getCompanyName()+"%");
        }
        if ( request.getStatus() != null && request.getStatus() == 0 ){
            c.andStatusEqualTo((byte)0);
        }else if ( request.getStatus() != null && request.getStatus() == 1 ){
            c.andStatusEqualTo((byte)1);
        }else if ( request.getStatus() != null && request.getStatus() == 2 ){
            c.andStatusEqualTo((byte)2);
        }
        List<ChargeCompanyChargeInfo> infos = new ArrayList<>();
        List<PayChargeCompanyCharge> list = payChargeCompanyChargeMapper.selectByExample(e);
        int count = payChargeCompanyChargeMapper.countByExample(e);
        if ( list != null && !list.isEmpty() ){
            for ( PayChargeCompanyCharge one:list ){
                ChargeCompanyChargeInfo chargeCompanyChargeInfo = new ChargeCompanyChargeInfo();

                BeanCopier beanCopier = BeanCopier.create(PayChargeCompanyCharge.class,ChargeCompanyChargeInfo.class,false);
                beanCopier.copy(one,chargeCompanyChargeInfo,null);

                infos.add(chargeCompanyChargeInfo);
            }
        }
        response.setList(infos);
        response.setCount(count);

        return response;
    }

    /**
     * 添加充值订单
     */
    @Override
    public int addRecord(ChargeCompanyChargeInfo info){
        PayChargeCompanyCharge payChargeCompanyCharge = new PayChargeCompanyCharge();

        BeanCopier beanCopier = BeanCopier.create(ChargeCompanyChargeInfo.class,PayChargeCompanyCharge.class,false);
        beanCopier.copy(info,payChargeCompanyCharge,null);
        return payChargeCompanyChargeMapper.insert(payChargeCompanyCharge);
    }
}
