package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
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
        if(request.getPage()<=0){
            request.setPage(1);
        }
        if (request.getRows() <= 0) {
            request.setRows(10);
        }
        PayMerchantsPaytypeExample example = new PayMerchantsPaytypeExample();
        example.setOrderByClause("created DESC");
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
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
        //只获取支付类型数据
        String pid = "5";
        c1.andPidEqualTo(pid);
        //获取支付类型
        List<PayMerchantsType> payMerchantsTypes = payMerchantsTypeMapper.selectByExample(payMerchantsTypeExample);
        Map<String,String> map = new HashMap<>();
        if ( payMerchantsTypes.size() > 0 )
        {
            for (PayMerchantsType payType : payMerchantsTypes)
            {
                map.put(payType.getCatid().toString(), payType.getCat());
            }
        }

        //获取分期信息
        PayMerchantsTypeExample stageExample = new PayMerchantsTypeExample();
        PayMerchantsTypeExample.Criteria cStage = stageExample.createCriteria();
        String pid_stage = "24";
        cStage.andPidEqualTo(pid_stage);
        List<PayMerchantsType> stageList = payMerchantsTypeMapper.selectByExample(stageExample);
        Map<String,String> mapStage = new HashMap<>();
        if ( stageList.size() > 0 )
        {
            for ( PayMerchantsType stageType : stageList )
            {
                mapStage.put(stageType.getCatid().toString(), stageType.getCat());
            }
        }

        //获取商户信息
        Map<Long, String> merchsMap = new HashMap<>();
        if ( payMerchantsPaytypes.size() > 0 )
        {
            List<Long> mtsids = new ArrayList<>();
            for ( PayMerchantsPaytype paytype : payMerchantsPaytypes )
            {
                mtsids.add(paytype.getMtsid());
            }
            PayMerchantsExample merchListExample = new PayMerchantsExample();
            PayMerchantsExample.Criteria merchsC = merchListExample.createCriteria();
            merchsC.andIdIn(mtsids);
            HashSet h = new HashSet(mtsids);
            mtsids.clear();
            mtsids.addAll(h);
            System.out.println(mtsids);
            List<PayMerchants> merList = payMerchantsMapper.selectByExample(merchListExample);
            if ( merList.size() > 0 )
            {
                for ( PayMerchants mer : merList )
                {
                    merchsMap.put(mer.getId(), mer.getMerchName());
                }
            }
        }

        for (PayMerchantsPaytype payMerchantsPaytype : payMerchantsPaytypes) {
            //支付类型zh
            String payType_tmp = new String();
            payType_tmp = map.get(payMerchantsPaytype.getTpid().toString());
            payMerchantsPaytype.setCatpath(payType_tmp);
            //分期信息
            if ( StringUtils.isNotBlank(payMerchantsPaytype.getCatpath()) )
            {
                StringBuilder sbf = new StringBuilder();
                String bankcatid_str = payMerchantsPaytype.getBankcatid();
                String[] bankcatid_arr = bankcatid_str.split("\\,");
                for ( String x : bankcatid_arr )
                {
                    sbf.append(mapStage.get(x) + "<br/>");
                }
                String stage_cn = sbf.substring(0, sbf.length()-1);
                payMerchantsPaytype.setBankcatid(stage_cn);
            }

            MerchantPayTypeInfo merchantPayTypeInfo = new MerchantPayTypeInfo();
            BeanCopier beanCopier = BeanCopier.create(PayMerchantsPaytype.class, MerchantPayTypeInfo.class, false);
            beanCopier.copy(payMerchantsPaytype, merchantPayTypeInfo, null);
            //商户信息
            merchantPayTypeInfo.setMerch_name(merchsMap.get(payMerchantsPaytype.getMtsid()));
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

        if (payMerchantsPaytypes != null && !payMerchantsPaytypes.isEmpty()) {
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST);
        }
        if (request.getBankcatid().length == 0 )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "请选择分期类型");
        }
        //分期类型
        StringBuilder builder = new StringBuilder();
        for ( int i=0; i< request.getBankcatid().length; i++ )
        {
            builder.append(request.getBankcatid()[i]+",");
        }
        String bankcatid = builder.substring(0, builder.length()-1).toString();

        Map<String, Object> jsonMap = new HashMap<>();
        List<Object> list = new ArrayList<>();
        String newJson = "";
        //微信
        if ( request.getTpid() == 9 )
        {
            if ( StringUtils.isNotBlank(request.getWx_merSubMchid()) )
            {
                jsonMap.put( "merSubMchid", request.getWx_merSubMchid() );
            } else
            {
                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "微信商户号不能为空");
            }
            String paytypeId_wx = "35";
            if ( !bankcatid.equals(paytypeId_wx) )
            {
                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, payMerchantsType.getCat() + "的分期只能设置为：不分期");
            }
            newJson = jsonUtils.toJson(jsonMap);
        }

        // 银联参数
        if ( request.getTpid() == 7 ){
            if ( StringUtils.isNotBlank(request.getCp_MerchaNo()) ){
                jsonMap.put("CP_MerchaNo", request.getCp_MerchaNo() );
            }
            if ( StringUtils.isNotBlank(request.getCp_Code()) ){
                jsonMap.put("CP_Code", request.getCp_Code());
            }
            if ( StringUtils.isNotBlank(request.getCp_Acctid()) ){
                jsonMap.put("CP_Acctid", request.getCp_Acctid());
            }
            if ( StringUtils.isNotBlank(request.getCp_Salt()) ){
                jsonMap.put("CP_Salt", request.getCp_Salt());
            }
            newJson = jsonUtils.toJson(jsonMap);
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
        record.setBankcatid(bankcatid);

        payMerchantsPaytypeMapper.insertSelective(record);
        return new JpfResponseDto();
    }

    /**
     * 编辑商户支付类型 --单个
     */
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
        if (payMerchantsPaytypes == null && payMerchantsPaytypes.isEmpty()) {
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST);
        }
        if (request.getBankcatid().length == 0 )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "请选择分期类型");
        }
        // 银联分期类型
        StringBuilder builder = new StringBuilder();
        for ( int i=0; i< request.getBankcatid().length; i++ )
        {
            builder.append(request.getBankcatid()[i]+",");
        }
        String bankcatid = builder.substring(0, builder.length()-1).toString();

        Map<String, Object> jsonMap = new HashMap<>();
        List<Object> list = new ArrayList<>();
        String newJson = "";
        //微信
        if ( request.getTpid() == 9 )
        {
            if ( StringUtils.isNotBlank(request.getWx_merSubMchid()) )
            {
                jsonMap.put( "merSubMchid", request.getWx_merSubMchid() );
            } else
            {
                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "微信商户号不能为空");
            }
            String paytypeId_wx = "35";
            if ( !bankcatid.equals(paytypeId_wx) )
            {
                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, payMerchantsType.getCat() + "的分期只能设置为：不分期");
            }
            newJson = jsonUtils.toJson(jsonMap);
        }

        // 银联参数
        if ( request.getTpid() == 7 ){
            if ( StringUtils.isNotBlank(request.getCp_MerchaNo()) ){
                jsonMap.put("CP_MerchaNo", request.getCp_MerchaNo() );
            }
            if ( StringUtils.isNotBlank(request.getCp_Code()) ){
                jsonMap.put("CP_Code", request.getCp_Code());
            }
            if ( StringUtils.isNotBlank(request.getCp_Acctid()) ){
                jsonMap.put("CP_Acctid", request.getCp_Acctid());
            }
            if ( StringUtils.isNotBlank(request.getCp_Salt()) ){
                jsonMap.put("CP_Salt", request.getCp_Salt());
            }
            newJson = jsonUtils.toJson(jsonMap);
        }

        PayMerchantsPaytype record = new PayMerchantsPaytype();
        record.setMtsid(request.getMtsid());
        record.setTpid(request.getTpid());
        record.setCatpath(payMerchantsType.getCatpath());
        record.setUpdated(new Date());
        if ( StringUtils.isNotBlank(newJson) )
        {
            record.setParam(newJson);
        }
        record.setBankcatid(bankcatid);

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

    /**
     * 获取某个商户指定支付类型的详情
     * @param mtsid 商户mtsid
     * @param tpid 支付类型
     * @return 支付配置信息
     */
    @Override
    public MerchantPayTypeInfo getOneMerPayTypeByTpid(Long mtsid, Integer tpid){
        PayMerchantsPaytypeExample e = new PayMerchantsPaytypeExample();
        PayMerchantsPaytypeExample.Criteria c = e.createCriteria();
        c.andMtsidEqualTo(mtsid);
        c.andTpidEqualTo(tpid);
        List<PayMerchantsPaytype> list = payMerchantsPaytypeMapper.selectByExample(e);

        MerchantPayTypeInfo merchantPayTypeInfo = new MerchantPayTypeInfo();
        BeanCopier beanCopier = BeanCopier.create(PayMerchantsPaytype.class, MerchantPayTypeInfo.class, false);
        beanCopier.copy(list.get(0), merchantPayTypeInfo, null);

        return merchantPayTypeInfo;
    }
    @Override
    public MerchantPayTypeInfo getOneMerPayTypeByTpid(Long mtsid, Integer tpid, boolean forInterface){
        PayMerchantsPaytypeExample e = new PayMerchantsPaytypeExample();
        PayMerchantsPaytypeExample.Criteria c = e.createCriteria();
        c.andMtsidEqualTo(mtsid);
        c.andTpidEqualTo(tpid);
        List<PayMerchantsPaytype> list = payMerchantsPaytypeMapper.selectByExample(e);

        if ( forInterface && list == null  && list.isEmpty() ){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode(),"商户支付参数获取失败");
        }

        MerchantPayTypeInfo merchantPayTypeInfo = new MerchantPayTypeInfo();
        BeanCopier beanCopier = BeanCopier.create(PayMerchantsPaytype.class, MerchantPayTypeInfo.class, false);
        beanCopier.copy(list.get(0), merchantPayTypeInfo, null);

        return merchantPayTypeInfo;
    }

    /**
     * 获取某个商户的某个支付类型 by id
     */
    public MerchantPayTypeInfo getMerOnePayTypes(Long id)
    {
        if ( id == null )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        PayMerchantsPaytype payMerchantsPaytype = payMerchantsPaytypeMapper.selectByPrimaryKey(id);
        MerchantPayTypeInfo payTypeInfo = new MerchantPayTypeInfo();
        BeanCopier beanCopier = BeanCopier.create(PayMerchantsPaytype.class, MerchantPayTypeInfo.class, false);
        beanCopier.copy(payMerchantsPaytype, payTypeInfo, null);
        return payTypeInfo;
    }

    /**
     * 商户分期类型配置
     */
    public JpfResponseDto modifyMerBankcatid(ModifyMerPayTypeRequest request)
    {
        if ( request.getId() == null )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "ID不能为空");
        }
        PayMerchantsPaytypeExample example = new PayMerchantsPaytypeExample();
        PayMerchantsPaytypeExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(request.getId());
        PayMerchantsPaytype payMerchantsPaytype = new PayMerchantsPaytype();
        payMerchantsPaytype.setUpdated(new Date());
        StringBuilder builder = new StringBuilder();
        for ( int i=0; i< request.getBankcatid().length; i++ )
        {
            builder.append(request.getBankcatid()[i]+",");
        }
        String bankcatid = builder.substring(0, builder.length()-1).toString();
        payMerchantsPaytype.setBankcatid(bankcatid);
        payMerchantsPaytypeMapper.updateByExampleSelective(payMerchantsPaytype, example);
        return new JpfResponseDto();
    }

}
