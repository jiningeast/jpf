package com.joiest.jpf.dto;

import com.joiest.jpf.entity.CloudCompanyInfo;

import java.util.List;

public class GetCloudCompanysResponse {

    private int count;

    private List<CloudCompanyInfo> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CloudCompanyInfo> getList() {
        return list;
    }

    public void setList(List<CloudCompanyInfo> list) {
        this.list = list;
    }
}
