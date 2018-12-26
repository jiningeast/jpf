package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayShopCouponOrder implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 订单号：XQ+时间戳+随机6位
     */
    private String orderNo;

    /**
     * 订单总额
     */
    private BigDecimal totalMoney;

    /**
     * 状态 0 已申请  1 已完成  2 已取消 3 已删除
     */
    private Byte status;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    /**
     * 发放金额
     */
    private BigDecimal couponMoney;

    /**
     * 服务费
     */
    private BigDecimal serviceMoney;

    /**
     * 服务内容
     */
    private String serviceContent;

    /**
     * 合同id
     */
    private String contractId;

    /**
     * 合同号
     */
    private String contractNo;

    /**
     * 企业id
     */
    private String companyId;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 人数
     */
    private Integer personNum;

    /**
     * 订单总的条数
     */
    private Integer totalNum;

    /**
     * 订单的余额
     */
    private BigDecimal balance;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public BigDecimal getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(BigDecimal couponMoney) {
        this.couponMoney = couponMoney;
    }

    public BigDecimal getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(BigDecimal serviceMoney) {
        this.serviceMoney = serviceMoney;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent == null ? null : serviceContent.trim();
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId == null ? null : contractId.trim();
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     *
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", totalMoney=").append(totalMoney);
        sb.append(", status=").append(status);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", couponMoney=").append(couponMoney);
        sb.append(", serviceMoney=").append(serviceMoney);
        sb.append(", serviceContent=").append(serviceContent);
        sb.append(", contractId=").append(contractId);
        sb.append(", contractNo=").append(contractNo);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", personNum=").append(personNum);
        sb.append(", totalNum=").append(totalNum);
        sb.append(", balance=").append(balance);
        sb.append("]");
        return sb.toString();
    }

    /**
     *
     * @param that
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PayShopCouponOrder other = (PayShopCouponOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getTotalMoney() == null ? other.getTotalMoney() == null : this.getTotalMoney().equals(other.getTotalMoney()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getCouponMoney() == null ? other.getCouponMoney() == null : this.getCouponMoney().equals(other.getCouponMoney()))
            && (this.getServiceMoney() == null ? other.getServiceMoney() == null : this.getServiceMoney().equals(other.getServiceMoney()))
            && (this.getServiceContent() == null ? other.getServiceContent() == null : this.getServiceContent().equals(other.getServiceContent()))
            && (this.getContractId() == null ? other.getContractId() == null : this.getContractId().equals(other.getContractId()))
            && (this.getContractNo() == null ? other.getContractNo() == null : this.getContractNo().equals(other.getContractNo()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getPersonNum() == null ? other.getPersonNum() == null : this.getPersonNum().equals(other.getPersonNum()))
            && (this.getTotalNum() == null ? other.getTotalNum() == null : this.getTotalNum().equals(other.getTotalNum()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getTotalMoney() == null) ? 0 : getTotalMoney().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getCouponMoney() == null) ? 0 : getCouponMoney().hashCode());
        result = prime * result + ((getServiceMoney() == null) ? 0 : getServiceMoney().hashCode());
        result = prime * result + ((getServiceContent() == null) ? 0 : getServiceContent().hashCode());
        result = prime * result + ((getContractId() == null) ? 0 : getContractId().hashCode());
        result = prime * result + ((getContractNo() == null) ? 0 : getContractNo().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getPersonNum() == null) ? 0 : getPersonNum().hashCode());
        result = prime * result + ((getTotalNum() == null) ? 0 : getTotalNum().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        return result;
    }
}