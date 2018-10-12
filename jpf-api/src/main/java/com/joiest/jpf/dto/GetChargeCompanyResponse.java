package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ChargeCompanyInfo;

import java.util.List;

public class GetChargeCompanyResponse {

    private int count;

    private List<ChargeCompanyInfo> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ChargeCompanyInfo> getList() {
        return list;
    }

    public void setList(List<ChargeCompanyInfo> list) {
        this.list = list;
    }
}
