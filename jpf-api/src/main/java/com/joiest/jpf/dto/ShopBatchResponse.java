package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopBatchInfo;

import java.util.List;

public class ShopBatchResponse {

    private List<ShopBatchInfo> list;

    private int count;

    public List<ShopBatchInfo> getList() {
        return list;
    }

    public void setList(List<ShopBatchInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
