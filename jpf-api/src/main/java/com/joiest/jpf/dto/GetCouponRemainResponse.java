package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopCouponRemainInfo;

import java.math.BigDecimal;
import java.util.List;

public class GetCouponRemainResponse {

    List<ShopCouponRemainInfo> list;

    //条数
    int count;

    //可用豆总额
    BigDecimal douTotal;

    public List<ShopCouponRemainInfo> getList() {
        return list;
    }

    public void setList(List<ShopCouponRemainInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getDouTotal() {
        return douTotal;
    }

    public void setDouTotal(BigDecimal douTotal) {
        this.douTotal = douTotal;
    }
}
