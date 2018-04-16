package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PaySystemlog;
import com.joiest.jpf.common.po.PaySystemlogExample;
import com.joiest.jpf.dao.repository.mapper.generate.PaySystemlogMapper;
import com.joiest.jpf.entity.SystemlogInfo;
import com.joiest.jpf.facade.SystemlogServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class SystemlogServiceFacadeImpl implements SystemlogServiceFacade {

    @Autowired
    private PaySystemlogMapper paySystemlogMapper;

    @Override
    public Integer getCount(){
        PaySystemlogExample e = new PaySystemlogExample();
        Integer count =paySystemlogMapper.countByExample(e);

        return count;
    }

    @Override
    public List<SystemlogInfo> getLogs( long page, long rows ){
        PaySystemlogExample e = new PaySystemlogExample();
        e.setPageNo(page);
        e.setPageSize(rows);
        List<PaySystemlog> list = paySystemlogMapper.selectByExampleWithBLOBs(e);
        List<SystemlogInfo> infos = new ArrayList<>();
        for (PaySystemlog paySystemlog : list) {
            SystemlogInfo info = new SystemlogInfo();
            BeanCopier beanCopier = BeanCopier.create(PaySystemlog.class, SystemlogInfo.class,false);
            beanCopier.copy( paySystemlog, info, null );

            infos.add(info);
        }

        return infos;
    }
}
