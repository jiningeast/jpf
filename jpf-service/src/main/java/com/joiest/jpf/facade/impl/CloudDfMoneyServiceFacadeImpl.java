package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayCloudDfMoneyInterfaceCustom;
import com.joiest.jpf.common.po.PayCloudDfMoney;
import com.joiest.jpf.common.po.PayCloudDfMoneyExample;
import com.joiest.jpf.common.util.BigDecimalCalculateUtils;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudDfMoneyInterfaceCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudDfMoneyMapper;
import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;
import com.joiest.jpf.facade.CloudDfMoneyServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class CloudDfMoneyServiceFacadeImpl implements CloudDfMoneyServiceFacade {

    @Autowired
    private PayCloudDfMoneyMapper payCloudDfMoneyMapper;

    @Autowired
    private PayCloudDfMoneyInterfaceCustomMapper payCloudDfMoneyInterfaceCustomMapper;

    @Override
    public GetCloudMoneyDfResponse getDfMoneyList(String start, String end, String uid, long pageNo, long pageSize, int flag)
    {
        PayCloudDfMoneyExample e = new PayCloudDfMoneyExample();
        if (flag == 1 )
        {
            e.setPageNo(pageNo);
            e.setPageSize(pageSize);
        }
        e.setOrderByClause("addtime DESC");

        PayCloudDfMoneyExample.Criteria c = e.createCriteria();
        c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(start,DateUtils.DATEFORMATSHORT));
        c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(end,DateUtils.DATEFORMATSHORT));
        c.andUidEqualTo(Long.parseLong(uid));

        List<PayCloudDfMoney> list = payCloudDfMoneyMapper.selectByExample(e);
        List<CloudDfMoneyInterfaceInfo> listnew = new ArrayList<>();

        if ( list.isEmpty() || list == null )
        {
            return null;
        }

        Double monthTotal = 0.00;
        for (PayCloudDfMoney one : list)
        {
            CloudDfMoneyInterfaceInfo info = new CloudDfMoneyInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoney.class, CloudDfMoneyInterfaceInfo.class, false);
            beanCopier.copy(one, info, null);
            listnew.add(info);

            monthTotal = BigDecimalCalculateUtils.add(new Double(one.getPayablemoney().toString()), monthTotal);
        }
        GetCloudMoneyDfResponse response = new GetCloudMoneyDfResponse();
        response.setList(listnew);
        response.setMonthTotal(monthTotal);
        return response;
    }

    @Override
    public Double getUserDfTotalMoney(String uid)
    {
        PayCloudDfMoneyExample e = new PayCloudDfMoneyExample();
        e.setOrderByClause("addtime DESC");

        PayCloudDfMoneyExample.Criteria c = e.createCriteria();
        c.andUidEqualTo(Long.parseLong(uid));

        List<PayCloudDfMoney> list = payCloudDfMoneyMapper.selectByExample(e);
        List<CloudDfMoneyInterfaceInfo> listnew = new ArrayList<>();

        if ( list.isEmpty() || list == null )
        {
            return null;
        }

        Double monthTotal = 0.00;
        for (PayCloudDfMoney one : list)
        {
            monthTotal = BigDecimalCalculateUtils.add(new Double(one.getPayablemoney().toString()), monthTotal);
        }
        return monthTotal;
    }

    @Override
    public List<CloudDfMoneyInterfaceInfo> getUserMonthList(Long uid)
    {
        PayCloudDfMoneyExample example = new PayCloudDfMoneyExample();
        example.setOrderByClause("addtime DESC");
        PayCloudDfMoneyExample.Criteria c = example.createCriteria();
        c.andUidEqualTo(uid);
        List<PayCloudDfMoneyInterfaceCustom> list = payCloudDfMoneyInterfaceCustomMapper.getUserMonthList(example);

        List<CloudDfMoneyInterfaceInfo> response = new ArrayList<>();
        if ( list.isEmpty() || list == null )
        {
            return null;
        }

        for (PayCloudDfMoneyInterfaceCustom one : list)
        {
            CloudDfMoneyInterfaceInfo info = new CloudDfMoneyInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoneyInterfaceCustom.class, CloudDfMoneyInterfaceInfo.class, false);
            beanCopier.copy(one, info, null);
            response.add(info);
        }

        return response;
    }

}
