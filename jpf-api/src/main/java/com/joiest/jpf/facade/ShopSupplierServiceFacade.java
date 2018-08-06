package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.ShopSupplierRequest;
import com.joiest.jpf.entity.ShopSupplierInfo;

import java.util.List;

public interface ShopSupplierServiceFacade {

    /**
     * 获取所有供应商 list
     */
    public List<ShopSupplierInfo> getShopSupplierList();

    /**
     * 添加供应商
     */
    public JpfResponseDto addShopProductSupplier(ShopSupplierRequest request);
}
