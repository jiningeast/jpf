package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayShopProductInterfaceCustom;

import java.util.List;

public interface PayShopProductInterfaceCustomMapper {

    List<PayShopProductInterfaceCustom> getProductListCustomByBrand(Integer brandId);

}