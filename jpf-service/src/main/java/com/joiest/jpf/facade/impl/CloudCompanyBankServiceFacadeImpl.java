package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayCloudCompanyBank;
import com.joiest.jpf.common.po.PayCloudCompanyBankExample;
import com.joiest.jpf.common.po.PayCloudEmployee;
import com.joiest.jpf.common.po.PayCloudEmployeeExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompanyBankMapper;
import com.joiest.jpf.entity.CloudCompanyBankInfo;
import com.joiest.jpf.entity.CloudEmployeeInfo;
import com.joiest.jpf.facade.CloudCompanyBankServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class CloudCompanyBankServiceFacadeImpl implements CloudCompanyBankServiceFacade {
    @Autowired
    PayCloudCompanyBankMapper payCloudCompanyBankMapper;
    public CloudCompanyBankInfo getCompanyBankInfoByMerchNo(String merchNo){
        PayCloudCompanyBankExample example= new PayCloudCompanyBankExample();
        PayCloudCompanyBankExample.Criteria c = example.createCriteria();
        c.andMerchNoEqualTo(merchNo);
        List<PayCloudCompanyBank> cloudCompanyList = payCloudCompanyBankMapper.selectByExample(example);
        if(cloudCompanyList.size() != 1 || cloudCompanyList == null){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "无效商户号");
        }

        CloudCompanyBankInfo cloudCompanyBankRep = new CloudCompanyBankInfo();
        for (PayCloudCompanyBank one : cloudCompanyList)
        {
            BeanCopier beanCopier = BeanCopier.create(PayCloudCompanyBank.class, CloudCompanyBankInfo.class, false);
            beanCopier.copy(one, cloudCompanyBankRep, null);
        }

        return cloudCompanyBankRep;
    }
}
