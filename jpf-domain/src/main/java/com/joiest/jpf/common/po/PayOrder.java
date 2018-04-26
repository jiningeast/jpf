package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class PayOrder implements Serializable {
    /**
     * 自增ID
     */
    private BigInteger id;

    /**
     * 订单ID
     */
    private String orderid;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 用户uid
     */
    private Long uid;

    /**
     * 商品ID
     */
    private Long pid;

    /**
     * 支付方式：pay_merchants_type 
     */
    private Integer paytype;

    /**
     * 订单实际缴纳金额
     */
    private BigDecimal orderprice;

    /**
     * 订单实际金额
     */
    private BigDecimal orderselprice;

    /**
     * 订单商品数量
     */
    private Integer ordernum;

    /**
     * 分期付款序列（json格式）
     */
    private String ordername;

    /**
     * 支付时间
     */
    private Date paytime;

    /**
     * 0:未支付；1:支付成功；2:支付失败
     */
    private Byte orderstatus;

    /**
     * 1:正常订单；2:退单处理；3:退款撤销；4:退单受理中，5:退单处理完毕, 6:审核失败
     */
    private Byte singlestatus;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Long getMtsid() {
        return mtsid;
    }

    public void setMtsid(Long mtsid) {
        this.mtsid = mtsid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public BigDecimal getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(BigDecimal orderprice) {
        this.orderprice = orderprice;
    }

    public BigDecimal getOrderselprice() {
        return orderselprice;
    }

    public void setOrderselprice(BigDecimal orderselprice) {
        this.orderselprice = orderselprice;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername == null ? null : ordername.trim();
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Byte getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Byte orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Byte getSinglestatus() {
        return singlestatus;
    }

    public void setSinglestatus(Byte singlestatus) {
        this.singlestatus = singlestatus;
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
        sb.append(", orderid=").append(orderid);
        sb.append(", mtsid=").append(mtsid);
        sb.append(", uid=").append(uid);
        sb.append(", pid=").append(pid);
        sb.append(", paytype=").append(paytype);
        sb.append(", orderprice=").append(orderprice);
        sb.append(", orderselprice=").append(orderselprice);
        sb.append(", ordernum=").append(ordernum);
        sb.append(", ordername=").append(ordername);
        sb.append(", paytime=").append(paytime);
        sb.append(", orderstatus=").append(orderstatus);
        sb.append(", singlestatus=").append(singlestatus);
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
        PayOrder other = (PayOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getMtsid() == null ? other.getMtsid() == null : this.getMtsid().equals(other.getMtsid()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getPaytype() == null ? other.getPaytype() == null : this.getPaytype().equals(other.getPaytype()))
            && (this.getOrderprice() == null ? other.getOrderprice() == null : this.getOrderprice().equals(other.getOrderprice()))
            && (this.getOrderselprice() == null ? other.getOrderselprice() == null : this.getOrderselprice().equals(other.getOrderselprice()))
            && (this.getOrdernum() == null ? other.getOrdernum() == null : this.getOrdernum().equals(other.getOrdernum()))
            && (this.getOrdername() == null ? other.getOrdername() == null : this.getOrdername().equals(other.getOrdername()))
            && (this.getPaytime() == null ? other.getPaytime() == null : this.getPaytime().equals(other.getPaytime()))
            && (this.getOrderstatus() == null ? other.getOrderstatus() == null : this.getOrderstatus().equals(other.getOrderstatus()))
            && (this.getSinglestatus() == null ? other.getSinglestatus() == null : this.getSinglestatus().equals(other.getSinglestatus()))
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
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getMtsid() == null) ? 0 : getMtsid().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getPaytype() == null) ? 0 : getPaytype().hashCode());
        result = prime * result + ((getOrderprice() == null) ? 0 : getOrderprice().hashCode());
        result = prime * result + ((getOrderselprice() == null) ? 0 : getOrderselprice().hashCode());
        result = prime * result + ((getOrdernum() == null) ? 0 : getOrdernum().hashCode());
        result = prime * result + ((getOrdername() == null) ? 0 : getOrdername().hashCode());
        result = prime * result + ((getPaytime() == null) ? 0 : getPaytime().hashCode());
        result = prime * result + ((getOrderstatus() == null) ? 0 : getOrderstatus().hashCode());
        result = prime * result + ((getSinglestatus() == null) ? 0 : getSinglestatus().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}