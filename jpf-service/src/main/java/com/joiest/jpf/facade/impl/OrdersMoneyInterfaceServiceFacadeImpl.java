package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayOrdersMoneyDetail;
import com.joiest.jpf.common.po.PayOrdersMoneyDetailExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrdersMoneyDetailMapper;
import com.joiest.jpf.entity.OrdersMoneyInterfaceInfo;
import com.joiest.jpf.facade.OrdersMoneyInterfaceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

public class OrdersMoneyInterfaceServiceFacadeImpl implements OrdersMoneyInterfaceServiceFacade {


    @Autowired
    private PayOrdersMoneyDetailMapper payOrdersMoneyDetailMapper;

    /**
     * 添加订单费率金额详情
     */
    public int addRecord(OrdersMoneyInterfaceInfo interfaceInfo)
    {

        PayOrdersMoneyDetail payOrdersMoneyDetail = new PayOrdersMoneyDetail();
        BeanCopier beanCopier = BeanCopier.create(OrdersMoneyInterfaceInfo.class, PayOrdersMoneyDetail.class, false);
        beanCopier.copy(interfaceInfo, payOrdersMoneyDetail, null);

        return payOrdersMoneyDetailMapper.insertSelective(payOrdersMoneyDetail);
    }

    public int modifyRecord(OrdersMoneyInterfaceInfo interfaceInfo)
    {
        PayOrdersMoneyDetail payOrdersMoneyDetail = new PayOrdersMoneyDetail();
        PayOrdersMoneyDetailExample example = new PayOrdersMoneyDetailExample();
        PayOrdersMoneyDetailExample.Criteria c = example.createCriteria();
        c.andOrderidEqualTo(interfaceInfo.getOrderid());
        BeanCopier beanCopier = BeanCopier.create(OrdersMoneyInterfaceInfo.class, PayOrdersMoneyDetail.class, false);
        beanCopier.copy(interfaceInfo, payOrdersMoneyDetail, null);
        return payOrdersMoneyDetailMapper.updateByExampleSelective(payOrdersMoneyDetail, example);
    }

}
