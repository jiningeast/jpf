package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetShopProductRequest;
import com.joiest.jpf.dto.GetShopProductResponse;
import com.joiest.jpf.dto.ModifyShopProductRequest;
import com.joiest.jpf.dto.ShopProductInfoRequest;
import com.joiest.jpf.entity.ShopProductInfo;
import com.joiest.jpf.entity.ShopProductInfoInfo;
import com.joiest.jpf.entity.UserInfo;

import java.util.List;

public interface ShopProductServiceFacade {

    /**
     * shop_product getRecords
     * @param request
     * @return
     */
    public GetShopProductResponse getProductList(GetShopProductRequest request);

    /**
     * 上下架
     */
    public JpfResponseDto upStatus(String id, Byte status,UserInfo userInfo);

    /**
     * 获取商品基础信息列表
     */
    List<ShopProductInfoInfo> getProductInfoList();

    /**
     * 添加商品
     */
    public JpfResponseDto addShopProduct(ModifyShopProductRequest request);

    /**
     * 获取商品 单个
     */
    public ShopProductInfo getShopProduct(String id);

    /**
     * 编辑商品
     */
    public JpfResponseDto modifyShopProduct(ModifyShopProductRequest request);

    /**
     * 添加商品基础信息 pay_shop_product_info
     */
    public JpfResponseDto addShopProductInfo(ShopProductInfoRequest request);

    /**
     * shop_product getRecords
     * @param request
     * @return
     */
    public GetShopProductResponse getList(GetShopProductRequest request);

}
