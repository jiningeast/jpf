package com.joiest.jpf.entity;


import java.util.List;

/**
 * @Auther: admin
 * @Date: 2018/11/29 11:16
 * @Description:
 */

public class CouponOrderList{

    private String totalNum;
    private String companyId;
    private String totalMoney;
    private String contractNo;
    private String contractId;
    private String couponMoney;
    private String serviceMoney;
    List<CouponOrderInfo> couponList;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public List<CouponOrderInfo> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CouponOrderInfo> couponList) {
        this.couponList = couponList;
    }

    public String getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(String couponMoney) {
        this.couponMoney = couponMoney;
    }

    public String getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(String serviceMoney) {
        this.serviceMoney = serviceMoney;
    }
}
