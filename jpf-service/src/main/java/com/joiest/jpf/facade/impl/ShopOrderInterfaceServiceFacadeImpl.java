package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayShopOrder;
import com.joiest.jpf.common.po.PayShopOrderExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopOrderMapper;
import com.joiest.jpf.entity.ShopOrderInterfaceInfo;
import com.joiest.jpf.facade.ShopOrderInterfaceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class ShopOrderInterfaceServiceFacadeImpl implements ShopOrderInterfaceServiceFacade {

    @Autowired
    private PayShopOrderMapper payShopOrderMapper;
    @Override
    public int addOrder(ShopOrderInterfaceInfo info) {

        PayShopOrder order = new PayShopOrder();
        PayShopOrderExample example = new PayShopOrderExample();
        PayShopOrderExample.Criteria c = example.createCriteria();
        BeanCopier beanCopier = BeanCopier.create(ShopOrderInterfaceInfo.class, PayShopOrder.class, false);
        beanCopier.copy(info, order, null);

        return payShopOrderMapper.insertSelective(order);
    }

    @Override
    public ShopOrderInterfaceInfo getOrderOne(String orderNo, String uid) {

        PayShopOrderExample example = new PayShopOrderExample();
        PayShopOrderExample.Criteria c = example.createCriteria();
        c.andOrderNoEqualTo(orderNo);
        c.andCustomerIdEqualTo(uid);
        List<PayShopOrder> list = payShopOrderMapper.selectByExample(example);
        if ( list == null )
        {
            return null;
        }
        ShopOrderInterfaceInfo info = new ShopOrderInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopOrder.class, ShopOrderInterfaceInfo.class, false);
        beanCopier.copy(list.get(0), info, null);

        return info;
    }

    /**
     * 获取用户订单数量，已取消的不统计
     */
    @Override
    public int getOrdersCount(String customerId){
        PayShopOrderExample e = new PayShopOrderExample();
        PayShopOrderExample.Criteria c = e.createCriteria();
        c.andCustomerIdEqualTo(customerId);
        c.andStatusNotEqualTo((byte)3);

        return payShopOrderMapper.countByExample(e);
    }
}
