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

    @Override
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
        return getProductResponse;
    }


}
