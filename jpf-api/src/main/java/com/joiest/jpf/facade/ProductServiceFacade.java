package com.joiest.jpf.facade;

import com.joiest.jpf.entity.ProductInfo;

import java.util.List;

public interface ProductServiceFacade {
    /**
     * 获取用户列表
     * @param pname
     * @param status
     * @return
     */
    public List<ProductInfo> getProductsList(Long mtsid, String pname, Byte status, long pageNo, long pageSize);

    /**
     * 获取用户列表统计
     * @param pname
     * @param status
     * @return
     */
    public int getProductsCount(Long mtsid, String pname, Byte status);

}