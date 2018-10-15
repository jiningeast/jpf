package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ChargeCompanyChargeInfo;

import java.util.List;

public class GetChargeCompanyChargeResponse {

    private int count;

    private List<ChargeCompanyChargeInfo> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ChargeCompanyChargeInfo> getList() {
        return list;
    }

    public void setList(List<ChargeCompanyChargeInfo> list) {
        this.list = list;
    }
}
