package com.joiest.jpf.dto;

import com.joiest.jpf.common.custom.ShopIncomeConfirmationDetailResponse;

import java.util.List;

/**
 * 财务管理-确认收入订单欣券消费明细响应实体
 * @author zhouchaowei
 */
public class ShopOrderCouponConsumeDetailResponse {

    /**
     * 欣券消费信息明细列表
     */
    private List<ShopIncomeConfirmationDetailResponse> list;
    
    private int count;

    public List<ShopIncomeConfirmationDetailResponse> getList() {
        return list;
    }

    public void setList(List<ShopIncomeConfirmationDetailResponse> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
