package com.joiest.jpf.dto;

import com.joiest.jpf.entity.CloudDfMoneyFreezeInfo;

import java.util.List;

public class GetCloudDfMoneyFreezeResponse {

    private int count;

    private List<CloudDfMoneyFreezeInfo> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CloudDfMoneyFreezeInfo> getList() {
        return list;
    }

    public void setList(List<CloudDfMoneyFreezeInfo> list) {
        this.list = list;
    }
}
