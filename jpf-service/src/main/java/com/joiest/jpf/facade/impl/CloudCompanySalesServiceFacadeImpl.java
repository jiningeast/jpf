package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudCompanySales;
import com.joiest.jpf.common.po.PayCloudCompanySalesExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompanySalesMapper;
import com.joiest.jpf.entity.CloudCompanySalesInfo;
import com.joiest.jpf.facade.CloudCompanySalesServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class CloudCompanySalesServiceFacadeImpl implements CloudCompanySalesServiceFacade {

    @Autowired
    private PayCloudCompanySalesMapper payCloudCompanySalesMapper;

    /**
     * 根据商户号获取业务公司
     */
    public CloudCompanySalesInfo getSalesBySalesNo(String salesNo){
        PayCloudCompanySalesExample e = new PayCloudCompanySalesExample();
        PayCloudCompanySalesExample.Criteria c = e.createCriteria();
        c.andSalesNoEqualTo(salesNo);
        CloudCompanySalesInfo cloudCompanySalesInfo = new CloudCompanySalesInfo();
        List<PayCloudCompanySales> list = payCloudCompanySalesMapper.selectByExample(e);

        BeanCopier beanCopier = BeanCopier.create( PayCloudCompanySales.class, CloudCompanySalesInfo.class, false);
        beanCopier.copy(list.get(0), cloudCompanySalesInfo, null);

        return cloudCompanySalesInfo;
    }
}
