package com.joiest.jpf.dto;

import com.joiest.jpf.entity.CloudTaskInfo;

import java.util.List;

public class CloudTaskResponse {

    private List<CloudTaskInfo> list;

    private int count;

    public List<CloudTaskInfo> getList() {
        return list;
    }

    public void setList(List<CloudTaskInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
