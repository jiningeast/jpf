package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopStockCardInfo;

import java.util.List;

public class GetShopStockCardResponse {

    List<ShopStockCardInfo> list;
    private int count;

    public List<ShopStockCardInfo> getList() {
        return list;
    }

    public void setList(List<ShopStockCardInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
