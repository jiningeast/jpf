package com.joiest.jpf.dto;

import com.joiest.jpf.entity.CloudCompanyMoneyInfo;

import java.util.List;

public class CloudCompanyMoneyResponse {


    private List<CloudCompanyMoneyInfo> list;

    private int count;

    public List<CloudCompanyMoneyInfo> getList() {
        return list;
    }

    public void setList(List<CloudCompanyMoneyInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
