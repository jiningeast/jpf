package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayShopStockOrder;
import com.joiest.jpf.common.po.PayShopStockOrderExample;
import com.joiest.jpf.common.po.PayShopStockOrderProduct;
import com.joiest.jpf.common.po.PayShopStockOrderProductExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopStockOrderMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopStockOrderProductMapper;
import com.joiest.jpf.dto.GetShopStockOrderProductResponse;
import com.joiest.jpf.dto.GetShopStockOrderRequest;
import com.joiest.jpf.dto.GetShopStockOrderResponse;
import com.joiest.jpf.entity.ShopStockOrderInfo;
import com.joiest.jpf.entity.ShopStockOrderProductInfo;
import com.joiest.jpf.facade.ShopStockOrderProductServiceFacade;
import com.joiest.jpf.facade.ShopStockOrderServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ShopStockOrderProductServiceFacadeImpl implements ShopStockOrderProductServiceFacade {

    @Autowired
    private PayShopStockOrderProductMapper payShopStockOrderProductMapper;

    /**
     * 采购订单列表---后台
     */
    @Override
    public GetShopStockOrderProductResponse getProduct(String OrderNo)
    {

        PayShopStockOrderProductExample example = new PayShopStockOrderProductExample();
        example.setOrderByClause("id DESC");
        PayShopStockOrderProductExample.Criteria c =example.createCriteria();

        if(OrderNo != null && StringUtils.isNotBlank(OrderNo)){
            c.andStockOrderNoEqualTo(OrderNo);
        }
        List<PayShopStockOrderProduct> list = payShopStockOrderProductMapper.selectByExample(example);
        List<ShopStockOrderProductInfo> infoList = new ArrayList<>();

        for (PayShopStockOrderProduct one : list)
        {
            ShopStockOrderProductInfo info = new ShopStockOrderProductInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopStockOrderProduct.class, ShopStockOrderProductInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetShopStockOrderProductResponse response = new GetShopStockOrderProductResponse();
        response.setList(infoList);
        int count = payShopStockOrderProductMapper.countByExample(example);
        response.setCount(count);
        return response;
    }

}
