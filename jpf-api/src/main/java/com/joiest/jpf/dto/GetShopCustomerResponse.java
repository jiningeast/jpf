package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopCustomerInfo;

import java.util.List;

public class GetShopCustomerResponse {

    List<ShopCustomerInfo> list;

    private int count;

    public List<ShopCustomerInfo> getList() {
        return list;
    }

    public void setList(List<ShopCustomerInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
