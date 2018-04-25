package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ValidatorUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsPaytypeMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsTypeMapper;
import com.joiest.jpf.dto.AddMerPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeResponse;
import com.joiest.jpf.dto.ModifyMerPayTypeRequest;
import com.joiest.jpf.entity.MerchantPayTypeInfo;
import com.joiest.jpf.facade.MerPayTypeServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.*;

public class MerPayTypeServiceFacadeImpl implements MerPayTypeServiceFacade {

    @Autowired
    private PayMerchantsMapper payMerchantsMapper;
    @Autowired
    private PayMerchantsPaytypeMapper payMerchantsPaytypeMapper;
    @Autowired
    private PayMerchantsTypeMapper payMerchantsTypeMapper;

    @Override
    public GetMerchPayTypeResponse getMerPayTypes(GetMerchPayTypeRequest request) {
        PayMerchantsPaytypeExample example = new PayMerchantsPaytypeExample();
        example.setOrderByClause("created DESC");
        example.setPageNo(request.getPageNo());
        example.setPageSize(request.getPageSize());
        PayMerchantsPaytypeExample.Criteria c = example.createCriteria();
        if (request != null && request.getMtsid() != null) {
            c.andMtsidEqualTo(request.getMtsid());
        }
        if (request != null && request.getTpid() != null) {
            c.andTpidEqualTo(request.getTpid());
        }
        if (request != null && StringUtils.isNotBlank(request.getCatpath())) {
            c.andCatpathEqualTo(request.getCatpath());
        }
        if (request != null && StringUtils.isNotBlank(request.getCreateStartDate()) && StringUtils.isNotBlank(request.getCreateEndDate())) {
            c.andCreatedBetween(DateUtils.getString2ShortDate(request.getCreateStartDate()), DateUtils.getString2ShortDate(request.getCreateEndDate()));
        }

        List<PayMerchantsPaytype> payMerchantsPaytypes = payMerchantsPaytypeMapper.selectByExample(example);
        example.clear();
        int count = payMerchantsPaytypeMapper.countByExample(example);
        //获取支付类型数据
        List<MerchantPayTypeInfo> payTypeInfos = new ArrayList<>();
        PayMerchantsTypeExample payMerchantsTypeExample = new PayMerchantsTypeExample();
        PayMerchantsTypeExample.Criteria c1 = payMerchantsTypeExample.createCriteria();
        //只拿支付类型数据
        String pid = "5";
        c1.andPidEqualTo(pid);
        List<PayMerchantsType> payMerchantsTypes = payMerchantsTypeMapper.selectByExample(payMerchantsTypeExample);
        Map<String,String> map = new HashMap<>();
        if ( payMerchantsTypes.size() > 0 )
        {
            for (PayMerchantsType payType : payMerchantsTypes)
            {
                map.put(payType.getCatid().toString(), payType.getCat());
            }
        }
        for (PayMerchantsPaytype payMerchantsPaytype : payMerchantsPaytypes) {
            String payType_tmp = new String();
            payType_tmp = map.get(payMerchantsPaytype.getTpid().toString());
            payMerchantsPaytype.setCatpath(payType_tmp);
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
        ValidatorUtils.validate(request);

        PayMerchants payMerchants = payMerchantsMapper.selectByPrimaryKey(request.getMtsid());
        if (payMerchants == null) {
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "商户信息不存在");
        }

        for (int i = 0; i < request.getTpid().size(); i++) {
            PayMerchantsType payMerchantsType = payMerchantsTypeMapper.selectByPrimaryKey(request.getTpid().get(i));

            PayMerchantsPaytypeExample example = new PayMerchantsPaytypeExample();
            PayMerchantsPaytypeExample.Criteria c = example.createCriteria();
            c.andMtsidEqualTo(request.getMtsid());
            c.andTpidEqualTo(request.getTpid().get(i));
            c.andCatpathEqualTo(payMerchantsType.getCatpath());

            List<PayMerchantsPaytype> payMerchantsPaytypes = payMerchantsPaytypeMapper.selectByExample(example);
            if (payMerchantsPaytypes != null && !payMerchantsPaytypes.isEmpty()) {
                continue;
            }

            PayMerchantsPaytype record = new PayMerchantsPaytype();
            record.setMtsid(request.getMtsid());
            record.setTpid(request.getTpid().get(i));
            record.setCatpath(payMerchantsType.getCatpath());
            record.setCreated(new Date());
            payMerchantsPaytypeMapper.insertSelective(record);
        }

        return new JpfResponseDto();
    }

    /**
     * 添加商户支付类型 --单个
     */
    public JpfResponseDto addMerPayTypeOne(ModifyMerPayTypeRequest request)
    {
        JsonUtils jsonUtils = new JsonUtils();
        ValidatorUtils.validate(request);

        PayMerchants payMerchants = payMerchantsMapper.selectByPrimaryKey(request.getMtsid());
        if (payMerchants == null) {
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "商户信息不存在");
        }

        //获取支付类型信息
        PayMerchantsType payMerchantsType = payMerchantsTypeMapper.selectByPrimaryKey(request.getTpid());

