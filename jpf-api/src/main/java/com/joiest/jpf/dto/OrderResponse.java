package com.joiest.jpf.dto;

import com.joiest.jpf.entity.OrderInfo;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponse {

    private List<OrderInfo> list;

    private int count;

    /**
     * 总订单数
     */
    private Long allOrdersCount;

    /**
     * 总金额
     */
    private BigDecimal allOrdersMoney;

    /**
     * 退款金额
     */
    private BigDecimal allRefundMoney;

    public List<OrderInfo> getList() {
        return list;
    }

    public void setList(List<OrderInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getAllOrdersCount() {
        return allOrdersCount;
    }

    public void setAllOrdersCount(Long allOrdersCount) {
        this.allOrdersCount = allOrdersCount;
    }

    public BigDecimal getAllOrdersMoney() {
        return allOrdersMoney;
    }

    public void setAllOrdersMoney(BigDecimal allOrdersMoney) {
        this.allOrdersMoney = allOrdersMoney;
    }

    public BigDecimal getAllRefundMoney() {
        return allRefundMoney;
    }

    public void setAllRefundMoney(BigDecimal allRefundMoney) {
        this.allRefundMoney = allRefundMoney;
    }
}
