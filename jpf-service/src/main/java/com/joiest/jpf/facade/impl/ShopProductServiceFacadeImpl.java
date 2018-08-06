package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.constant.EnumConstants;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopProduct;
import com.joiest.jpf.common.po.PayShopProductExample;
import com.joiest.jpf.common.po.PayShopProductInfo;
import com.joiest.jpf.common.po.PayShopProductInfoExample;
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

import java.util.*;

public class ShopProductServiceFacadeImpl implements ShopProductServiceFacade {

    @Autowired
    private PayShopProductMapper payShopProductMapper;

    @Autowired
    private PayShopProductInfoMapper payShopProductInfoMapper;

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
        example.setOrderByClause("p.addtime DESC");
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
        c.andStatusEqualTo((byte)1);
        List<PayShopProductInfo> list = payShopProductInfoMapper.selectByExample(example);
        if ( list.isEmpty() || list == null )
        {
            return null;
        }
        List<ShopProductInfoInfo> resultList = new ArrayList<>();
        for (PayShopProductInfo one : list)
        {
            ShopProductInfoInfo info = new ShopProductInfoInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopProductInfo.class, ShopProductInfoInfo.class, false);
            beanCopier.copy(one, info, null);
            Map<String,String> resultMap = new HashMap<>();
            resultMap.put("id", one.getId().toString());
            StringBuilder sb = new StringBuilder();
            sb.append("商品类型:");
            sb.append(one.getTypeName());
            sb.append(";供应商:");
            sb.append(one.getSupplierName());
            sb.append(";品牌:");
            sb.append(one.getBrandName());
            resultMap.put("str", sb.toString());
            info.setContent(sb.toString());
            info.setContentId(one.getId().toString());
            resultList.add(info);
        }
        return resultList;
    }

    @Override
    public JpfResponseDto addShopProduct(ModifyShopProductRequest request) {
        PayShopProduct payShopProduct = new PayShopProduct();
        payShopProduct.setProductInfoId(Integer.valueOf(request.getProductInfoId()));
        payShopProduct.setName(request.getName());
        payShopProduct.setImage(request.getImage());
        payShopProduct.setDou(Integer.parseInt(request.getDou()));
        payShopProduct.setIntro(request.getIntro());
        payShopProduct.setStored(Integer.parseInt(request.getStored()));
        payShopProduct.setStoredSafe(Integer.parseInt(request.getStoredSafe()));
        payShopProduct.setAddtime(new Date());
        payShopProduct.setOperatorId(request.getOperatorId());
        payShopProduct.setOperatorName(request.getOperatorName());
        int res = payShopProductMapper.insertSelective(payShopProduct);
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
        PayShopProduct payShopProduct = payShopProductMapper.selectByPrimaryKey(id);

        if ( payShopProduct == null )
        {
            return null;
        }
        ShopProductInfo info = new ShopProductInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopProduct.class, ShopProductInfo.class, false);
        beanCopier.copy(payShopProduct, info, null);

        return info;
    }

    @Override
    public JpfResponseDto modifyShopProduct(ModifyShopProductRequest request) {
        PayShopProduct payShopProduct = new PayShopProduct();
        payShopProduct.setId(request.getId());
        payShopProduct.setProductInfoId(Integer.valueOf(request.getProductInfoId()));
        payShopProduct.setName(request.getName());
        payShopProduct.setImage(request.getImage());
        payShopProduct.setDou(Integer.parseInt(request.getDou()));
        payShopProduct.setIntro(request.getIntro());
        payShopProduct.setStored(Integer.parseInt(request.getStored()));
        payShopProduct.setStoredSafe(Integer.parseInt(request.getStoredSafe()));
        payShopProduct.setOperatorId(request.getOperatorId());
        payShopProduct.setOperatorName(request.getOperatorName());
        payShopProduct.setStatus(request.getStatus());
        payShopProduct.setUpdatetime(new Date());
        int res = payShopProductMapper.updateByPrimaryKeySelective(payShopProduct);
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
        info.setStatus((byte)1);
        info.setAddtime(new Date());

        int res = payShopProductInfoMapper.insertSelective(info);

        return new JpfResponseDto();
    }
}
