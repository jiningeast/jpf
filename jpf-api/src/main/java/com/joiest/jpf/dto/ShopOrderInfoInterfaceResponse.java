package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopOrderInfoInterface;

import java.util.List;

public class ShopOrderInfoInterfaceResponse {

    private List<ShopOrderInfoInterface> list;

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ShopOrderInfoInterface> getList() {
        return list;
    }

    public void setList(List<ShopOrderInfoInterface> list) {
        this.list = list;
    }
}
