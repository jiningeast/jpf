package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayShopBargainRechargeView implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * pay_shop_bargain_order.id
     */
    private Long boid;

    /**
     * pay_shop_bargain_order.order_no
     */
    private String orderNo;

    /**
     * 订单状态 1:未完成; 2:已完成
     */
    private Integer status;

    /**
     * 总额
     */
    private BigDecimal transferPrice;

    /**
     * 已拥有
     */
    private BigDecimal alreadyPrice;

    /**
     * 剩余
     */
    private BigDecimal remainPrice;

    /**
     * 
     */
    private Date addtime;

    /**
     * 
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBoid() {
        return boid;
    }

    public void setBoid(Long boid) {
        this.boid = boid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(BigDecimal transferPrice) {
        this.transferPrice = transferPrice;
    }

    public BigDecimal getAlreadyPrice() {
        return alreadyPrice;
    }

    public void setAlreadyPrice(BigDecimal alreadyPrice) {
        this.alreadyPrice = alreadyPrice;
    }

    public BigDecimal getRemainPrice() {
        return remainPrice;
    }

    public void setRemainPrice(BigDecimal remainPrice) {
        this.remainPrice = remainPrice;
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
        sb.append(", boid=").append(boid);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", status=").append(status);
        sb.append(", transferPrice=").append(transferPrice);
        sb.append(", alreadyPrice=").append(alreadyPrice);
        sb.append(", remainPrice=").append(remainPrice);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
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
        PayShopBargainRechargeView other = (PayShopBargainRechargeView) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBoid() == null ? other.getBoid() == null : this.getBoid().equals(other.getBoid()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getTransferPrice() == null ? other.getTransferPrice() == null : this.getTransferPrice().equals(other.getTransferPrice()))
            && (this.getAlreadyPrice() == null ? other.getAlreadyPrice() == null : this.getAlreadyPrice().equals(other.getAlreadyPrice()))
            && (this.getRemainPrice() == null ? other.getRemainPrice() == null : this.getRemainPrice().equals(other.getRemainPrice()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBoid() == null) ? 0 : getBoid().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getTransferPrice() == null) ? 0 : getTransferPrice().hashCode());
        result = prime * result + ((getAlreadyPrice() == null) ? 0 : getAlreadyPrice().hashCode());
        result = prime * result + ((getRemainPrice() == null) ? 0 : getRemainPrice().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}