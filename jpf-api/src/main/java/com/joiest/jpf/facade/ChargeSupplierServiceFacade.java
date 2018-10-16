package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.ChargeSupplierRequest;
import com.joiest.jpf.entity.ChargeProductSupplierInfo;

import java.util.List;

public interface ChargeSupplierServiceFacade {

    /**
     * 获取所有供应商 getRecords
     */
    public List<ChargeProductSupplierInfo> getShopSupplierList();

    /**
     * 添加供应商
     */
    public JpfResponseDto addShopProductSupplier(ChargeSupplierRequest request);
}
