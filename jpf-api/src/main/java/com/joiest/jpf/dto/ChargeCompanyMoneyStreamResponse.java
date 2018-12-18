package com.joiest.jpf.dto;

import com.joiest.jpf.entity.ChargeCompanyMoneyStreamInfo;

import java.math.BigDecimal;
import java.util.List;

public class ChargeCompanyMoneyStreamResponse {


    private List<ChargeCompanyMoneyStreamInfo> list;

    private int count;

    /**
     * 商户总余额
     */
    private BigDecimal balance;

    /**
     * 商户充值总金额
     */
    private BigDecimal totalMoney;

    public List<ChargeCompanyMoneyStreamInfo> getList() {
        return list;
    }

    public void setList(List<ChargeCompanyMoneyStreamInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}
