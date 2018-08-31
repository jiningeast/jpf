package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopCompanyChargeInfo;

import java.util.List;

public class GetShopCompanyChargeResponse {

    List<ShopCompanyChargeInfo> list;

    private int count;

    public List<ShopCompanyChargeInfo> getList() {
        return list;
    }

    public void setList(List<ShopCompanyChargeInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
