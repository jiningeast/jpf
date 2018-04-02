package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayMerchants;
import com.joiest.jpf.common.po.PayMerchantsBank;
import com.joiest.jpf.common.po.PayMerchantsBankExample;
import com.joiest.jpf.common.po.PayMerchantsExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsBankMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsMapper;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.MerchantBankInfo;
import com.joiest.jpf.entity.MerchantInfo;
import com.joiest.jpf.facade.MerchantServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MerchantServiceFacadeImpl implements MerchantServiceFacade {

    @Autowired
    private PayMerchantsMapper payMerchantsMapper;

    @Autowired
    private PayMerchantsBankMapper payMerchantsBankMapper;

    @Override
    public GetMerchsResponse getMerchInfoList(GetMerchsRequest request) {
        if(request.getPageNo()<=0){
            request.setPageNo(1);
        }
        if (request.getPageSize() <= 0) {
            request.setPageSize(20);
        }
        PayMerchantsExample example = new PayMerchantsExample();
        example.setPageNo(request.getPageNo());
        example.setPageSize(request.getPageSize());
        example.setOrderByClause("ADDTIME DESC");
        PayMerchantsExample.Criteria c = example.createCriteria();
        if(StringUtils.isNotBlank(request.getMerchNo())){
            c.andMerchNoEqualTo(request.getMerchNo());
        }
        if(StringUtils.isNotBlank(request.getMerchName())){
            c.andMerchNameLike("%"+request.getMerchName()+"%");
        }
        if(StringUtils.isNotBlank(request.getUsername())){
            c.andUsernameEqualTo(request.getUsername());
        }
        if (StringUtils.isNotBlank(request.getCompanyname())) {
            c.andCompanynameLike("%" + request.getCompanyname() + "%");
        }
        if(StringUtils.isNotBlank(request.getStartAddTime())&&StringUtils.isNotBlank(request.getEndAddTime())){
            c.andAddtimeBetween(DateUtils.getFdate(request.getStartAddTime(),DateUtils.DATEFORMATSHORT),DateUtils.addInteger(request.getEndAddTime(),5,1,DateUtils.DATEFORMATSHORT));
        }
        if(request.getStatus()!=null){
            c.andStatusEqualTo(request.getStatus());
        }
        if (StringUtils.isNotBlank(request.getBslicense())) {
            c.andBslicenseEqualTo(request.getBslicense());
        }
        if(request.getAttestation()!=null){
            c.andAttestationEqualTo(request.getAttestation());
        }
        if (request.getMuserid() != null) {
            c.andMuseridEqualTo(request.getMuserid());
        }

        List<PayMerchants> list = payMerchantsMapper.selectByExample(example);
        example.setPageNo(0);
        example.setPageSize(0);
        int count = payMerchantsMapper.countByExample(example);
        List<MerchantInfo> infoList = new ArrayList<>();
        for(PayMerchants payMerchants:list){
            MerchantInfo merchantInfo = new MerchantInfo();
            BeanCopier beanCopier = BeanCopier.create(PayMerchants.class, MerchantInfo.class, false);
            beanCopier.copy(payMerchants,merchantInfo,null);
        }
        GetMerchsResponse getMerchsResponse = new GetMerchsResponse();
        getMerchsResponse.setCount(count);
        getMerchsResponse.setList(infoList);
        return getMerchsResponse;
    }

    @Override
    public MerchantInfo getMerchant(String merchNo) {
        PayMerchantsExample example = new PayMerchantsExample();
        PayMerchantsExample.Criteria c = example.createCriteria();
        c.andMerchNoEqualTo(merchNo);
        List<PayMerchants> list = payMerchantsMapper.selectByExample(example);
        if (list == null || list.isEmpty()) {
            return null;
        }
        PayMerchants payMerchants = list.get(0);
        MerchantInfo merchantInfo = new MerchantInfo();
        BeanCopier beanCopier = BeanCopier.create(PayMerchants.class, MerchantInfo.class, false);
        beanCopier.copy(payMerchants,merchantInfo,null);
        return merchantInfo;
    }

    @Override
    public MerchantBankInfo getMerchBank(Long mtsid) {
        PayMerchantsBankExample example = new PayMerchantsBankExample();
        PayMerchantsBankExample.Criteria c = example.createCriteria();
        c.andMtsidEqualTo(mtsid);
        List<PayMerchantsBank> list = payMerchantsBankMapper.selectByExample(example);
        if(list==null||list.isEmpty()){
            return null;
        }
        PayMerchantsBank payMerchantsBank = list.get(0);
        MerchantBankInfo merchantBankInfo = new MerchantBankInfo();
        BeanCopier beanCopier = BeanCopier.create(PayMerchantsBank.class, MerchantBankInfo.class, false);
        beanCopier.copy(payMerchantsBank, merchantBankInfo, null);
        return merchantBankInfo;
    }

    @Override
    @Transactional
    public JpfResponseDto addMerchant(AddMerchRequest request) {
        PayMerchantsExample example = new PayMerchantsExample();
        PayMerchantsExample.Criteria c = example.createCriteria();
        c.andMerchNoEqualTo(request.getMerchNo());
        List<PayMerchants> list = payMerchantsMapper.selectByExample(example);
        if (list != null && !list.isEmpty()) {
            setResponse("9999","商户信息已经存在");
        }
        example.clear();
        c.andMerchNameEqualTo(request.getMerchName());
        list = payMerchantsMapper.selectByExample(example);
        if (list != null && !list.isEmpty()) {
            setResponse("9999","商户信息已经存在");
        }
        example.clear();
        c.andCompanynameEqualTo(request.getCompanyname());
        list = payMerchantsMapper.selectByExample(example);
        if (list != null && !list.isEmpty()) {
            setResponse("9999","商户信息已经存在");
        }

        PayMerchants merchantsRecord = new PayMerchants();
        BeanCopier beanCopier = BeanCopier.create(AddMerchRequest.class, PayMerchants.class, false);
        beanCopier.copy(request, merchantsRecord, null);
        merchantsRecord.setAddtime(new Date());
        payMerchantsMapper.insertSelective(merchantsRecord);

        PayMerchantsBank merchantsBankrecord = new PayMerchantsBank();
        BeanCopier beanCopier1 = BeanCopier.create(AddMerchRequest.class, PayMerchantsBank.class, false);
        beanCopier1.copy(request,merchantsBankrecord,null);
        merchantsBankrecord.setCreated(new Date());
        payMerchantsBankMapper.insertSelective(merchantsBankrecord);
        return setResponse("0000", "操作完成");
    }

    @Override
    public JpfResponseDto modifyMerchant(ModifyMerchRequest request) {
        PayMerchantsExample example = new PayMerchantsExample();
        PayMerchantsExample.Criteria c = example.createCriteria();
        c.andMerchNoEqualTo(request.getMerchNo());
        List<PayMerchants> list = payMerchantsMapper.selectByExample(example);
        if (list == null || list.isEmpty()) {
            return setResponse("9999","商户信息不存在");
        }
        PayMerchants merchant = list.get(0);

        PayMerchantsBankExample example1 = new PayMerchantsBankExample();
        PayMerchantsBankExample.Criteria c1 = example1.createCriteria();
        c1.andMtsidEqualTo(merchant.getId());
        List<PayMerchantsBank> merchantsBanks = payMerchantsBankMapper.selectByExample(example1);
        if(merchantsBanks==null||merchantsBanks.isEmpty()){
            return setResponse("9999","商户信息不存在");
        }
        PayMerchantsBank merchantsBank = merchantsBanks.get(0);

        PayMerchants merchantsRecord = new PayMerchants();
        BeanCopier beanCopier = BeanCopier.create(AddMerchRequest.class, PayMerchants.class, false);
        beanCopier.copy(request, merchantsRecord, null);
        merchantsRecord.setAddtime(new Date());
        merchantsRecord.setId(merchant.getId());
        payMerchantsMapper.updateByPrimaryKeySelective(merchantsRecord);

        PayMerchantsBank merchantsBankrecord = new PayMerchantsBank();
        BeanCopier beanCopier1 = BeanCopier.create(AddMerchRequest.class, PayMerchantsBank.class, false);
        beanCopier1.copy(request,merchantsBankrecord,null);
        merchantsBankrecord.setCreated(new Date());
        merchantsBankrecord.setId(merchantsBank.getId());
        payMerchantsBankMapper.updateByPrimaryKeySelective(merchantsBankrecord);
        return setResponse("0000", "操作完成");
    }

    @Override
    public JpfResponseDto auditMerchant(AuditMerchRequest request) {
        PayMerchants merchant = payMerchantsMapper.selectByPrimaryKey(request.getId());
        if(merchant==null){
            return setResponse("9999","商户信息不存在");
        }
        PayMerchants merchantsRecord = new PayMerchants();
        merchantsRecord.setId(request.getId());
        merchantsRecord.setAttestation(request.getAttestation());
        payMerchantsMapper.updateByPrimaryKeySelective(merchantsRecord);
        return setResponse("0000", "操作完成");
    }

    private JpfResponseDto setResponse(String errorCode,String errorMsg) {
        JpfResponseDto jpfResponseDto = new JpfResponseDto();
        if(StringUtils.isNotBlank(errorCode)&&!errorCode.equals("0000")){
            jpfResponseDto.setResponseError(errorCode,errorMsg);
        }
        return jpfResponseDto;
    }
}
