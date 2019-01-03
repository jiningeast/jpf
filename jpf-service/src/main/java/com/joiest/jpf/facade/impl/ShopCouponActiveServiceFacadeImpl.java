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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    @Override
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
        example.setOrderByClause("ID DESC");

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
        //单号搜索
        if(StringUtils.isNotBlank(request.getOrderNo())){
            c.andOrderNoEqualTo(request.getOrderNo());
        }
        if(StringUtils.isNotBlank(request.getBargainOrderNo())){

            c.andBargainOrderNoEqualTo(request.getBargainOrderNo());
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

    /**
     * 欣豆日志添加
     * */
    @Override
    public int addShopCouponActive(PayShopCouponActive payShopCouponActive){

        return payShopCouponActiveMapper.insertSelective(payShopCouponActive);
    }

    /**
     *
     * @param payShopCouponActive
     * @return
     */
    @Override
    public List<PayShopCouponActive> getCouponActive(PayShopCouponActive payShopCouponActive) {
        PayShopCouponActiveExample example = new PayShopCouponActiveExample();
        PayShopCouponActiveExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(payShopCouponActive.getOrderNo())){
            criteria.andOrderNoEqualTo(payShopCouponActive.getOrderNo());
        }
        if(StringUtils.isNotBlank(payShopCouponActive.getSource())){
            criteria.andSourceEqualTo(payShopCouponActive.getSource());
        }
        if(StringUtils.isNotBlank(payShopCouponActive.getType())){
            criteria.andTypeEqualTo(payShopCouponActive.getType());
        }
        return payShopCouponActiveMapper.selectByExample(example);
    }


}
