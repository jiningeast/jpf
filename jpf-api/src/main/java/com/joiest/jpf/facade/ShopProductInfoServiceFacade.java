package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopProductInfo;
import com.joiest.jpf.dto.GetShopProductInfoRequest;
import com.joiest.jpf.dto.GetShopProductInfoResponse;
import com.joiest.jpf.dto.ShopProductInfoRequest;
import com.joiest.jpf.entity.ShopProductInfoInfo;

import java.util.List;

/**
 * 欣豆市场-商品服务信息Service
 * @author admin
 */
public interface ShopProductInfoServiceFacade {

    /**
     * 后台基础信息列表
     * @param request 请求数据类
     * @return
     */
    GetShopProductInfoResponse getRecords(GetShopProductInfoRequest request);

    /**
     * 获取单条基础信息
     * @param id 请求数据类
     * @return
     */
    ShopProductInfoInfo getOne(String id);


    /**
     * 获取旅游服务商品列表
     * @param payShopSupplierId 供应商Id
     * @return
     */
    List<PayShopProductInfo> getProductInfoList(String payShopSupplierId);

    /**
     * 获取旅游服务商品列表数据条数
     * @param decoderPayShopSupplierId 供应商Id
     * @return
     */
    int getProductInfoCount(String decoderPayShopSupplierId);

    /**
     * 编辑商品基础信息 pay_shop_product_info
     */
    public JpfResponseDto editShopProductInfo(ShopProductInfoRequest request);

}
