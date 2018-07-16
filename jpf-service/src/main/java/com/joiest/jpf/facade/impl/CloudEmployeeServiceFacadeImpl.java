package com.joiest.jpf.facade.impl;

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
    private PayCloudEmployeeMapper payCloudEmployeeMapper;

    /**
     * 获取公司登录信息通过邮箱
     **/
    public CloudEmployeeInfo getCompayLoginInfoByEmail(String email){

        PayCloudEmployeeExample example = new PayCloudEmployeeExample();
        PayCloudEmployeeExample.Criteria c = example.createCriteria();

        c.andLinkemailEqualTo(email);

        List<PayCloudEmployee> payCloudEmployee = payCloudEmployeeMapper.selectByExample(example);
        if(payCloudEmployee == null || payCloudEmployee.isEmpty()) return null;

        PayCloudEmployee payCloudEmployee1 = payCloudEmployee.get(0);

        CloudEmployeeInfo cloudEmployeeInfo = new CloudEmployeeInfo();

        BeanCopier beanCopier = BeanCopier.create(PayCloudEmployee.class,CloudEmployeeInfo.class,false);
        beanCopier.copy(payCloudEmployee1,cloudEmployeeInfo,null);

        return cloudEmployeeInfo;
    }
    /*
    * 获取公司登录信息通过主键id
    * */
    public CloudEmployeeInfo getCompayEmployeeByUid(Integer uid){

        PayCloudEmployee payCloudEmployee = payCloudEmployeeMapper.selectByPrimaryKey(uid);

        if(payCloudEmployee == null) return null;

        CloudEmployeeInfo cloudEmployeeInfo = new CloudEmployeeInfo();

        BeanCopier beanCopier = BeanCopier.create(PayCloudEmployee.class,CloudEmployeeInfo.class,false);
        beanCopier.copy(payCloudEmployee,cloudEmployeeInfo,null);
        
        return cloudEmployeeInfo;
    }
}
