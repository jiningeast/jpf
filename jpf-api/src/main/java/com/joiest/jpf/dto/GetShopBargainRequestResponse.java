package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopBargainRequestInfo;

import java.util.List;

public class GetShopBargainRequestResponse {

    private List<ShopBargainRequestInfo> list;

    private int count;

    public List<ShopBargainRequestInfo> getList() {
        return list;
    }

    public void setList(List<ShopBargainRequestInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
