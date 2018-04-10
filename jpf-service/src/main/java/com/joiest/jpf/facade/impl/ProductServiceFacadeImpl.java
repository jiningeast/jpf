package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.*;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsProductMapper;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.ProductInfo;
import com.joiest.jpf.facade.ProductServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceFacadeImpl implements ProductServiceFacade {

    private static final Logger logger = LogManager.getLogger(ProductServiceFacadeImpl.class);

    @Autowired
    private PayMerchantsProductMapper payMerchantsProductMapper;

    /*@Override
    public GetProductResponse getProductList(GetProductRequest request) {
        if(request.getPageNo()<=0){
            request.setPageNo(1);
        }
        if (request.getPageSize() <= 0) {
            request.setPageSize(20);
        }
        PayMerchantsProductExample example = new PayMerchantsProductExample();
        example.setPageNo(request.getPageNo());
        example.setPageSize(request.getPageSize());
        example.setOrderByClause("updated DESC");
        PayMerchantsProductExample.Criteria c = example.createCriteria();
        if(StringUtils.isNotBlank(request.getPname())){
            c.andPnameEqualTo(request.getPname());
        }

        List<PayMerchantsProduct> list = payMerchantsProductMapper.selectByExample(example);
        example.setPageNo(0);
        example.setPageSize(0);
        int count = payMerchantsProductMapper.countByExample(example);
        List<ProductInfo> infoList = new ArrayList<>();
        for(PayMerchantsProduct product:list){
            ProductInfo productInfo = new ProductInfo();
            BeanCopier beanCopier = BeanCopier.create(PayMerchantsProduct.class, ProductInfo.class, false);
            beanCopier.copy(product,productInfo,null);
            infoList.add(productInfo);
        }
        GetProductResponse getProductResponse = new GetProductResponse();
        getProductResponse.setCount(count);
        getProductResponse.setList(infoList);
        return getProductResponse;*//*
    }*/

    /**
     * 获取用户列表
     * @param pname
     * @param status
     * @return
     */
    public List<ProductInfo> getProductsList(Long mtsid, String pname, Byte status, long pageNo, long pageSize)
    {
        if (pageNo<=0) {
            pageNo = 1;
        }
        if (pageSize<=0) {
            pageSize = 20;
        }
        PayMerchantsProductExample example = new PayMerchantsProductExample();
        example.setPageNo(pageNo);
        example.setPageSize(pageSize);
        example.setOrderByClause("updated DESC");
        PayMerchantsProductExample.Criteria c = example.createCriteria();

        if (mtsid != null) {
            c.andMtsidEqualTo(mtsid);
        }

        if(StringUtils.isNotBlank(pname)){
            c.andPnameLike( "%" + pname.trim() + "%" );
        }
        if (status != null) {
            c.andStatusEqualTo(status);
        }

        List<PayMerchantsProduct> list = payMerchantsProductMapper.selectByExample(example);
        List<ProductInfo> infoList = new ArrayList<>();
        for(PayMerchantsProduct product:list){
            ProductInfo productInfo = new ProductInfo();
            BeanCopier beanCopier = BeanCopier.create(PayMerchantsProduct.class, ProductInfo.class, false);
            beanCopier.copy(product,productInfo,null);
            infoList.add(productInfo);
        }
        return infoList;
    }

    /**
     * 获取用户列表统计
     * @param pname
     * @param status
     * @return
     */
    public int getProductsCount(Long mtsid, String pname, Byte status)
    {
        PayMerchantsProductExample example = new PayMerchantsProductExample();
        PayMerchantsProductExample.Criteria c = example.createCriteria();
        if(StringUtils.isNotBlank(pname)){
            c.andPnameEqualTo(pname);
        }
        if (status != null) {
            c.andStatusEqualTo(status);
        }
        return  payMerchantsProductMapper.countByExample(example);
    }

}
