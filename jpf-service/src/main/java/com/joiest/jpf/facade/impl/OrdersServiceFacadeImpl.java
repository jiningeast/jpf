package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayOrdersCustom;
import com.joiest.jpf.common.po.PayOrderExample;
import com.joiest.jpf.common.po.PayOrders;
import com.joiest.jpf.common.po.PayOrdersExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayOrdersCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrdersMapper;
import com.joiest.jpf.dto.GetOrdersRequest;
import com.joiest.jpf.dto.GetOrdersResponse;
import com.joiest.jpf.entity.OrdersInfo;
import com.joiest.jpf.facade.OrdersServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class OrdersServiceFacadeImpl implements OrdersServiceFacade {

    @Autowired
    private PayOrdersMapper payOrdersMapper;

    @Autowired
    private PayOrdersCustomMapper payOrdersCustomMapper;
    /**
     * 插入一条记录
     */
    @Override
    public int insRecord(OrdersInfo ordersInfo){
        PayOrders payOrders = new PayOrders();
        BeanCopier beanCopier = BeanCopier.create(OrdersInfo.class, PayOrders.class, false);
        beanCopier.copy(ordersInfo, payOrders, null);

        return payOrdersMapper.insertSelective(payOrders);
    }

    /**
     * 列表---后台
     */
    public GetOrdersResponse getOrdersList(GetOrdersRequest request)
    {
        if ( request.getRows() <= 0 )
        {
            request.setRows(10);
        }

        if ( request.getPage() <= 0 )
        {
            request.setPage(1);
        }

        PayOrdersExample example = new PayOrdersExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("created DESC");

        PayOrdersExample.Criteria c = example.createCriteria();
        if ( request.getPaytype() != null )
        {
            c.andPaytypeEqualTo(request.getPaytype() );
        }
        if (StringUtils.isNotBlank(request.getAddtimeStart()))
        {
            c.andCreatedGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()))
        {
            c.andCreatedLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }
//        c.addCriterionCustom("m.companyname", "北京天道保险经纪有限责任公司", "productName");
        List<PayOrdersCustom> list = payOrdersCustomMapper.getOrdersListAndPaytype(example);
        List<OrdersInfo> infoList = new ArrayList<>();
        for (PayOrdersCustom one : list)
        {
            OrdersInfo info = new OrdersInfo();
            BeanCopier beanCopier = BeanCopier.create(PayOrdersCustom.class, OrdersInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetOrdersResponse response = new GetOrdersResponse();
        response.setList(infoList);
        int count = payOrdersMapper.countByExample(example);
        response.setCount(count);
        return response;
    }

}
