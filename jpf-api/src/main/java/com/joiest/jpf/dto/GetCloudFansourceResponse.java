package com.joiest.jpf.dto;

import com.joiest.jpf.entity.CloudFanSourceInfo;

import java.util.List;

public class GetCloudFansourceResponse {

    List<CloudFanSourceInfo> list;

    private int count;

    public List<CloudFanSourceInfo> getList() {
        return list;
    }

    public void setList(List<CloudFanSourceInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
