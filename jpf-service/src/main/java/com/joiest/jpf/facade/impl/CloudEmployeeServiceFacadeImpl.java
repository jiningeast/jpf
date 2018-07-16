package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayCloudEmployee;
import com.joiest.jpf.common.po.PayCloudEmployeeExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudEmployeeMapper;
import com.joiest.jpf.entity.CloudEmployeeInfo;
import com.joiest.jpf.facade.CloudEmployeeServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class CloudEmployeeServiceFacadeImpl implements CloudEmployeeServiceFacade {

    @Autowired
    PayCloudEmployeeMapper payCloudEmployeeMapper;

    public CloudEmployeeInfo getEmployeeInfoByMerchNo(String merchNo){
        PayCloudEmployeeExample example= new PayCloudEmployeeExample();
        PayCloudEmployeeExample.Criteria c = example.createCriteria();
        c.andMerchNoEqualTo(merchNo);
        List<PayCloudEmployee> employeeInfoList = payCloudEmployeeMapper.selectByExample(example);
        if(employeeInfoList.size() != 1 || employeeInfoList == null){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "无效商户号");
        }

        CloudEmployeeInfo cloudEmployeeRep = new CloudEmployeeInfo();
        for (PayCloudEmployee one : employeeInfoList)
        {
            BeanCopier beanCopier = BeanCopier.create(PayCloudEmployee.class, CloudEmployeeInfo.class, false);
            beanCopier.copy(one, cloudEmployeeRep, null);
        }

        return cloudEmployeeRep;
    }
}
