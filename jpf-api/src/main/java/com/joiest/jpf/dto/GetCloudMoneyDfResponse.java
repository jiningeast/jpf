package com.joiest.jpf.dto;

import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;

import java.util.List;

public class GetCloudMoneyDfResponse {

    private List<CloudDfMoneyInterfaceInfo> list;

    private Double monthTotal;

    public List<CloudDfMoneyInterfaceInfo> getList() {
        return list;
    }

    public void setList(List<CloudDfMoneyInterfaceInfo> list) {
        this.list = list;
    }

    public Double getMonthTotal() {
        return monthTotal;
    }

    public void setMonthTotal(Double monthTotal) {
        this.monthTotal = monthTotal;
    }
}
