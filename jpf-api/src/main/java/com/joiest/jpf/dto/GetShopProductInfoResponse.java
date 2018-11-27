package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopProductInfoInfo;

import java.util.List;

public class GetShopProductInfoResponse {

    List<ShopProductInfoInfo> list;

    int count;

    public List<ShopProductInfoInfo> getList() {
        return list;
    }

    public void setList(List<ShopProductInfoInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
