package com.joiest.jpf.dto;

import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;

import java.math.BigDecimal;
import java.util.List;

public class GetCloudMoneyDfResponse {

    private int count;

    private List<CloudDfMoneyInterfaceInfo> list;

    private BigDecimal monthTotal;

    public List<CloudDfMoneyInterfaceInfo> getList() {
        return list;
    }

    public void setList(List<CloudDfMoneyInterfaceInfo> list) {
        this.list = list;
    }

    public BigDecimal getMonthTotal() {
        return monthTotal;
    }

    public void setMonthTotal(BigDecimal monthTotal) {
        this.monthTotal = monthTotal;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