        PayMerchantsPaytypeExample example = new PayMerchantsPaytypeExample();
        PayMerchantsPaytypeExample.Criteria c = example.createCriteria();
        c.andMtsidEqualTo(request.getMtsid());
        c.andTpidEqualTo(request.getTpid());
        c.andCatpathEqualTo(payMerchantsType.getCatpath());
        List<PayMerchantsPaytype> payMerchantsPaytypes = payMerchantsPaytypeMapper.selectByExample(example);
//        if (payMerchantsPaytypes != null && !payMerchantsPaytypes.isEmpty()) {
//            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST);
//        }
        Map<String, Object> jsonMap = new HashMap<>();
        List<Object> list = new ArrayList<>();
        String newJson = "";
        //微信
        if ( request.getTpid() == 9 )
        {
            if ( StringUtils.isNotBlank(request.getWx_merSubMchid()) )
            {
                jsonMap.put( "merSubMchid", request.getWx_merSubMchid() );
            }
            list.add(jsonMap);
            newJson = jsonUtils.toJson(list);
        }

        PayMerchantsPaytype record = new PayMerchantsPaytype();
        record.setMtsid(request.getMtsid());
        record.setTpid(request.getTpid());
        record.setCatpath(payMerchantsType.getCatpath());
        record.setCreated(new Date());
        if ( StringUtils.isNotBlank(newJson) )
        {
            record.setParam(newJson);
        }
        payMerchantsPaytypeMapper.insertSelective(record);

        return new JpfResponseDto();
    }

    public JpfResponseDto modifyMerPayTypeOne(ModifyMerPayTypeRequest request)
    {
        JsonUtils jsonUtils = new JsonUtils();
        ValidatorUtils.validate(request);

        PayMerchants payMerchants = payMerchantsMapper.selectByPrimaryKey(request.getMtsid());
        if (payMerchants == null) {
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "商户信息不存在");
        }

        //获取支付类型信息
        PayMerchantsType payMerchantsType = payMerchantsTypeMapper.selectByPrimaryKey(request.getTpid());

        PayMerchantsPaytypeExample example = new PayMerchantsPaytypeExample();
        PayMerchantsPaytypeExample.Criteria c = example.createCriteria();
        c.andMtsidEqualTo(request.getMtsid());
        c.andTpidEqualTo(request.getTpid());
        c.andCatpathEqualTo(payMerchantsType.getCatpath());
        List<PayMerchantsPaytype> payMerchantsPaytypes = payMerchantsPaytypeMapper.selectByExample(example);
        if (payMerchantsPaytypes != null && !payMerchantsPaytypes.isEmpty()) {
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST);
        }
        Map<String, Object> jsonMap = new HashMap<>();
        List<Object> list = new ArrayList<>();
        String newJson = "";
        //微信
        if ( request.getTpid() == 9 )
        {
            if ( StringUtils.isNotBlank(request.getWx_merSubMchid()) )
            {
                jsonMap.put( "merSubMchid", request.getWx_merSubMchid() );
            }
            list.add(jsonMap);
            newJson = jsonUtils.toJson(list);
        }

        PayMerchantsPaytype record = new PayMerchantsPaytype();
        record.setMtsid(request.getMtsid());
        record.setTpid(request.getTpid());
        record.setCatpath(payMerchantsType.getCatpath());
        record.setCreated(new Date());
        if ( StringUtils.isNotBlank(newJson) )
        {
            record.setParam(newJson);
        }

        PayMerchantsPaytypeExample paytypeExample = new PayMerchantsPaytypeExample();
        PayMerchantsPaytypeExample.Criteria c1 = paytypeExample.createCriteria();
        c1.andIdEqualTo(request.getId());

        payMerchantsPaytypeMapper.updateByExampleSelective(record,paytypeExample);

        return new JpfResponseDto();
    }

    @Override
    public JpfResponseDto deleteMerPayType(List<Long> id) {
        for (int i = 0; i < id.size(); i++) {
            payMerchantsPaytypeMapper.deleteByPrimaryKey(id.get(i));
        }
        return new JpfResponseDto();
    }

    @Override
    public GetMerchPayTypeResponse getOneMerPayTypes(Long mtsid)
    {
        PayMerchantsPaytypeExample example = new PayMerchantsPaytypeExample();
        PayMerchantsPaytypeExample.Criteria c = example.createCriteria();
        c.andMtsidEqualTo(mtsid);
        List<PayMerchantsPaytype> paytypeList = payMerchantsPaytypeMapper.selectByExample(example);

        //获取所有的支付类型
        String pid = "5";
        PayMerchantsTypeExample paytypeExample = new PayMerchantsTypeExample();
        PayMerchantsTypeExample.Criteria c1 = paytypeExample.createCriteria();
        c1.andPidEqualTo(pid);
        List<PayMerchantsType> payMerchantsTypes = payMerchantsTypeMapper.selectByExample(paytypeExample);
        if ( payMerchantsTypes == null || payMerchantsTypes.isEmpty() )
        {
            return null;
        }
        Map<Integer,String> map = new HashMap<>();
        for ( PayMerchantsType payType : payMerchantsTypes )
        {
            map.put(payType.getCatid(), payType.getCat());
        }

        List<MerchantPayTypeInfo> payTypeInfos = new ArrayList<>();
        for ( PayMerchantsPaytype one : paytypeList )
        {
            MerchantPayTypeInfo merchantPayTypeInfo = new MerchantPayTypeInfo();
            BeanCopier beanCopier = BeanCopier.create(PayMerchantsPaytype.class, merchantPayTypeInfo.getClass(), false);
            beanCopier.copy(one, merchantPayTypeInfo, null);
            String payType_temp = new String();
            payType_temp = map.get(one.getTpid());
            merchantPayTypeInfo.setCatpath_zh(payType_temp);
            payTypeInfos.add(merchantPayTypeInfo);
        }
        GetMerchPayTypeResponse response = new GetMerchPayTypeResponse();
        response.setPayTypeInfos(payTypeInfos);
        return response;
    }
}
