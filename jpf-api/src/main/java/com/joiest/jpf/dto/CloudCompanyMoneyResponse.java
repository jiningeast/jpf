package com.joiest.jpf.dto;

import com.joiest.jpf.entity.CloudCompanyMoneyInfo;

import java.math.BigDecimal;
import java.util.List;

public class CloudCompanyMoneyResponse {


    private List<CloudCompanyMoneyInfo> list;

    private int count;

    /**
     * 总订单数
     */
    private Long allCount;

    /**
     * 总金额
     */
    private BigDecimal allMoney;
    /**
     * 总金额
     */
    private BigDecimal allServiceMoney;
    /**
     * 总金额
     */
    private BigDecimal addedMoney;
    /**
     * 总金额
     */
    private BigDecimal addedMoneyPay;
    /**
     * 总金额
     */
    private BigDecimal totalGross;

    public List<CloudCompanyMoneyInfo> getList() {
        return list;
    }

    public void setList(List<CloudCompanyMoneyInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getAllCount() {
        return allCount;
    }

    public void setAllCount(Long allCount) {
        this.allCount = allCount;
    }

    public BigDecimal getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(BigDecimal allMoney) {
        this.allMoney = allMoney;
    }

    public BigDecimal getAllServiceMoney() {
        return allServiceMoney;
    }

    public void setAllServiceMoney(BigDecimal allServiceMoney) {
        this.allServiceMoney = allServiceMoney;
    }

    public BigDecimal getAddedMoney() {
        return addedMoney;
    }

    public void setAddedMoney(BigDecimal addedMoney) {
        this.addedMoney = addedMoney;
    }

    public BigDecimal getAddedMoneyPay() {
        return addedMoneyPay;
    }

    public void setAddedMoneyPay(BigDecimal addedMoneyPay) {
        this.addedMoneyPay = addedMoneyPay;
    }

    public BigDecimal getTotalGross() {
        return totalGross;
    }

    public void setTotalGross(BigDecimal totalGross) {
        this.totalGross = totalGross;
    }
}
