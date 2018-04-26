package com.joiest.jpf.dto;

import com.joiest.jpf.entity.OrderInfo;

import java.util.List;

public class OrderResponse {

    private List<OrderInfo> list;

    private int count;

    public List<OrderInfo> getList() {
        return list;
    }

    public void setList(List<OrderInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
