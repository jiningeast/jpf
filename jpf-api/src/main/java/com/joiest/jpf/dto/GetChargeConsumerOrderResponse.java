package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ChargeConsumerOrderInfo;

import java.util.List;

public class GetChargeConsumerOrderResponse {

    private int count;

    private List<ChargeConsumerOrderInfo> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ChargeConsumerOrderInfo> getList() {
        return list;
    }

    public void setList(List<ChargeConsumerOrderInfo> list) {
        this.list = list;
    }
}
