package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopBatchCouponInfo;

import java.util.List;

public class ShopBatchCouponResponse {

    private List<ShopBatchCouponInfo> list;

    private int count;

    public List<ShopBatchCouponInfo> getList() {
        return list;
    }

    public void setList(List<ShopBatchCouponInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
