package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.ModifyProductRequest;
import com.joiest.jpf.entity.MerchantTypeInfo;
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

    /**
     * 上下架
     * @param pname
     * @param status
     * @return
     */
    public JpfResponseDto upStatus(String pname,Byte status);

    /**
     * 获取单个产品详情
     * @param pid
     * @return
     */
    public ProductInfo getProduct(Long pid);

    /**
     * 产品编辑
     * @param request
     * @return
     */
    public JpfResponseDto modifyProduct(ModifyProductRequest request);
}