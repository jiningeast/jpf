package com.joiest.jpf.dto;

import com.joiest.jpf.entity.RechargeNeedInfo;

import java.util.List;

public class GetRechargeNeedResponse {

    private List<RechargeNeedInfo> info;

    private int count;

    public List<RechargeNeedInfo> getInfo() {
        return info;
    }

    public void setInfo(List<RechargeNeedInfo> info) {
        this.info = info;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
