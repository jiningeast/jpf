package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayTdorder;
import com.joiest.jpf.common.po.PayTdorderExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayTdorderMapper;
import com.joiest.jpf.entity.TdorderInfo;
import com.joiest.jpf.facade.TdorderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class TdorderServiceFacadeImpl implements TdorderServiceFacade {

    @Autowired
    private PayTdorderMapper payTdorderMapper;

    @Override
    public int getTdordersCount(){
        PayTdorderExample e = new PayTdorderExample();
        return payTdorderMapper.countByExample(e);
    }

    @Override
    public List<TdorderInfo> getTdorders(long page, long rows){
        PayTdorderExample e = new PayTdorderExample();
        e.setPageNo(page);
        e.setPageSize(rows);
        List<PayTdorder> list = payTdorderMapper.selectByExample(e);
        List<TdorderInfo> infos = new ArrayList<>();
        for (PayTdorder payTdorder:list){
            TdorderInfo tdorderInfo = new TdorderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayTdorder.class, TdorderInfo.class, false);
            beanCopier.copy(payTdorder, tdorderInfo, null);
            infos.add(tdorderInfo);
        }

        return infos;
    }
}
