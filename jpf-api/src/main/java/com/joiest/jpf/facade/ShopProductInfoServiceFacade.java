package com.joiest.jpf.facade;

import com.joiest.jpf.common.po.PayShopProductInfo;

import java.util.List;

/**
 * 欣豆市场-商品服务信息Service
 * @author admin 
 */
public interface ShopProductInfoServiceFacade {

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
}
