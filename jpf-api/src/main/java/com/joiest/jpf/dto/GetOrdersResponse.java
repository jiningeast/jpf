package com.joiest.jpf.dto;

import com.joiest.jpf.entity.OrdersInfo;

import java.util.List;

public class GetOrdersResponse {

    List<OrdersInfo> list;

    private int count;

    public List<OrdersInfo> getList() {
        return list;
    }

    public void setList(List<OrdersInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
