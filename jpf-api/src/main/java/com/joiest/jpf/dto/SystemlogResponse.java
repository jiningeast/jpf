package com.joiest.jpf.dto;

import com.joiest.jpf.entity.SystemlogInfo;

import java.util.List;

public class SystemlogResponse {

    private List<SystemlogInfo> list;

    private int count;

    public List<SystemlogInfo> getList() {
        return list;
    }

    public void setList(List<SystemlogInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
