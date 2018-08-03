package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopOrderInfo;
import java.util.List;

public class GetShopOrderResponse {

    List<ShopOrderInfo> list;

    private int count;

    public List<ShopOrderInfo> getList() {
        return list;
    }

    public void setList(List<ShopOrderInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
