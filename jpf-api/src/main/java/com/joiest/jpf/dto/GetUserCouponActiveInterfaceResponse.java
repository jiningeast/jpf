package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopCouponActiveInterfaceInfo;

import java.math.BigDecimal;
import java.util.List;

public class GetUserCouponActiveInterfaceResponse {

    List<ShopCouponActiveInterfaceInfo> list;

    int count;

    //可用豆总额
    BigDecimal douTotal;

    public List<ShopCouponActiveInterfaceInfo> getList() {
        return list;
    }

    public void setList(List<ShopCouponActiveInterfaceInfo> list) {
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
