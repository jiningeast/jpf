package com.joiest.jpf.dto;

import com.joiest.jpf.common.po.PayShopStockOrder;
import com.joiest.jpf.entity.ShopOrderInfo;
import com.joiest.jpf.entity.ShopStockOrderInfo;

import java.util.List;

public class GetShopStockOrderResponse {

    List<ShopStockOrderInfo> list;

    private int count;

    public List<ShopStockOrderInfo> getList() {
        return list;
    }

    public void setList(List<ShopStockOrderInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
