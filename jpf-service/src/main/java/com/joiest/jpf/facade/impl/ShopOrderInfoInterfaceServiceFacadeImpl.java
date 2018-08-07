package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayShopOrderCustom;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopOrderExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopOrderCustomMapper;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;
import com.joiest.jpf.dto.ShopOrderInfoInterfaceRequest;
import com.joiest.jpf.dto.ShopOrderInfoInterfaceResponse;
import com.joiest.jpf.entity.ShopOrderInfo;
import com.joiest.jpf.entity.ShopOrderInfoInterface;
import com.joiest.jpf.facade.ShopOrderInfoInterfaceServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ShopOrderInfoInterfaceServiceFacadeImpl implements ShopOrderInfoInterfaceServiceFacade {

    @Autowired
    private PayShopOrderCustomMapper payShopOrderCustomMapper;

    /**
     * 订单列表---后台
     */
    public ShopOrderInfoInterfaceResponse getList(ShopOrderInfoInterfaceRequest request)
    {
        if ( request.getPageSize() ==null || Long.parseLong(request.getPageSize()) <= 0 )
        {
            request.setPageSize("10");
        }

        if ( request.getPage() ==null || Long.parseLong(request.getPage()) <= 0 )
        {
            request.setPage("1");
        }

        PayShopOrderExample example = new PayShopOrderExample();
        example.setPageNo(Long.parseLong(request.getPage()));
        example.setPageSize(Long.parseLong(request.getPageSize()));
        example.setOrderByClause("addtime DESC");

        PayShopOrderExample.Criteria c =example.createCriteria();

        //目前只支持订单号搜索
        if ( StringUtils.isNotBlank(request.getKeyword()))
        {
            c.andOrderNoEqualTo( request.getKeyword()  );
        }


        List<PayShopOrderCustom> list = payShopOrderCustomMapper.selectByExampleInterfaceJoin(example);
        if( list.size() <=0 || list == null){
            return null;
        }
        List<ShopOrderInfoInterface> infoList = new ArrayList<>();

        for (PayShopOrderCustom one : list)
        {
            ShopOrderInfoInterface info = new ShopOrderInfoInterface();
            BeanCopier beanCopier = BeanCopier.create(PayShopOrderCustom.class, ShopOrderInfoInterface.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        ShopOrderInfoInterfaceResponse response = new ShopOrderInfoInterfaceResponse();
        response.setList(infoList);
        int count = payShopOrderCustomMapper.countByExample(example);
        response.setCount(count);

        return response;
    }


    /**
     * 订单详情
     */
    public ShopOrderInfoInterface getOne(ShopOrderInfoInterfaceRequest request)
    {
        if ( StringUtils.isBlank(request.getOrderNo()))
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单编号不能为空");
        }
        PayShopOrderCustom payShopOrderCustom = payShopOrderCustomMapper.selectOrderAll(request.getOrderNo());

        if(payShopOrderCustom==null){
            return null;
        }
        ShopOrderInfoInterface shopOrderInfoInterface = new ShopOrderInfoInterface();
        BeanCopier beanCopier = BeanCopier.create(PayShopOrderCustom.class, ShopOrderInfoInterface.class, false);
        beanCopier.copy(payShopOrderCustom,shopOrderInfoInterface,null);

        return shopOrderInfoInterface;
    }


}
