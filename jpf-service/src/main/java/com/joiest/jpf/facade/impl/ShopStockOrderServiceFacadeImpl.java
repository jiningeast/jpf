package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayShopOrderCustom;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopOrderExample;
import com.joiest.jpf.common.po.PayShopStockOrder;
import com.joiest.jpf.common.po.PayShopStockOrderExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopStockOrderMapper;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;
import com.joiest.jpf.dto.GetShopStockOrderRequest;
import com.joiest.jpf.dto.GetShopStockOrderResponse;
import com.joiest.jpf.entity.ShopOrderInfo;
import com.joiest.jpf.entity.ShopStockOrderInfo;
import com.joiest.jpf.facade.ShopOrderServiceFacade;
import com.joiest.jpf.facade.ShopStockOrderServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ShopStockOrderServiceFacadeImpl implements ShopStockOrderServiceFacade {

    @Autowired
    private PayShopStockOrderMapper payShopStockOrderMapper;

    /**
     * 订单列表---后台
     */
    @Override
    public GetShopStockOrderResponse getList(GetShopStockOrderRequest request)
    {
        if( request.getPage() <= 1){
            request.setPage(Long.parseLong("1"));
        }
        PayShopStockOrderExample example = new PayShopStockOrderExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("id DESC");

        PayShopStockOrderExample.Criteria c =example.createCriteria();
        if(request.getStatus() != null && !request.getStatus().equals("")){
            c.andStatusEqualTo(request.getStatus());
        }
        // 添加时间搜索
        if (StringUtils.isNotBlank(request.getAddtimeStart()))
        {
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()))
        {
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }

        List<PayShopStockOrder> list = payShopStockOrderMapper.selectByExample(example);

        List<ShopStockOrderInfo> infoList = new ArrayList<>();

        for (PayShopStockOrder one : list)
        {
            ShopStockOrderInfo info = new ShopStockOrderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopStockOrder.class, ShopStockOrderInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetShopStockOrderResponse response = new GetShopStockOrderResponse();
        response.setList(infoList);
        int count = payShopStockOrderMapper.countByExample(example);
        response.setCount(count);
        return response;
    }


    /**
     * 订单列表---后台
     */
    @Override
    public ShopStockOrderInfo getOne(String order_no)
    {
        if(StringUtils.isBlank(order_no)){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "采购单号不能为空");
        }
        PayShopStockOrderExample example = new PayShopStockOrderExample();
        PayShopStockOrderExample.Criteria c = example.createCriteria();
        c.andOrderNoEqualTo(order_no);

        List<PayShopStockOrder> list = payShopStockOrderMapper.selectByExample(example);
        if(list.size() <=0 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到数据");
        }

        ShopStockOrderInfo info = new ShopStockOrderInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopStockOrder.class, ShopStockOrderInfo.class, false);
        beanCopier.copy(list.get(0), info, null);


        return info;
    }


}
