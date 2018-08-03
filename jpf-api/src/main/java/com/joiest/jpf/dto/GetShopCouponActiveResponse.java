package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopCouponActiveInfo;
import java.util.List;

public class GetShopCouponActiveResponse {

    List<ShopCouponActiveInfo> list;

    private int count;

    public List<ShopCouponActiveInfo> getList() {
        return list;
    }

    public void setList(List<ShopCouponActiveInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
