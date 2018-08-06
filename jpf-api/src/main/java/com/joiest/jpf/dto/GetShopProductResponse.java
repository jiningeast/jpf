package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopProductInfo;

import java.util.List;

public class GetShopProductResponse {

    List<ShopProductInfo> list;

    Long count;

    public List<ShopProductInfo> getList() {
        return list;
    }

    public void setList(List<ShopProductInfo> list) {
        this.list = list;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
