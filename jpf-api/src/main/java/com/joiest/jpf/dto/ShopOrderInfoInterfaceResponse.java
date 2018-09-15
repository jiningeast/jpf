package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopOrderInterfaceInfo;

import java.util.List;

public class ShopOrderInfoInterfaceResponse {

    private List<ShopOrderInterfaceInfo> list;

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ShopOrderInterfaceInfo> getList() {
        return list;
    }

    public void setList(List<ShopOrderInterfaceInfo> list) {
        this.list = list;
    }
}
