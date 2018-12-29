package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.constant.EnumConstants;
import com.joiest.jpf.common.custom.PayShopProductCustom;
import com.joiest.jpf.common.custom.PayShopProductCustomExample;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopProductContentCustomMapper;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopProductCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopProductContentMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopProductInfoMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopProductMapper;
import com.joiest.jpf.dto.GetShopProductRequest;
import com.joiest.jpf.dto.GetShopProductResponse;
import com.joiest.jpf.dto.ModifyShopProductRequest;
import com.joiest.jpf.dto.ShopProductInfoRequest;
import com.joiest.jpf.entity.ShopProductInfo;
import com.joiest.jpf.entity.ShopProductInfoInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.ShopProductServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

public class ShopProductServiceFacadeImpl implements ShopProductServiceFacade {

    @Autowired
    private PayShopProductMapper payShopProductMapper;

    @Autowired
    private PayShopProductCustomMapper payShopProductCustomMapper;

    @Autowired
    private PayShopProductInfoMapper payShopProductInfoMapper;

    @Autowired
    private PayShopProductContentMapper payShopProductContentMapper;

    @Autowired
    private PayShopProductContentCustomMapper payShopProductContentCustomMapper;

    @Override
    public GetShopProductResponse getProductList(GetShopProductRequest request) {

        if (request.getPage() <= 0) {
            request.setPage(1);
        }
        if (request.getRows()<=0) {
            request.setRows(20);
        }
        PayShopProductExample example = new PayShopProductExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("addtime DESC");
        PayShopProductExample.Criteria c = example.createCriteria();
        if ( StringUtils.isNotBlank(request.getName()) )
        {
            c.andNameLike("%" + request.getName().trim()+ "%");
        }
        if ( request.getStatus() != null )
        {
            c.andStatusEqualTo(request.getStatus());
        }

        List<PayShopProduct> list = payShopProductMapper.selectByExample(example);
        int count = payShopProductMapper.countByExample(example);

        GetShopProductResponse response = new GetShopProductResponse();
        response.setCount(count);
        List<ShopProductInfo> resultList = new ArrayList<>();
        for (PayShopProduct one : list)
        {
            ShopProductInfo info = new ShopProductInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopProduct.class, ShopProductInfo.class, false);
            beanCopier.copy(one, info, null);

            //商品基础信息
            PayShopProductInfo pInfo = payShopProductInfoMapper.selectByPrimaryKey(one.getProductInfoId());
            info.setBrandId(pInfo.getBrandId());
            info.setBrandName(pInfo.getBrandName());
            info.setSupplierId(pInfo.getSupplierId());
            info.setSupplierName(pInfo.getSupplierName());
            info.setTypeId(pInfo.getTypeId());
            info.setTypeName(pInfo.getTypeName());
            resultList.add(info);
        }
        response.setList(resultList);

