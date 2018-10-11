package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.ShopProductInfo;
import com.joiest.jpf.entity.ShopProductInfoInfo;
import com.joiest.jpf.entity.UserInfo;

import java.util.List;

public interface ChargeProductServiceFacade {

    /**
     * shop_product list
     * @param request
     * @return
     */
    public GetChargeProductResponse getProductList(GetChargeProductRequest request);

    /**
     * 上下架
     */
    public JpfResponseDto upStatus(String id, Byte status, UserInfo userInfo);

    /**
     * 获取商品基础信息列表
     */
    public List<ShopProductInfoInfo> getProductInfoList();

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

}
