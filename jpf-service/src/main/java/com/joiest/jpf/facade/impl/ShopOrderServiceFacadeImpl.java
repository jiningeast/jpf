package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayShopOrderCustom;
import com.joiest.jpf.common.po.PayShopOrderExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopOrderCustomMapper;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;
import com.joiest.jpf.entity.ShopOrderInfo;
import com.joiest.jpf.facade.ShopOrderServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ShopOrderServiceFacadeImpl implements ShopOrderServiceFacade {

    @Autowired
    private PayShopOrderCustomMapper payShopOrderCustomMapper;

    /**
     * 公司列表---后台
     */
    public GetShopOrderResponse getList(GetShopOrderRequest request)
    {
        if ( request.getRows() <= 0 )
        {
            request.setRows(10);
        }

        if ( request.getPage() <= 0 )
        {
            request.setPage(1);
        }

        PayShopOrderExample example = new PayShopOrderExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("addtime DESC");

        PayShopOrderExample.Criteria c =example.createCriteria();

        if ( StringUtils.isNotBlank(request.getProductName()))
        {
            c.andProductNameLike("%"+ request.getProductName() +"%" );
        }
        if( StringUtils.isNotBlank(request.getCustomerName())){
            c.andCustomerNameLike( "%"+ request.getCustomerName() +"%" );
        }
        if( StringUtils.isNotBlank(request.getOrderNo())){
            c.andOrderNoEqualTo(request.getOrderNo());
        }
        if(request.getStatus()!=null && request.getStatus().toString()!=""){
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

        // 添加时间搜索
        if (StringUtils.isNotBlank(request.getPaytimeStart()))
        {
            c.andPaytimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getPaytimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getPaytimeEnd()))
        {
            c.andPaytimeLessThanOrEqualTo(DateUtils.getFdate(request.getPaytimeEnd(),DateUtils.DATEFORMATLONG));
        }

        List<PayShopOrderCustom> list = payShopOrderCustomMapper.selectByExampleJoin(example);
        List<ShopOrderInfo> infoList = new ArrayList<>();

        for (PayShopOrderCustom one : list)
        {
            ShopOrderInfo info = new ShopOrderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopOrderCustom.class, ShopOrderInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetShopOrderResponse response = new GetShopOrderResponse();
        response.setList(infoList);
        int count = payShopOrderCustomMapper.countByExample(example);
        response.setCount(count);
        return response;
    }


}
