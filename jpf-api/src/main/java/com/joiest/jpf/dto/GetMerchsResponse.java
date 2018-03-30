package com.joiest.jpf.dto;

import com.joiest.jpf.entity.MerchantInfo;

import java.util.List;

public class GetMerchsResponse {

    private List<MerchantInfo> list;

    private int count;

    public List<MerchantInfo> getList() {
        return list;
    }

    public void setList(List<MerchantInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
