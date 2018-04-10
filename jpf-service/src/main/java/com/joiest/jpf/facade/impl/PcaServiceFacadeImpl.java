package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayPca;
import com.joiest.jpf.common.po.PayPcaExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayPcaMapper;
import com.joiest.jpf.entity.PcaInfo;
import com.joiest.jpf.facade.PcaServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class PcaServiceFacadeImpl implements PcaServiceFacade {

    @Autowired
    private PayPcaMapper payPcaMapper;

    @Override
    public List<PcaInfo> getPcas(String pid) {

        PayPcaExample example = new PayPcaExample();
        PayPcaExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(pid)) {
            criteria.andPidEqualTo(Integer.valueOf(pid));
        }else{
            criteria.andPidEqualTo(0);
        }
        List<PayPca> pcas = payPcaMapper.selectByExample(example);
        List<PcaInfo> pcaInfos = new ArrayList<>();
        for (PayPca payPca : pcas) {
            PcaInfo pcaInfo = new PcaInfo();
            BeanCopier beanCopier = BeanCopier.create(PayPca.class, PcaInfo.class, false);
            beanCopier.copy(payPca, pcaInfo, null);
            pcaInfos.add(pcaInfo);
        }
        return pcaInfos;
    }

    @Override
    public List<PcaInfo> getPca(long page, long pageSize) {
        PayPcaExample example = new PayPcaExample();
        if ( page <= 0 )
        {
            page = 1;
        }else
        {
            example.setPageNo(page);
        }
        if ( pageSize <= 0 )
        {
            pageSize = 10;
        }else
        {
            example.setPageSize(pageSize);
        }
        List<PayPca> pcas = payPcaMapper.selectByExample(example);
        List<PcaInfo> pcaInfos = new ArrayList<>();
        for (PayPca payPca : pcas) {
            PcaInfo pcaInfo = new PcaInfo();
            BeanCopier beanCopier = BeanCopier.create(PayPca.class, PcaInfo.class, false);
            beanCopier.copy(payPca, pcaInfo, null);
            pcaInfos.add(pcaInfo);
        }

        return pcaInfos;
    }

    @Override
    public Integer getPcaCount() {
        PayPcaExample example = new PayPcaExample();
        Integer count = payPcaMapper.countByExample(example);

        return count;
    }
}
