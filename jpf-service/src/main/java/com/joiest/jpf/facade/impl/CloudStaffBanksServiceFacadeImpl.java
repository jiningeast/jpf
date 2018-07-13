package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudCompanyStaff;
import com.joiest.jpf.common.po.PayCloudStaffBanks;
import com.joiest.jpf.common.po.PayCloudStaffBanksExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudStaffBanksMapper;
import com.joiest.jpf.entity.CloudStaffBanksInfo;
import com.joiest.jpf.facade.CloudStaffBanksServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigInteger;
import java.util.List;

public class CloudStaffBanksServiceFacadeImpl implements CloudStaffBanksServiceFacade {

    @Autowired
    private PayCloudStaffBanksMapper payCloudStaffBanksMapper;

    /**
     * 通过银行卡号、员工号获取员工银行卡信息
     * */
    @Override
    public CloudStaffBanksInfo getStaffBankByNumSid(String bankNum, BigInteger id){

        PayCloudStaffBanksExample example = new PayCloudStaffBanksExample();

        PayCloudStaffBanksExample.Criteria c = example.createCriteria();
        c.andBanknoEqualTo(bankNum);
        c.andStaffidEqualTo(new Long(String.valueOf(id)));

        List<PayCloudStaffBanks> getStaffBankInfo = payCloudStaffBanksMapper.selectByExample(example);
        if(getStaffBankInfo == null || getStaffBankInfo.isEmpty()){

            return null;
        }
        PayCloudStaffBanks payCloudStaffBanks = getStaffBankInfo.get(0);

        CloudStaffBanksInfo cloudStaffBanksInfo = new CloudStaffBanksInfo();
        if ( !getStaffBankInfo.isEmpty() ){
            BeanCopier beanCopier = BeanCopier.create( PayCloudStaffBanks.class, CloudStaffBanksInfo.class, false);
            beanCopier.copy(payCloudStaffBanks, cloudStaffBanksInfo, null);
        }

        return cloudStaffBanksInfo;
    }

    /**
     * 通过员工号、手机号获取员工银行卡信息
     * */
    @Override
    public CloudStaffBanksInfo getStaffBankBySidPhone(String id,String mobile){

        PayCloudStaffBanksExample example = new PayCloudStaffBanksExample();

        PayCloudStaffBanksExample.Criteria c = example.createCriteria();
        c.andBankphoneEqualTo(mobile);
        c.andStaffidEqualTo(new Long(String.valueOf(id)));

        List<PayCloudStaffBanks> getStaffBankInfo = payCloudStaffBanksMapper.selectByExample(example);
        if(getStaffBankInfo == null || getStaffBankInfo.isEmpty()){

            return null;
        }
        PayCloudStaffBanks payCloudStaffBanks = getStaffBankInfo.get(0);

        CloudStaffBanksInfo cloudStaffBanksInfo = new CloudStaffBanksInfo();
        BeanCopier beanCopier = BeanCopier.create( PayCloudStaffBanks.class, CloudStaffBanksInfo.class, false);
        beanCopier.copy(payCloudStaffBanks, cloudStaffBanksInfo, null);

        return cloudStaffBanksInfo;
    }

    /**
     * 插入员工银行卡信息
     */
    @Override
    public int addStaffBank(CloudStaffBanksInfo cloudStaffBanksInfo){
        PayCloudStaffBanks payCloudStaffBanks = new PayCloudStaffBanks();
        BeanCopier beanCopier = BeanCopier.create( CloudStaffBanksInfo.class, PayCloudStaffBanks.class, false);
        beanCopier.copy(cloudStaffBanksInfo, payCloudStaffBanks, null);

        return payCloudStaffBanksMapper.insert(payCloudStaffBanks);
    }
}
