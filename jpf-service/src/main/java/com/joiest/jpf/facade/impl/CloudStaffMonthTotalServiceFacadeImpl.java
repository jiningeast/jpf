package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudStaffMonthTotal;
import com.joiest.jpf.common.po.PayCloudStaffMonthTotalExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudStaffMonthTotalMapper;
import com.joiest.jpf.dto.ModifyCloudStaffMonthTotalRequest;
import com.joiest.jpf.entity.CloudStaffMonthTotalInterfaceInfo;
import com.joiest.jpf.facade.CloudStaffMonthTotalServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class CloudStaffMonthTotalServiceFacadeImpl implements CloudStaffMonthTotalServiceFacade {

    @Autowired
    private PayCloudStaffMonthTotalMapper payCloudStaffMonthTotalMapper;

    @Override
    public CloudStaffMonthTotalInterfaceInfo getStaffMonthTotal(String month, Long busstaffid)
    {
        PayCloudStaffMonthTotalExample example = new PayCloudStaffMonthTotalExample();
        PayCloudStaffMonthTotalExample.Criteria c = example.createCriteria();
        c.andMonthEqualTo(month);
        c.andBusstaffidEqualTo(busstaffid);
        List<PayCloudStaffMonthTotal> list = payCloudStaffMonthTotalMapper.selectByExample(example);
        if ( list.isEmpty() || list == null )
        {
            return null;
        }
        CloudStaffMonthTotalInterfaceInfo info = new CloudStaffMonthTotalInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create(PayCloudStaffMonthTotal.class, CloudStaffMonthTotalInterfaceInfo.class, false);
        beanCopier.copy(list.get(0), info, null);

        return info;
    }

    @Override
    public int addStaffMonthTotal(ModifyCloudStaffMonthTotalRequest request) {
        PayCloudStaffMonthTotal payCloudStaffMonthTotal = new PayCloudStaffMonthTotal();
        BeanCopier beanCopier = BeanCopier.create(ModifyCloudStaffMonthTotalRequest.class, PayCloudStaffMonthTotal.class, false);
        beanCopier.copy(request, payCloudStaffMonthTotal, null);

        return payCloudStaffMonthTotalMapper.insertSelective(payCloudStaffMonthTotal);
    }

    @Override
    public int updateStaffMonthTotal(ModifyCloudStaffMonthTotalRequest request) {
        PayCloudStaffMonthTotal payCloudStaffMonthTotal = new PayCloudStaffMonthTotal();
        BeanCopier beanCopier = BeanCopier.create(ModifyCloudStaffMonthTotalRequest.class, PayCloudStaffMonthTotal.class, false);
        beanCopier.copy(request, payCloudStaffMonthTotal, null);
        PayCloudStaffMonthTotalExample example = new PayCloudStaffMonthTotalExample();
        PayCloudStaffMonthTotalExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(request.getId());

        return payCloudStaffMonthTotalMapper.updateByExampleSelective(payCloudStaffMonthTotal, example);
    }
}
