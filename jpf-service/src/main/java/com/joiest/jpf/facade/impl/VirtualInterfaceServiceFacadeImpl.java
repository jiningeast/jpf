package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayVirtual;
import com.joiest.jpf.common.po.PayVirtualExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayVirtualMapper;
import com.joiest.jpf.entity.VirtualInfo;
import com.joiest.jpf.facade.VirtualInterfaceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class VirtualInterfaceServiceFacadeImpl implements VirtualInterfaceServiceFacade {

    @Autowired
    private PayVirtualMapper payVirtualMapper;

    /**
     * 根据关联ID获取分期费率信息
     */
    @Override
    public VirtualInfo getInfoByRelateId(Integer relateId)
    {
        PayVirtualExample example = new PayVirtualExample();
        PayVirtualExample.Criteria c = example.createCriteria();
        c.andRelateIdEqualTo(relateId);
        List<PayVirtual> list = payVirtualMapper.selectByExample(example);
        VirtualInfo info = new VirtualInfo();
        if ( list == null || list.isEmpty() )
        {
            return info;
        }
        BeanCopier beanCopier = BeanCopier.create(PayVirtual.class, VirtualInfo.class, false);
        beanCopier.copy(list.get(0), info, null);
        return info;
    }
}
