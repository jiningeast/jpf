package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.ValidatorUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayBankMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsBankMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsShopMapper;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.BankInfo;
import com.joiest.jpf.entity.MerchantBankInfo;
import com.joiest.jpf.entity.MerchantInfo;
import com.joiest.jpf.facade.BankServiceFacade;
import com.joiest.jpf.facade.MerShopServiceFacade;
import com.joiest.jpf.facade.MerchantServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MerchantServiceFacadeImpl implements MerchantServiceFacade {

    private static final Logger logger = LogManager.getLogger(MerchantServiceFacadeImpl.class);

    @Autowired
    private PayMerchantsMapper payMerchantsMapper;

    @Autowired
    private PayMerchantsBankMapper payMerchantsBankMapper;

    @Autowired
    private BankServiceFacade bankServiceFacade;

    @Autowired
    private PayMerchantsShopMapper payMerchantsShopMapper;

    @Override
    public GetMerchsResponse getMerchInfoList(GetMerchsRequest request) {
        if(request.getPage()<=0){
            request.setPage(1);
        }
        if (request.getRows() <= 0) {
            request.setRows(10);
        }
        PayMerchantsExample example = new PayMerchantsExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
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
        int count = payMerchantsMapper.countByExample(example);
        List<MerchantInfo> infoList = new ArrayList<>();
        for(PayMerchants payMerchants:list){
            MerchantInfo merchantInfo = new MerchantInfo();
            BeanCopier beanCopier = BeanCopier.create(PayMerchants.class, MerchantInfo.class, false);
            beanCopier.copy(payMerchants,merchantInfo,null);
            infoList.add(merchantInfo);
        }
        GetMerchsResponse getMerchsResponse = new GetMerchsResponse();
        getMerchsResponse.setCount(count);
        getMerchsResponse.setList(infoList);
        return getMerchsResponse;
    }

    @Override
    public MerchantInfo getMerchant(Long id) {
        if (id == null) {
            logger.info("求情参数id为空");
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        PayMerchants payMerchants = payMerchantsMapper.selectByPrimaryKey(id);
        MerchantInfo merchantInfo = new MerchantInfo();
        BeanCopier beanCopier = BeanCopier.create(PayMerchants.class, MerchantInfo.class, false);
        beanCopier.copy(payMerchants,merchantInfo,null);
        return merchantInfo;
    }

    @Override
    public MerchantBankInfo getMerchBank(Long mtsid) {
        if (mtsid == null) {
            logger.info("mtsid");
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "mtsid不能为空");
        }
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

    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    @Override
    public JpfResponseDto modifyMerchant(ModifyMerchRequest request) {
        ValidatorUtils.validate(request);

        PayMerchants payMerchants = payMerchantsMapper.selectByPrimaryKey(request.getId());
        if (payMerchants == null) {
            logger.info("商户信息不存在");
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "商户信息不存在");
        }

        //商户对公帐户信息  insert or update
        PayMerchantsBankExample example1 = new PayMerchantsBankExample();
        PayMerchantsBankExample.Criteria c1 = example1.createCriteria();
        c1.andMtsidEqualTo(payMerchants.getId());
        List<PayMerchantsBank> merchantsBanks = payMerchantsBankMapper.selectByExample(example1);
        //获取银行信息
        BankInfo bankInfos = bankServiceFacade.getBankInfo(request.getBankid());
        if ( bankInfos == null )
        {
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "银行信息不正确");
        }

        if( merchantsBanks == null || merchantsBanks.isEmpty() ){
//            logger.info("商户信息-对公账户不存在");
//            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "商户对公账户信息不存在");
            //insert
            PayMerchantsBank merBankNew = new PayMerchantsBank();
            merBankNew.setMtsid(request.getId());
            merBankNew.setProvince(request.getBankProvince());
            merBankNew.setCity(request.getBankCity());
            merBankNew.setBankid(Long.parseLong(request.getBankid()));
            merBankNew.setBankname(bankInfos.getPaybankname());
            merBankNew.setBanktype(request.getBanktype());
            merBankNew.setBankno(request.getBankno());
            merBankNew.setBanksubname(request.getBanksubname());
            Date date_curr = new Date();
            merBankNew.setCreated(date_curr);
            merBankNew.setUpdated(date_curr);
            merBankNew.setMobile(request.getMobile());
            merBankNew.setChinacode(bankInfos.getBankcode());
            payMerchantsBankMapper.insert(merBankNew);
        } else
        {
            //update
            PayMerchantsBank merchantsBank = merchantsBanks.get(0);
            PayMerchantsBank merchantsBankrecord = new PayMerchantsBank();
            merchantsBankrecord.setId(merchantsBank.getId());
            merchantsBankrecord.setMtsid(request.getId());
            merchantsBankrecord.setProvince(request.getBankProvince());
            merchantsBankrecord.setCity(request.getBankCity());
            merchantsBankrecord.setStatus(request.getStatus());
            merchantsBankrecord.setBankid(Long.parseLong(request.getBankid()));
            merchantsBankrecord.setBankname(bankInfos.getPaybankname());
            merchantsBankrecord.setBanktype(request.getBanktype());
            merchantsBankrecord.setBankno(request.getBankno());
            merchantsBankrecord.setBanksubname(request.getBanksubname());
            merchantsBankrecord.setUpdated(new Date());
            merchantsBankrecord.setMobile(request.getLinkphone());
            merchantsBankrecord.setUpdated(new Date());
            merchantsBankrecord.setChinacode(bankInfos.getBankcode());
            payMerchantsBankMapper.updateByPrimaryKeySelective(merchantsBankrecord);
        }

        //添加门店信息
        byte isHeadShop = 1;
        if ( request.getIsHeadShop().equals(isHeadShop) )
        {
            //该商户的门店信息是否存在
            Long mtsid = request.getId();
            Long pid_level_0 = 0L;
            String path = request.getId() + ":";
            Integer isDel = 0;
            PayMerchantsShopExample shopE = new PayMerchantsShopExample();
            PayMerchantsShopExample.Criteria shopC = shopE.createCriteria();
            shopC.andMtsidEqualTo(mtsid);
            shopC.andIsDelEqualTo(isDel);
            List<PayMerchantsShop> shopList = payMerchantsShopMapper.selectByExample(shopE);
            if ( shopList == null || shopList.isEmpty() )
            {
                this.addShop(mtsid, pid_level_0, path);
            } else
            {
                //之前为分店，删除&重新插入 ; 之前总店，不操作
                PayMerchantsShop shopOne = shopList.get(0);
                if ( !shopOne.getPid().equals(pid_level_0) )
                {
                    payMerchantsShopMapper.deleteByPrimaryKey(shopOne.getId());
                    this.addShop(mtsid,pid_level_0, path);
                }
            }
        }
        //更新商户信息
        PayMerchants merchantsRecord = new PayMerchants();
        BeanCopier beanCopier = BeanCopier.create(ModifyMerchRequest.class, PayMerchants.class, false);
        beanCopier.copy(request, merchantsRecord, null);
        merchantsRecord.setId(payMerchants.getId());
        payMerchantsMapper.updateByPrimaryKeySelective(merchantsRecord);

        return new JpfResponseDto();
    }

    //添加总店信息
    private int addShop(Long mtsid, Long pid, String path)
    {
        //添加pay_merchant_shop 总店信息
        PayMerchantsShop shop = new PayMerchantsShop();
        shop.setMtsid(mtsid);
        shop.setPid(pid);
        shop.setPath(path);
        shop.setIsDel(0);
        Date date = new Date();
        shop.setCreated(date);
        shop.setUpdated(date);
        int res = payMerchantsShopMapper.insertSelective(shop);
        return res;
    }

    @Override
    public JpfResponseDto auditMerchant(AuditMerchRequest request) {
        ValidatorUtils.validate(request);

        PayMerchants merchant = payMerchantsMapper.selectByPrimaryKey(request.getId());
        if(merchant==null){
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "商户信息不存在");
        }

        PayMerchants merchantsRecord = new PayMerchants();
        merchantsRecord.setId(request.getId());
        merchantsRecord.setAttestation(request.getAttestation());
        merchantsRecord.setContent(request.getContent());
        payMerchantsMapper.updateByPrimaryKeySelective(merchantsRecord);
        return new JpfResponseDto();
    }

}
