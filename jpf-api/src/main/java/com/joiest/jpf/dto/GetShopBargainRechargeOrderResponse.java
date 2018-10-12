package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopBargainRechargeOrderInfo;

import java.util.List;

public class GetShopBargainRechargeOrderResponse {

    private int count;

    private List<ShopBargainRechargeOrderInfo> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ShopBargainRechargeOrderInfo> getList() {
        return list;
    }

    public void setList(List<ShopBargainRechargeOrderInfo> list) {
        this.list = list;
    }
}
