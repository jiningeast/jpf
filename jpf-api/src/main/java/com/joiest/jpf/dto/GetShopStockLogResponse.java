package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopStockLogInfo;

import java.util.List;

public class GetShopStockLogResponse {

    List<ShopStockLogInfo> list;

    private int count;

    public List<ShopStockLogInfo> getList() {
        return list;
    }

    public void setList(List<ShopStockLogInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
