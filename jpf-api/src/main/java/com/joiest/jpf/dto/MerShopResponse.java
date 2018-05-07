package com.joiest.jpf.dto;

import com.joiest.jpf.entity.MerchantShopInfo;

import java.util.List;

public class MerShopResponse {

    private List<MerchantShopInfo> list;

    private Integer count;

    public List<MerchantShopInfo> getList() {
        return list;
    }

    public void setList(List<MerchantShopInfo> list) {
        this.list = list;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
