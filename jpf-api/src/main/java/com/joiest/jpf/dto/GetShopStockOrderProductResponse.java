package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopStockOrderProductInfo;

import java.util.List;

public class GetShopStockOrderProductResponse {

    List<ShopStockOrderProductInfo> list;

    private int count;

    public List<ShopStockOrderProductInfo> getList() {
        return list;
    }

    public void setList(List<ShopStockOrderProductInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
