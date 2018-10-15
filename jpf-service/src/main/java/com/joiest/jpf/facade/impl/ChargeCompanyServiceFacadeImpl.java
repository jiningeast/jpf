package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayChargeCompany;
import com.joiest.jpf.common.po.PayChargeCompanyExample;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeCompanyMapper;
import com.joiest.jpf.dto.GetChargeCompanyRequest;
import com.joiest.jpf.dto.GetChargeCompanyResponse;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChargeCompanyServiceFacadeImpl implements ChargeCompanyServiceFacade {

    @Autowired
    private PayChargeCompanyMapper payChargeCompanyMapper;

    /**
     * 获取商户列表
     */
    @Override
    public GetChargeCompanyResponse getRecords(GetChargeCompanyRequest request){
        GetChargeCompanyResponse response = new GetChargeCompanyResponse();

        PayChargeCompanyExample e = new PayChargeCompanyExample();
        PayChargeCompanyExample.Criteria c = e.createCriteria();
        c.andIsDelEqualTo((byte)0);
        if (StringUtils.isNotBlank(""+request.getPage()) && request.getPage() > 0 ){
            e.setPageNo(request.getPage());
        }else{
            e.setPageNo(1);
        }
        if (StringUtils.isNotBlank(""+request.getRows()) && request.getRows() > 0 ){
            e.setPageSize(request.getRows());
        }else{
            e.setPageSize(10);
        }
        if ( request.getMerchNo() != null && StringUtils.isNotBlank(request.getMerchNo()) ){
            c.andMerchNoEqualTo(request.getMerchNo());
        }
        if ( request.getCompanyName() != null && StringUtils.isNotBlank(request.getCompanyName()) ){
            c.andCompanyNameLike("%"+request.getCompanyName()+"%");
        }
        if ( request.getIsFreeze() != null && request.getIsFreeze() == 1 ){
            c.andIsFreezeEqualTo((byte)0);
        }else if ( request.getIsFreeze() != null && request.getIsFreeze() == 2 ){
            c.andIsFreezeEqualTo((byte)1);
        }
        e.setOrderByClause("id DESC");

        List<PayChargeCompany> list =  payChargeCompanyMapper.selectByExample(e);
        response.setCount(payChargeCompanyMapper.countByExample(e));
        List<ChargeCompanyInfo> infos = new ArrayList<>();
        for (PayChargeCompany one:list){
            ChargeCompanyInfo info = new ChargeCompanyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayChargeCompany.class,ChargeCompanyInfo.class,false);
            beanCopier.copy(one,info,null);

            infos.add(info);
        }
        response.setList(infos);

        return response;
    }

    /**
     * 商品信息
     * */
    @Override
    public ChargeCompanyInfo getOne(ChargeCompanyInfo companyInfo){

        PayChargeCompanyExample example = new PayChargeCompanyExample();
        PayChargeCompanyExample.Criteria c = example.createCriteria();
        if(companyInfo.getMerchNo() != null ){
            c.andMerchNoEqualTo(companyInfo.getMerchNo());
        }

        List<PayChargeCompany> list = payChargeCompanyMapper.selectByExample(example);
        if( list == null || list.size() <=0  ){
            return null;
        }
        ChargeCompanyInfo info = new ChargeCompanyInfo();
        BeanCopier beanCopier = BeanCopier.create(PayChargeCompany.class,ChargeCompanyInfo.class,false);
        beanCopier.copy(list.get(0),info,null);
        return info;
    }

    /**
     * 根据主键id获取商户
     */
    @Override
    public ChargeCompanyInfo getRecordByPrimaryKey(String id){
        PayChargeCompany payChargeCompany = payChargeCompanyMapper.selectByPrimaryKey(id);
        ChargeCompanyInfo chargeCompanyInfo = new ChargeCompanyInfo();
        if ( payChargeCompany != null ){
            BeanCopier beanCopier = BeanCopier.create(PayChargeCompany.class,ChargeCompanyInfo.class,false);
            beanCopier.copy(payChargeCompany,chargeCompanyInfo,null);
        }

        return chargeCompanyInfo;
    }

    /**
     * 添加商户
     */
    @Override
    public int addRecord(ChargeCompanyInfo chargeCompanyInfo){
        PayChargeCompany payChargeCompany = new PayChargeCompany();
        if ( chargeCompanyInfo != null ){
            BeanCopier beanCopier = BeanCopier.create(ChargeCompanyInfo.class,PayChargeCompany.class,false);
            beanCopier.copy(chargeCompanyInfo,payChargeCompany,null);
        }
        String merchNo = "MC"+System.currentTimeMillis()+ToolUtils.getRandomInt(100000,999999);     // 生成商户号
        String privateKey = ToolUtils.createPrivateKey(16);     // 生成秘钥
        payChargeCompany.setMerchNo(merchNo);
        payChargeCompany.setPrivateKey(privateKey);
        payChargeCompany.setMoney(new BigDecimal(0));
        payChargeCompany.setIsDel((byte)0);
        payChargeCompany.setAddtime(new Date());

        return payChargeCompanyMapper.insertSelective(payChargeCompany);
    }

    /**
     * 更新商户
     */
    @Override
    public int updateColumnByPrimaryKey(ChargeCompanyInfo chargeCompanyInfo){
        PayChargeCompany payChargeCompany = new PayChargeCompany();
        if ( chargeCompanyInfo != null ){
            BeanCopier beanCopier = BeanCopier.create(ChargeCompanyInfo.class,PayChargeCompany.class,false);
            beanCopier.copy(chargeCompanyInfo,payChargeCompany,null);
        }

        return payChargeCompanyMapper.updateByPrimaryKeySelective(payChargeCompany);
    }
}
