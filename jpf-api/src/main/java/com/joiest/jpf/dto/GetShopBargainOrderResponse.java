package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopBargainOrderInfo;

import java.util.List;

public class GetShopBargainOrderResponse {

    private List<ShopBargainOrderInfo> list;

    private int count;

    public List<ShopBargainOrderInfo> getList() {
        return list;
    }

    public void setList(List<ShopBargainOrderInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
