package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopAdInfo;

import java.util.List;

public class GetShopAdInterfaceResponse {

    private List<ShopAdInfo> list;

    private int count;

    public List<ShopAdInfo> getList() {
        return list;
    }

    public void setList(List<ShopAdInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
