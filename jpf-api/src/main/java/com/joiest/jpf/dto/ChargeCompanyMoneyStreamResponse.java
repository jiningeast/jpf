package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ChargeCompanyMoneyStreamInfo;

import java.util.List;

public class ChargeCompanyMoneyStreamResponse {


    private List<ChargeCompanyMoneyStreamInfo> list;

    private int count;

    public List<ChargeCompanyMoneyStreamInfo> getList() {
        return list;
    }

    public void setList(List<ChargeCompanyMoneyStreamInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
