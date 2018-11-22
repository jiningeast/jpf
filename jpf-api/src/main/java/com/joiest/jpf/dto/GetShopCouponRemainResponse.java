package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ShopCouponActiveInfo;
import com.joiest.jpf.entity.ShopCouponRemainInfo;

import java.util.List;

public class GetShopCouponRemainResponse {

    List<ShopCouponActiveInfo> list;
    List<ShopCouponRemainInfo>saleYes;//可转让欣券
    List<ShopCouponRemainInfo>saleNo;//不可转让欣豆
    private int count;
    private int saleYesSum;
    private int saleNoSum;
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

    public List<ShopCouponRemainInfo> getSaleYes() {
        return saleYes;
    }

    public void setSaleYes(List<ShopCouponRemainInfo> saleYes) {
        this.saleYes = saleYes;
    }

    public List<ShopCouponRemainInfo> getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(List<ShopCouponRemainInfo> saleNo) {
        this.saleNo = saleNo;
    }

    public int getSaleYesSum() {
        return saleYesSum;
    }

    public void setSaleYesSum(int saleYesSum) {
        this.saleYesSum = saleYesSum;
    }

    public int getSaleNoSum() {
        return saleNoSum;
    }

    public void setSaleNoSum(int saleNoSum) {
        this.saleNoSum = saleNoSum;
    }
}
