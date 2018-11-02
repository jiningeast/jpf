package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayChargeProduct;
import com.joiest.jpf.common.po.PayChargeProductExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeProductMapper;
import com.joiest.jpf.dto.ChargeProductInterfaceRequest;
import com.joiest.jpf.dto.GetChargeProductRequest;
import com.joiest.jpf.dto.GetChargeProductResponse;
import com.joiest.jpf.entity.ChargeProductInfo;
import com.joiest.jpf.facade.ChargeProductServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ChargeProductServiceFacadeImpl implements ChargeProductServiceFacade {

    @Autowired
    private PayChargeProductMapper payChargeProductMapper;

    /**
     * 查询商品列表信息
     * */
    @Override
    public List<ChargeProductInfo> getList(PayChargeProduct record){

        PayChargeProductExample example = new PayChargeProductExample();
        PayChargeProductExample.Criteria c = example.createCriteria();
        //  getMobileType == 0 查询所有
        example.setOrderByClause("id DESC");
        if( record.getMobileType() !=null && record.getMobileType() != 0  ){
            c.andMobileTypeEqualTo(record.getMobileType());
        }
        if(record.getInterfaceType() != null && record.getInterfaceType() != 0)
            c.andInterfaceTypeEqualTo(record.getInterfaceType());

        c.andIsOnSaleEqualTo((byte)1);
        List<PayChargeProduct> list = payChargeProductMapper.selectByExample(example);
        if( list==null || list.isEmpty() ){
            return null;
        }
        List<ChargeProductInfo> infos = new ArrayList<>();
        for (PayChargeProduct one:list){
            ChargeProductInfo info = new ChargeProductInfo();
            BeanCopier beanCopier = BeanCopier.create(PayChargeProduct.class,ChargeProductInfo.class,false);
            beanCopier.copy(one,info,null);
            infos.add(info);
        }
        return infos;
    }
    /**
     * 获取商品信息 单个 通过商品id
     * */
    @Override
    public ChargeProductInfo getProductById(String id){

        PayChargeProduct payChargeProduct = payChargeProductMapper.selectByPrimaryKey(id);
        ChargeProductInfo chargeProductInfo = new ChargeProductInfo();
        if(payChargeProduct == null)
            return null;

        BeanCopier beanCopier = BeanCopier.create(PayChargeProduct.class,ChargeProductInfo.class,false);
        beanCopier.copy(payChargeProduct,chargeProductInfo,null);

        return chargeProductInfo;
    }
    /**
     * 查询商品列表信息，并返回数量
     */
    @Override
    public GetChargeProductResponse getProductList(GetChargeProductRequest request) {


        if (request.getPage() <= 0) {
            request.setPage(1);
        }
        if (request.getRows()<=0) {
            request.setRows(10);
        }
        PayChargeProductExample example = new PayChargeProductExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("addtime DESC");
        PayChargeProductExample.Criteria c = example.createCriteria();
        if ( StringUtils.isNotBlank(request.getName()) )
        {
            c.andNameLike("%" + request.getName().trim()+ "%");
        }
        if ( request.getIsOnSale() != null )
        {
            c.andIsOnSaleEqualTo(request.getIsOnSale());
        }

        GetChargeProductResponse response = new GetChargeProductResponse();
        List<PayChargeProduct> list = payChargeProductMapper.selectByExample(example);
        int count = payChargeProductMapper.countByExample(example);
        response.setCount(count);
        List<ChargeProductInfo> resultList = new ArrayList<>();
        for (PayChargeProduct one : list)
        {
            ChargeProductInfo info = new ChargeProductInfo();
            BeanCopier beanCopier = BeanCopier.create(PayChargeProduct.class, ChargeProductInfo.class, false);
            beanCopier.copy(one, info, null);

            resultList.add(info);
        }
        response.setList(resultList);

        return response;
    }


    /**
     * 查询商品列表信息，并返回数量
     */
    @Override
    public GetChargeProductResponse getProductListInterface(ChargeProductInterfaceRequest request) {


        if ( request.getPageSize() ==null || Long.parseLong(request.getPageSize()) <= 0 || Long.parseLong(request.getPageSize()) > 10 )
        {
            request.setPageSize("10");
        }else{
            request.setPageSize(request.getPageSize());
        }

        if ( request.getPage() ==null || Long.parseLong(request.getPage()) <= 0 )
        {
            request.setPage("1");
        }
        PayChargeProductExample example = new PayChargeProductExample();
        example.setPageNo(Long.parseLong(request.getPage()));
        example.setPageSize(Long.parseLong(request.getPageSize()));
        example.setOrderByClause("addtime DESC");
        PayChargeProductExample.Criteria c = example.createCriteria();

        if ( StringUtils.isNotBlank(request.getKeyword()) )
        {
            c.andNameLike("%" + request.getKeyword().trim()+ "%");
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
        if(request.getIsOnSale()!=null && !request.getIsOnSale().equals("")){
            c.andIsOnSaleEqualTo(Byte.valueOf(request.getIsOnSale()));
        }
        GetChargeProductResponse response = new GetChargeProductResponse();
        List<PayChargeProduct> list = payChargeProductMapper.selectByExample(example);
        int count = payChargeProductMapper.countByExample(example);
        response.setCount(count);
        List<ChargeProductInfo> resultList = new ArrayList<>();
        for (PayChargeProduct one : list)
        {
            ChargeProductInfo info = new ChargeProductInfo();
            BeanCopier beanCopier = BeanCopier.create(PayChargeProduct.class, ChargeProductInfo.class, false);
            beanCopier.copy(one, info, null);

            resultList.add(info);
        }
        response.setList(resultList);

        return response;
    }

    /**
     * 添加商品
     */
    @Override
    public int addChargeProduct(ChargeProductInfo info) {
        String ofProductId = StringUtils.stripToNull(info.getOfProductId());
        String wnProductId = StringUtils.stripToNull(info.getWnProductId());
        info.setOfProductId(ofProductId);
        info.setWnProductId(wnProductId);

        PayChargeProduct payChargeProduct = new PayChargeProduct();

        BeanCopier beanCopier = BeanCopier.create(ChargeProductInfo.class, PayChargeProduct.class, false);
        beanCopier.copy(info, payChargeProduct, null);

        int insertRes = payChargeProductMapper.insertSelective(payChargeProduct);
        if ( insertRes > 0 ){
            return insertRes;
        }else{
            return -1;
        }
    }

    /**
     * 获取商品 单个
     */
    @Override
    public ChargeProductInfo getChargeProduct(String id) {
        if ( StringUtils.isBlank(id) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER,"id不能为空");
        }
        PayChargeProductExample example = new PayChargeProductExample();
        PayChargeProductExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(id);
        PayChargeProduct payChargeProduct = payChargeProductMapper.selectByPrimaryKey(id);

        if ( payChargeProduct == null )
        {
            return null;
        }
        ChargeProductInfo info = new ChargeProductInfo();
        BeanCopier beanCopier = BeanCopier.create(PayChargeProduct.class, ChargeProductInfo.class, false);
        beanCopier.copy(payChargeProduct, info, null);

        return info;
    }

    /**
     * 编辑商品
     */
    @Override
    public JpfResponseDto modifyChargeProduct(ChargeProductInfo info) {
        String ofProductId = StringUtils.stripToNull(info.getOfProductId());
        String wnProductId = StringUtils.stripToNull(info.getWnProductId());
        info.setOfProductId(ofProductId);
        info.setWnProductId(wnProductId);

        PayChargeProduct payChargeProduct = new PayChargeProduct();

        BeanCopier beanCopier = BeanCopier.create(ChargeProductInfo.class, PayChargeProduct.class, false);
        beanCopier.copy(info, payChargeProduct, null);

        int res = payChargeProductMapper.updateByPrimaryKeySelective(payChargeProduct);
        return new JpfResponseDto();
    }

    /**
     * 编辑商品 上游价格
     */
    public int upChargeProduct(ChargeProductInfo chargeProductInfo){

        PayChargeProduct payChargeProduct = new PayChargeProduct();

        BeanCopier beanCopier = BeanCopier.create(ChargeProductInfo.class, PayChargeProduct.class, false);
        beanCopier.copy(chargeProductInfo, payChargeProduct, null);

        return payChargeProductMapper.updateByPrimaryKeySelective(payChargeProduct);
    }

}