        return response;
    }

    @Override
    public JpfResponseDto upStatus(String id, Byte status,UserInfo userInfo) {
        if ( StringUtils.isBlank(id) ) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "商品id不能为空");
        }
        if ( status == null ) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "状态不能为空");
        }
        if ( !EnumConstants.ShopProductStatus.normal.value().equals(status) && !EnumConstants.ShopProductStatus.forbid.value().equals(status) ) {
            throw new JpfException(JpfErrorInfo.STATUS_ERROR, "状态不匹配");
        }
        PayShopProductExample example = new PayShopProductExample();
        PayShopProductExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(id);
        PayShopProduct payShopProduct = new PayShopProduct();
        payShopProduct.setId(id);
        payShopProduct.setStatus(status);
        payShopProduct.setUpdatetime(new Date());
        payShopProduct.setOperatorId(userInfo.getId());
        payShopProduct.setOperatorName(userInfo.getUserName());
        int res = payShopProductMapper.updateByPrimaryKeySelective(payShopProduct);

        return new JpfResponseDto();
    }

    @Override
    public  List<ShopProductInfoInfo> getProductInfoList() {
        PayShopProductInfoExample example = new PayShopProductInfoExample();
        example.setOrderByClause("addtime DESC");
        PayShopProductInfoExample.Criteria c = example.createCriteria();
        c.andStatusNotEqualTo((byte)1);
        List<PayShopProductInfo> list = payShopProductInfoMapper.selectByExample(example);
        List<ShopProductInfoInfo> resultList = new ArrayList<>();
        for (PayShopProductInfo one : list){
            ShopProductInfoInfo info = new ShopProductInfoInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopProductInfo.class, ShopProductInfoInfo.class, false);
            beanCopier.copy(one, info, null);
            StringBuilder sb = new StringBuilder();
            sb.append("商品类型:");
            sb.append(one.getTypeName());
            sb.append(";供应商:");
            sb.append(one.getSupplierName());
            sb.append(";品牌:");
            sb.append(one.getBrandName());
            info.setContent(sb.toString());
            info.setContentId(one.getId().toString());
            resultList.add(info);
        }
        return resultList;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})//事务处理
    public JpfResponseDto addShopProduct(ModifyShopProductRequest request) {
        PayShopProduct payShopProduct = new PayShopProduct();
        payShopProduct.setProductInfoId(Integer.valueOf(request.getProductInfoId()));
        payShopProduct.setName(request.getName());
        payShopProduct.setImage(request.getImage());
        payShopProduct.setDou(new BigDecimal(request.getDou()));
        payShopProduct.setIntro(request.getIntro());
        payShopProduct.setStored(Integer.parseInt(request.getStored()));
        payShopProduct.setStoredSafe(Integer.parseInt(request.getStoredSafe()));
        payShopProduct.setAddtime(new Date());
        payShopProduct.setOperatorId(request.getOperatorId());
        payShopProduct.setOperatorName(request.getOperatorName());
        payShopProduct.setRechargeMoney(Integer.parseInt(request.getRechargeMoney()));
        payShopProduct.setCardid(request.getCardid());
        payShopProduct.setStatus(request.getStatus());
        BigDecimal bid=new BigDecimal(request.getBid());
        BigDecimal money=new BigDecimal(request.getMoney());
        payShopProduct.setBid(bid);
        payShopProduct.setMoney(money);
        payShopProduct.setType(request.getType());
        payShopProduct.setProductContentId("0"); //内容ID

        //int res = payShopProductMapper.insertSelective(payShopProduct);
        payShopProductCustomMapper.insertSelective(payShopProduct);
        if( Integer.valueOf(payShopProduct.getId()) > 0 ){

            //是否添加详情内容
            if( !StringUtils.isBlank(request.getProductContent())){
                PayShopProductContent productContent = new PayShopProductContent();
                productContent.setContent(request.getProductContent());
                payShopProductContentCustomMapper.insertSelective(productContent);
                if( Integer.valueOf(productContent.getId()) > 0 ){

                    PayShopProduct shopProduct = new PayShopProduct();
                    shopProduct.setProductContentId(productContent.getId());
                    shopProduct.setId(payShopProduct.getId());
                    int upCount = payShopProductMapper.updateByPrimaryKeySelective(shopProduct);
                    if( upCount != 1 ){
                        throw new JpfException(JpfErrorInfo.INVALID_PARAMETER,"操作失败");
                    }
                }
            }

        }
        return new JpfResponseDto();
    }

    @Override
    public ShopProductInfo getShopProduct(String id) {
        if ( StringUtils.isBlank(id) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER,"id不能为空");
        }
        PayShopProductExample example = new PayShopProductExample();
        PayShopProductExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(id);
        List<PayShopProductCustom> payShopProductCustom = payShopProductCustomMapper.selectContentByPid(example);

        if ( payShopProductCustom == null || payShopProductCustom.size() <= 0 )
        {
            return null;
        }
        ShopProductInfo info = new ShopProductInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopProductCustom.class, ShopProductInfo.class, false);
        beanCopier.copy(payShopProductCustom.get(0), info, null);

        return info;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})//事务处理
    public JpfResponseDto modifyShopProduct(ModifyShopProductRequest request) {
        PayShopProduct payShopProduct = new PayShopProduct();
        payShopProduct.setId(request.getId());
        payShopProduct.setProductInfoId(Integer.valueOf(request.getProductInfoId()));
        payShopProduct.setName(request.getName());
        payShopProduct.setImage(request.getImage());
        payShopProduct.setDou(new BigDecimal(request.getDou()));
        payShopProduct.setIntro(request.getIntro());
        payShopProduct.setStored(Integer.parseInt(request.getStored()));
        payShopProduct.setStoredSafe(Integer.parseInt(request.getStoredSafe()));
        payShopProduct.setOperatorId(request.getOperatorId());
        payShopProduct.setOperatorName(request.getOperatorName());
        payShopProduct.setStatus(request.getStatus());
        payShopProduct.setUpdatetime(new Date());
        payShopProduct.setRechargeMoney(Integer.parseInt(request.getRechargeMoney()));
        payShopProduct.setCardid(request.getCardid());
        BigDecimal bid=new BigDecimal(request.getBid());
        BigDecimal money=new BigDecimal(request.getMoney());
        payShopProduct.setBid(bid);
        payShopProduct.setMoney(money);
        payShopProduct.setType(request.getType());
        int res = payShopProductMapper.updateByPrimaryKeySelective(payShopProduct);
        if( res == 1  ){

            //商品详情是否存在
            if( Integer.valueOf(request.getProductContentId()) > 0 ){

                PayShopProductContent productContent = new PayShopProductContent();
                productContent.setContent(request.getProductContent());
                productContent.setId(request.getProductContentId());
                int upCount = payShopProductContentMapper.updateByPrimaryKeySelective(productContent);
                if( upCount != 1 ){
                        throw new JpfException(JpfErrorInfo.INVALID_PARAMETER,"操作失败");
                }
            }else{
                //添加商品详情
                //是否添加详情内容
                if( !StringUtils.isBlank(request.getProductContent())){
                    PayShopProductContent productContent = new PayShopProductContent();
                    productContent.setContent(request.getProductContent());
                    payShopProductContentCustomMapper.insertSelective(productContent);
                    if( Integer.valueOf(productContent.getId()) > 0 ){

                        PayShopProduct shopProduct = new PayShopProduct();
                        shopProduct.setProductContentId(productContent.getId());
                        shopProduct.setId(payShopProduct.getId());
                        int upCount = payShopProductMapper.updateByPrimaryKeySelective(shopProduct);
                        if( upCount != 1 ){
                            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER,"操作失败");
                        }
                    }
                }
            }
        }

        return new JpfResponseDto();
    }

    @Override
    public JpfResponseDto addShopProductInfo(ShopProductInfoRequest request) {
        PayShopProductInfoExample example = new PayShopProductInfoExample();
        PayShopProductInfoExample.Criteria c = example.createCriteria();
        PayShopProductInfo info = new PayShopProductInfo();
        BeanCopier beanCopier = BeanCopier.create(ShopProductInfoRequest.class, PayShopProductInfo.class, false);
        beanCopier.copy(request, info, null);

        info.setBrandId(Integer.parseInt(request.getBrandId()));
        info.setSupplierId(Integer.parseInt(request.getSupplierId()));
        info.setTypeId(Integer.parseInt(request.getTypeId()));
        info.setStatus((byte)request.getStatus());
        info.setAddtime(new Date());
        info.setTitle(request.getTitle());
        info.setImgurl(request.getImgurl());
        info.setMoneyscope(request.getMoneyscope());

        int res = payShopProductInfoMapper.insertSelective(info);

        return new JpfResponseDto();
    }

    /**
     * 商品详情页
     */
    @Override
    public GetShopProductResponse getList(GetShopProductRequest request){

        PayShopProductExample example = new PayShopProductExample();
        PayShopProductExample.Criteria c = example.createCriteria();
        c.andProductInfoIdEqualTo(Integer.valueOf(request.getProductInfoId()));
        //商品状态：0=下架 1=上架
        c.andStatusEqualTo((byte)1);
        example.setOrderByClause("recharge_money ASC");

        List<PayShopProductCustom> list = payShopProductCustomMapper.selectContentByPid(example);
        if( list == null || list.size() < 0 ){
            return null;
        }

        GetShopProductResponse response = new GetShopProductResponse();
        List<ShopProductInfo> infos = new ArrayList<>();
        for(PayShopProductCustom one:list){
            ShopProductInfo shopProductInfo = new ShopProductInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopProductCustom.class,ShopProductInfo.class,false);
            beanCopier.copy(one,shopProductInfo,null);
            infos.add(shopProductInfo);
        }
        response.setList(infos);
        response.setCount(infos.size());
        return response;

    }



}
