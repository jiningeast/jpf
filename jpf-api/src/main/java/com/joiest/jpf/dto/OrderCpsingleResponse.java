package com.joiest.jpf.dto;

import com.joiest.jpf.entity.OrderCpsingleInfo;

import java.util.List;

public class OrderCpsingleResponse {

    private List<OrderCpsingleInfo> list;

    private int count;

    public List<OrderCpsingleInfo> getList() {
        return list;
    }

    public void setList(List<OrderCpsingleInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
