package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ChargeBalanceInfo;

import java.util.List;

public class ChargeBalanceResponse {


    private List<ChargeBalanceInfo> list;

    private int count;

    public List<ChargeBalanceInfo> getList() {
        return list;
    }

    public void setList(List<ChargeBalanceInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
