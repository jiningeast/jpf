package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopCompanyInfo;

import java.util.List;

public class GetShopCompanyResponse {

    List<ShopCompanyInfo> list;

    private int count;

    public List<ShopCompanyInfo> getList() {
        return list;
    }

    public void setList(List<ShopCompanyInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
