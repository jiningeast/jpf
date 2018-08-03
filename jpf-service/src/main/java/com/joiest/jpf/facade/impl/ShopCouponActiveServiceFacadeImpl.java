package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayShopCouponActive;
import com.joiest.jpf.common.po.PayShopCouponActiveExample;
import com.joiest.jpf.common.po.PayShopCustomer;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCouponActiveMapper;
import com.joiest.jpf.dto.GetShopCouponActiveRequest;
import com.joiest.jpf.dto.GetShopCouponActiveResponse;
import com.joiest.jpf.dto.GetShopCustomerResponse;
import com.joiest.jpf.entity.ShopCouponActiveInfo;
import com.joiest.jpf.entity.ShopCustomerInfo;
import com.joiest.jpf.facade.ShopCouponActiveServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ShopCouponActiveServiceFacadeImpl implements ShopCouponActiveServiceFacade {

    @Autowired
    private PayShopCouponActiveMapper payShopCouponActiveMapper;

    /**
     * 客户欣豆行为列表
     */
    public GetShopCouponActiveResponse getList(GetShopCouponActiveRequest request,String id)
    {
        if ( request.getRows() <= 0 )
        {
            request.setRows(10);
        }

        if ( request.getPage() <= 0 )
        {
            request.setPage(1);
        }

        PayShopCouponActiveExample example = new PayShopCouponActiveExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("addtime DESC");

        PayShopCouponActiveExample.Criteria c = example.createCriteria();

        if ( StringUtils.isNotBlank(request.getContent()))
        {
            c.andContentEqualTo(request.getContent() );
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
        //判断是否有用户id
        if(StringUtils.isNotBlank(request.getCustomerId())){
            c.andCustomerIdEqualTo(request.getCustomerId());
        }
        if(StringUtils.isNotBlank(id)){
            c.andCustomerIdEqualTo(id);
        }
        if(request.getType()!=null && request.getType().toString()!=""){
            c.andTypeEqualTo(request.getType());
        }

        List<PayShopCouponActive> list = payShopCouponActiveMapper.selectByExample(example);
        List<ShopCouponActiveInfo> infoList = new ArrayList<>();
        for (PayShopCouponActive one : list)
        {
            ShopCouponActiveInfo info = new ShopCouponActiveInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopCouponActive.class, ShopCouponActiveInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetShopCouponActiveResponse response = new GetShopCouponActiveResponse();
        response.setList(infoList);
        int count = payShopCouponActiveMapper.countByExample(example);
        response.setCount(count);
        return response;
    }

}
