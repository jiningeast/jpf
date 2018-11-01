package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayShopBargainRechargeOrder implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 订单号：OD+3位随机数+毫秒时间戳+3位随机数
     */
    private String orderNo;

    /**
     * bargain_order.id
     */
    private Long boid;

    /**
     * 订单类型 1:中国石化; 2中国石油; 3话费充值
     */
    private Integer orderType;

    /**
     * 第三方订单号
     */
    private String foreignOrderNo;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 面额
     */
    private BigDecimal facePrice;

    /**
     * 数量
     */
    private Integer amt;

    /**
     * 订单总金额
     */
    private BigDecimal amount;

    /**
     * 充值号，手机号
     */
    private String chargeNo;

    /**
     * 敬恒的账号区分
     */
    private Integer userId;

    /**
     * 订单状态 0=待支付 1=已支付 2=支付失败 3=已取消
     */
    private Integer status;

    /**
     * 充值状态
     */
    private String rechargeStatus;

    /**
     * 充值时间
     */
    private Date rechargeTime;

    /**
     * 下单时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 1:未绑定; 2:已绑定
     */
    private Integer infoStatus;

    /**
     * 订单信息
     */
    private String module;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Long getBoid() {
        return boid;
    }

    public void setBoid(Long boid) {
        this.boid = boid;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getForeignOrderNo() {
        return foreignOrderNo;
    }

    public void setForeignOrderNo(String foreignOrderNo) {
        this.foreignOrderNo = foreignOrderNo == null ? null : foreignOrderNo.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getFacePrice() {
        return facePrice;
    }

    public void setFacePrice(BigDecimal facePrice) {
        this.facePrice = facePrice;
    }

    public Integer getAmt() {
        return amt;
    }

    public void setAmt(Integer amt) {
        this.amt = amt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo == null ? null : chargeNo.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRechargeStatus() {
        return rechargeStatus;
    }

    public void setRechargeStatus(String rechargeStatus) {
        this.rechargeStatus = rechargeStatus == null ? null : rechargeStatus.trim();
    }

    public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
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

    public Integer getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(Integer infoStatus) {
        this.infoStatus = infoStatus;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
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
        sb.append(", boid=").append(boid);
        sb.append(", orderType=").append(orderType);
        sb.append(", foreignOrderNo=").append(foreignOrderNo);
        sb.append(", itemName=").append(itemName);
        sb.append(", price=").append(price);
        sb.append(", facePrice=").append(facePrice);
        sb.append(", amt=").append(amt);
        sb.append(", amount=").append(amount);
        sb.append(", chargeNo=").append(chargeNo);
        sb.append(", userId=").append(userId);
        sb.append(", status=").append(status);
        sb.append(", rechargeStatus=").append(rechargeStatus);
        sb.append(", rechargeTime=").append(rechargeTime);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", infoStatus=").append(infoStatus);
        sb.append(", module=").append(module);
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
        PayShopBargainRechargeOrder other = (PayShopBargainRechargeOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getBoid() == null ? other.getBoid() == null : this.getBoid().equals(other.getBoid()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getForeignOrderNo() == null ? other.getForeignOrderNo() == null : this.getForeignOrderNo().equals(other.getForeignOrderNo()))
            && (this.getItemName() == null ? other.getItemName() == null : this.getItemName().equals(other.getItemName()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getFacePrice() == null ? other.getFacePrice() == null : this.getFacePrice().equals(other.getFacePrice()))
            && (this.getAmt() == null ? other.getAmt() == null : this.getAmt().equals(other.getAmt()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getChargeNo() == null ? other.getChargeNo() == null : this.getChargeNo().equals(other.getChargeNo()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRechargeStatus() == null ? other.getRechargeStatus() == null : this.getRechargeStatus().equals(other.getRechargeStatus()))
            && (this.getRechargeTime() == null ? other.getRechargeTime() == null : this.getRechargeTime().equals(other.getRechargeTime()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getInfoStatus() == null ? other.getInfoStatus() == null : this.getInfoStatus().equals(other.getInfoStatus()))
            && (this.getModule() == null ? other.getModule() == null : this.getModule().equals(other.getModule()));
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
        result = prime * result + ((getBoid() == null) ? 0 : getBoid().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getForeignOrderNo() == null) ? 0 : getForeignOrderNo().hashCode());
        result = prime * result + ((getItemName() == null) ? 0 : getItemName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getFacePrice() == null) ? 0 : getFacePrice().hashCode());
        result = prime * result + ((getAmt() == null) ? 0 : getAmt().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getChargeNo() == null) ? 0 : getChargeNo().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRechargeStatus() == null) ? 0 : getRechargeStatus().hashCode());
        result = prime * result + ((getRechargeTime() == null) ? 0 : getRechargeTime().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getInfoStatus() == null) ? 0 : getInfoStatus().hashCode());
        result = prime * result + ((getModule() == null) ? 0 : getModule().hashCode());
        return result;
    }
}