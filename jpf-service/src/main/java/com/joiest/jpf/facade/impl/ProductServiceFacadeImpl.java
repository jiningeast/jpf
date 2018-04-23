package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.constant.EnumConstants;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.ValidatorUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsProductMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsTypeMapper;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.ProductInfo;
import com.joiest.jpf.facade.ProductServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

public class ProductServiceFacadeImpl implements ProductServiceFacade {

    private static final Logger logger = LogManager.getLogger(ProductServiceFacadeImpl.class);

    @Autowired
    private PayMerchantsProductMapper payMerchantsProductMapper;

    @Autowired
    private PayMerchantsTypeMapper payMerchantsTypeMapper;

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
    @Override
    public List<ProductInfo> getProductsList(Long pid, Long mtsid, String pname, Byte status, long pageNo, long pageSize)
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
        if ( pid != null ){
            c.andPidEqualTo(pid);
        }
        if(StringUtils.isNotBlank(pname)){
            c.andPnameLike( "%" + pname.trim() + "%" );
        }
        if (status != null) {
            c.andStatusEqualTo(status);
        }

        List<PayMerchantsProduct> list = payMerchantsProductMapper.selectByExample(example);
        PayMerchantsTypeExample example1 = new PayMerchantsTypeExample();
        PayMerchantsTypeExample.Criteria c1 = example1.createCriteria();

        List<PayMerchantsType> payTypeList = payMerchantsTypeMapper.selectByExample(example1);
        Map<Integer,String> map = new HashMap<>();
        for ( PayMerchantsType payType : payTypeList )
        {
            map.put(payType.getCatid(), payType.getCat());
        }

        if ( list.size() > 0 && payTypeList.size() > 0 ){
            for(PayMerchantsProduct product:list){
                String zftype_tmp = new String();

                String str = product.getZftype();
                String[] strArr = str.split("\\,");
                for (String x : strArr){
                    zftype_tmp += map.get(Integer.parseInt(x))+"<br/>";
                }
                String zftype_cn = zftype_tmp.substring(0,zftype_tmp.length()-1);
                product.setZftype(zftype_cn);
            }
        }

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
    @Override
    public int getProductsCount(Long pid, Long mtsid, String pname, Byte status)
    {
        PayMerchantsProductExample example = new PayMerchantsProductExample();
        PayMerchantsProductExample.Criteria c = example.createCriteria();
        if (mtsid != null) {
            c.andMtsidEqualTo(mtsid);
        }
        if ( pid != null ){
            c.andPidEqualTo(pid);
        }
        if(StringUtils.isNotBlank(pname)){
            c.andPnameLike( "%" + pname.trim() + "%" );
        }
        if (status != null) {
            c.andStatusEqualTo(status);
        }
        return  payMerchantsProductMapper.countByExample(example);
    }

    /**
     * 上下架
     * @param pname
     * @param status
     * @return
     */
    @Override
    public JpfResponseDto upStatus(String pname,Byte status)
    {
        if ( StringUtils.isBlank(pname) ) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "产品名称不能为空");
        }
        if ( status == null ) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "状态不能为空");
        }
        if ( !EnumConstants.ProductStatus.normal.value().equals(status) && !EnumConstants.ProductStatus.forbid.value().equals(status) ) {
            throw new JpfException(JpfErrorInfo.STATUS_ERROR, "状态不匹配");
        }

        PayMerchantsProductExample example = new PayMerchantsProductExample();
        PayMerchantsProductExample.Criteria c = example.createCriteria();
        c.andPnameEqualTo(pname);
        PayMerchantsProduct product = new PayMerchantsProduct();
        product.setStatus(status);
        payMerchantsProductMapper.updateByExampleSelective(product,example);
        return new JpfResponseDto();
    }

    @Override
    public ProductInfo getProduct(Long pid){
        if ( pid == null ){
            logger.info("求情参数pid为空");
        }
        PayMerchantsProduct payMerchantsProduct = payMerchantsProductMapper.selectByPrimaryKey(pid);
        ProductInfo productInfo = new ProductInfo();
        BeanCopier beanCopier = BeanCopier.create(PayMerchantsProduct.class, ProductInfo.class,false);
        beanCopier.copy(payMerchantsProduct,productInfo,null);
        return productInfo;
    }

    /**
     * 产品编辑
     * @param request
     * @return
     */
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    @Override
    public JpfResponseDto modifyProduct(ModifyProductRequest request){
        ValidatorUtils.validate(request);
        PayMerchantsProduct payMerchantsProduct = payMerchantsProductMapper.selectByPrimaryKey(request.getPid());
        if ( payMerchantsProduct == null ){
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "产品信息不存在");
        }

        PayMerchantsProductExample example = new PayMerchantsProductExample();
        PayMerchantsProductExample.Criteria c = example.createCriteria();
        c.andPidEqualTo(request.getPid());
        PayMerchantsProduct product = new PayMerchantsProduct();
        product.setPname(request.getPname());
        product.setPintro(request.getPintro());
        product.setPmoney(request.getPmoney());
        product.setStatus(request.getStatus());
        product.setUpdated(new Date());
        String zftype_tmp = new String();
        String zftype = new String();
        for (int i=0; i < request.getZftype().size(); i++){
            zftype_tmp += request.getZftype().get(i) + ',';
        }
        zftype = zftype_tmp.substring(0,zftype_tmp.length()-1);
        product.setZftype(zftype);
        payMerchantsProductMapper.updateByExampleSelective(product,example);
        return new JpfResponseDto();
    }

}
