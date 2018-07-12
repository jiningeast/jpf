package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayCloudCompactStaffInterfaceCustom;
import com.joiest.jpf.common.po.PayCloudCompactStaff;
import com.joiest.jpf.common.po.PayCloudCompactStaffExample;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudCompactStaffInterfaceCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompactStaffMapper;
import com.joiest.jpf.entity.CloudCompactStaffInterfaceCustomInfo;
import com.joiest.jpf.facade.CloudCompactStaffInterfaceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class CloudCompactStaffInterfaceServiceFacadeImpl implements CloudCompactStaffInterfaceServiceFacade {

    @Autowired
    private PayCloudCompactStaffMapper payCloudCompactStaffMapper;

    @Autowired
    private PayCloudCompactStaffInterfaceCustomMapper payCloudCompactStaffInterfaceCustomMapper;

    @Override
    public List<CloudCompactStaffInterfaceCustomInfo> getUserCompactList(Long uid, byte compactActive)
    {
        if ( uid == null )
        {
            return  null;
        }
        PayCloudCompactStaffExample example = new PayCloudCompactStaffExample();
        PayCloudCompactStaffExample.Criteria c = example.createCriteria();
        c.andStaffidEqualTo(uid);
        if ( compactActive != 0 )
        {
            c.andCompactActiveEqualTo(compactActive);
        }

        List<PayCloudCompactStaff> list = payCloudCompactStaffMapper.selectByExample(example);
        if ( list == null || list.isEmpty() )
        {
            return  null;
        }
        List<CloudCompactStaffInterfaceCustomInfo> resultList = new ArrayList<>();
        for ( PayCloudCompactStaff one : list )
        {
            CloudCompactStaffInterfaceCustomInfo info = new CloudCompactStaffInterfaceCustomInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudCompactStaff.class, CloudCompactStaffInterfaceCustomInfo.class, false);
            beanCopier.copy(one, info, null);
            resultList.add(info);
        }
        return resultList;
    }

    @Override
    public List<CloudCompactStaffInterfaceCustomInfo> getUserCompactListCustom(Long uid, byte compactActive)
    {
        if ( uid == null )
        {
            return  null;
        }
        PayCloudCompactStaffExample example = new PayCloudCompactStaffExample();
        if ( compactActive == 1 )
        {
            example.setOrderByClause("updated DESC");
        } else if ( compactActive == 0 )
        {
            example.setOrderByClause("created ASC");
        }

        PayCloudCompactStaffExample.Criteria c = example.createCriteria();
        c.andStaffidEqualTo(uid);
        if ( compactActive == 0 || compactActive == 1 )
        {
            c.andCompactActiveEqualTo(compactActive);
        }

        List<PayCloudCompactStaffInterfaceCustom> list = payCloudCompactStaffInterfaceCustomMapper.getUserCompactListCustom(example);
        if ( list == null || list.isEmpty() )
        {
            return  null;
        }
        List<CloudCompactStaffInterfaceCustomInfo> resultList = new ArrayList<>();
        for ( PayCloudCompactStaffInterfaceCustom one : list )
        {
            CloudCompactStaffInterfaceCustomInfo info = new CloudCompactStaffInterfaceCustomInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudCompactStaffInterfaceCustom.class, CloudCompactStaffInterfaceCustomInfo.class, false);
            beanCopier.copy(one, info, null);
            resultList.add(info);
        }
        return resultList;
    }

}
