package com.joiest.jpf.dto;

import com.joiest.jpf.entity.CloudDfTaskInterfaceInfo;

import java.util.List;

public class GetCloudDfTaskInterfaceResponse {

    /**
     * 当前可执行的任务列表
     */
    private List<CloudDfTaskInterfaceInfo> list;

    /**
     * 当前可执行任务总数
     */
    private int count;

    public List<CloudDfTaskInterfaceInfo> getList() {
        return list;
    }

    public void setList(List<CloudDfTaskInterfaceInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
