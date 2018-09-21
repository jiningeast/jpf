package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ShopBargainRechargeViewInfo {
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
        this.orderNo = orderNo;
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
}
