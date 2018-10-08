package com.joiest.jpf.dto;

import com.joiest.jpf.entity.CloudStaffMonthTotalInfo;

import java.util.List;

public class GetCloudStaffMonthTotalResponse {

    List<CloudStaffMonthTotalInfo> list;

    private int count;

    public List<CloudStaffMonthTotalInfo> getList() {
        return list;
    }

    public void setList(List<CloudStaffMonthTotalInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}