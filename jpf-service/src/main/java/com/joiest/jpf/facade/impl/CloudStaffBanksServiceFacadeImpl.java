package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudStaffBanks;
import com.joiest.jpf.common.po.PayCloudStaffBanksExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudStaffBanksMapper;
import com.joiest.jpf.entity.CloudStaffBanksInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigInteger;
import java.util.List;

public class CloudStaffBanksServiceFacadeImpl {

    @Autowired
    private PayCloudStaffBanksMapper payCloudStaffBanksMapper;
    /**
     * 通过银行卡号、员工号获取员工银行卡信息
     * */
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
        BeanCopier beanCopier = BeanCopier.create( PayCloudStaffBanks.class, CloudStaffBanksInfo.class, false);
        beanCopier.copy(payCloudStaffBanks, cloudStaffBanksInfo, null);

        return cloudStaffBanksInfo;
    }
    /**
     * 通过员工号、手机号获取员工银行卡信息
     * */
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
}
