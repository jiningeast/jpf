package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ChargeOrderInfo;

import java.util.List;

public class GetChargeOrderResponse {

    private int count;

    private List<ChargeOrderInfo> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ChargeOrderInfo> getList() {
        return list;
    }

    public void setList(List<ChargeOrderInfo> list) {
        this.list = list;
    }
}
