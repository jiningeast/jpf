package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayMerchantsPaytype;
import com.joiest.jpf.common.po.PayMerchantsPaytypeExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.ValidatorUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsPaytypeMapper;
import com.joiest.jpf.dto.AddMerPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeResponse;
import com.joiest.jpf.dto.ModifyMerPayTypeRequest;
import com.joiest.jpf.entity.MerchantPayTypeInfo;
import com.joiest.jpf.entity.PcaInfo;
import com.joiest.jpf.facade.MerPayTypeServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MerPayTypeServiceFacadeImpl implements MerPayTypeServiceFacade {

    @Autowired
    private PayMerchantsPaytypeMapper payMerchantsPaytypeMapper;

    @Override
    public GetMerchPayTypeResponse getMerPayTypes(GetMerchPayTypeRequest request) {

        ValidatorUtils.validate(GetMerchPayTypeRequest.class);

        PayMerchantsPaytypeExample example = new PayMerchantsPaytypeExample();
        example.setOrderByClause("created DESC");
        example.setPageNo(request.getPageNo());
        example.setPageSize(request.getPageSize());
        PayMerchantsPaytypeExample.Criteria c = example.createCriteria();
        if(request!=null&&request.getMtsid()!=null){
            c.andMtsidEqualTo(request.getMtsid());
        }
        if (request!=null&&request.getTpid() != null) {
            c.andTpidEqualTo(request.getTpid());
        }
        if(request!=null&&StringUtils.isNotBlank(request.getCatpath())){
            c.andCatpathEqualTo(request.getCatpath());
        }
        if(request!=null&&StringUtils.isNotBlank(request.getCreateStartDate())&&StringUtils.isNotBlank(request.getCreateEndDate())){
            c.andCreatedBetween(DateUtils.getString2ShortDate(request.getCreateStartDate()),DateUtils.getString2ShortDate(request.getCreateEndDate()));
        }
        List<PayMerchantsPaytype> payMerchantsPaytypes = payMerchantsPaytypeMapper.selectByExample(example);
        example.clear();
        int count = payMerchantsPaytypeMapper.countByExample(example);
        List<MerchantPayTypeInfo> payTypeInfos = new ArrayList<>();
        for (PayMerchantsPaytype payMerchantsPaytype : payMerchantsPaytypes) {
            MerchantPayTypeInfo merchantPayTypeInfo = new MerchantPayTypeInfo();
            BeanCopier beanCopier = BeanCopier.create(PayMerchantsPaytype.class, MerchantPayTypeInfo.class, false);
            beanCopier.copy(payMerchantsPaytype, merchantPayTypeInfo, null);
            payTypeInfos.add(merchantPayTypeInfo);
        }
        GetMerchPayTypeResponse response = new GetMerchPayTypeResponse();
        response.setPayTypeInfos(payTypeInfos);
        response.setCount(count);
        return response;
    }

    @Override
    public JpfResponseDto addMerPayType(AddMerPayTypeRequest request) {
        ValidatorUtils.validate(AddMerPayTypeRequest.class);

        PayMerchantsPaytype record = new PayMerchantsPaytype();
        BeanCopier beanCopier = BeanCopier.create(AddMerPayTypeRequest.class, PayMerchantsPaytype.class, false);
        beanCopier.copy(request, record,null);
        payMerchantsPaytypeMapper.insertSelective(record);
        record.setCreated(new Date());
        return setResponse("","");
    }

    @Override
    public JpfResponseDto modifyMerPayType(ModifyMerPayTypeRequest request) {
        ValidatorUtils.validate(ModifyMerPayTypeRequest.class);

        PayMerchantsPaytype payMerchantsPaytype = payMerchantsPaytypeMapper.selectByPrimaryKey(request.getId());
        if(payMerchantsPaytype==null){
            return setResponse("9999","记录不存在");
        }

        PayMerchantsPaytype record = new PayMerchantsPaytype();
        BeanCopier beanCopier = BeanCopier.create(ModifyMerPayTypeRequest.class, PayMerchantsPaytype.class, false);
        beanCopier.copy(request, record,null);
        record.setUpdated(new Date());
        payMerchantsPaytypeMapper.updateByPrimaryKeySelective(record);
        return setResponse("","");
    }

    private JpfResponseDto setResponse(String errorCode,String errorMsg) {
        JpfResponseDto jpfResponseDto = new JpfResponseDto();
        if(StringUtils.isNotBlank(errorCode)&&!errorCode.equals("0000")){
            jpfResponseDto.setResponseError(errorCode,errorMsg);
        }
        return jpfResponseDto;
    }
}
