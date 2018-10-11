package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ChargeProductInfo;

import java.util.List;

public class GetChargeProductResponse {

    List<ChargeProductInfo> list;

    int count;

    public List<ChargeProductInfo> getList() {
        return list;
    }

    public void setList(List<ChargeProductInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
