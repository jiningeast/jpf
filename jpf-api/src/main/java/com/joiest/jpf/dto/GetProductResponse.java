package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ProductInfo;

import java.util.List;

public class GetProductResponse {

    private List<ProductInfo> list;

    private int count;

    public List<ProductInfo> getList() {
        return list;
    }

    public void setList(List<ProductInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
