package com.joiest.jpf.dto;


import com.joiest.jpf.common.po.PayShopBargainRechargeOrder;

import java.math.BigDecimal;
import java.util.List;

public class MarchingDataRequest {

    /**
     * 匹配的数据
     */
    private List<PayShopBargainRechargeOrder> list;

    /**
     * 企业的名字
     */
    private String companyName;
    /**
     * 企业余额
     */
    private BigDecimal money;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public List<PayShopBargainRechargeOrder> getList() {
        return list;
    }

    public void setList(List<PayShopBargainRechargeOrder> list) {
        this.list = list;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
