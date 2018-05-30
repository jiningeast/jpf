package com.joiest.jpf.common.custom;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class PayOrderInterfaceCustom implements Serializable {
    /**
     * 自增ID
     */
    private BigInteger id;

    /**
     * 0：旅游分期，1：保险公司
     */
    private Byte ordertype;

    /**
     * 订单ID
     */
    private String orderid;

    /**
     * 外来订单号
     */
    private String foreignOrderid;

    /**
     * 签约订单号，对应pay_order_cp的orderid字段
     */
    private Long signOrderid;

    /**
     * 外来请求字符串
     */
    private String foreignRequest;

    /**
     * 返回地址
     */
    private String returnUrl;

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
     * 产品订单实际金额
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
     * 1:正常订单；2:退单处理；3:退款撤销；4:运营已审核,待财务审核，5:财务已审核，银联退款中, 6:审核驳回,7:银联退款成功,8:银联退款失败
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

    /**
     * 异步回调地址
     */
    private String notifyUrl;
    //=========产品信息 Begin==============
    /**
     * 产品ID
     */
    private Long bpid;

    /**
     * 产品名称
     */
    private String pname;

    /**
     * 产品简介
     */
    private String pintro;

    /**
     * 产品价格
     */
    private BigDecimal pmoney;

    /**
     * 产品图片地址
     */
    private String pdpicture;
    //=========产品信息 End==============
    //=========支付方式 Begin==============

    private String cat;

    //=========支付方式 End==============

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

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPintro() {
        return pintro;
    }

    public void setPintro(String pintro) {
        this.pintro = pintro;
    }

    public BigDecimal getPmoney() {
        return pmoney;
    }

    public void setPmoney(BigDecimal pmoney) {
        this.pmoney = pmoney;
    }

    public String getPdpicture() {
        return pdpicture;
    }

    public void setPdpicture(String pdpicture) {
        this.pdpicture = pdpicture;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getBpid() {
        return bpid;
    }

    public void setBpid(Long bpid) {
        this.bpid = bpid;
    }

    public Byte getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Byte ordertype) {
        this.ordertype = ordertype;
    }

    public String getForeignOrderid() {
        return foreignOrderid;
    }

    public void setForeignOrderid(String foreignOrderid) {
        this.foreignOrderid = foreignOrderid;
    }

    public Long getSignOrderid() {
        return signOrderid;
    }

    public void setSignOrderid(Long signOrderid) {
        this.signOrderid = signOrderid;
    }

    public String getForeignRequest() {
        return foreignRequest;
    }

    public void setForeignRequest(String foreignRequest) {
        this.foreignRequest = foreignRequest;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    /**
     *
     */
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", orderid=").append(orderid);
//        sb.append(", mtsid=").append(mtsid);
//        sb.append(", uid=").append(uid);
//        sb.append(", pid=").append(pid);
//        sb.append(", paytype=").append(paytype);
//        sb.append(", orderprice=").append(orderprice);
//        sb.append(", orderselprice=").append(orderselprice);
//        sb.append(", ordernum=").append(ordernum);
//        sb.append(", ordername=").append(ordername);
//        sb.append(", paytime=").append(paytime);
//        sb.append(", orderstatus=").append(orderstatus);
//        sb.append(", singlestatus=").append(singlestatus);
//        sb.append(", addtime=").append(addtime);
//        sb.append(", updatetime=").append(updatetime);
//        sb.append("]");
//        return sb.toString();
//    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PayOrderInterfaceCustom{");
        sb.append("id=").append(id);
        sb.append(", ordertype=").append(ordertype);
        sb.append(", orderid='").append(orderid).append('\'');
        sb.append(", foreignOrderid='").append(foreignOrderid).append('\'');
        sb.append(", signOrderid=").append(signOrderid);
        sb.append(", foreignRequest='").append(foreignRequest).append('\'');
        sb.append(", returnUrl='").append(returnUrl).append('\'');
        sb.append(", mtsid=").append(mtsid);
        sb.append(", uid=").append(uid);
        sb.append(", pid=").append(pid);
        sb.append(", paytype=").append(paytype);
        sb.append(", orderprice=").append(orderprice);
        sb.append(", orderselprice=").append(orderselprice);
        sb.append(", ordernum=").append(ordernum);
        sb.append(", ordername='").append(ordername).append('\'');
        sb.append(", paytime=").append(paytime);
        sb.append(", orderstatus=").append(orderstatus);
        sb.append(", singlestatus=").append(singlestatus);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", bpid=").append(bpid);
        sb.append(", pname='").append(pname).append('\'');
        sb.append(", pintro='").append(pintro).append('\'');
        sb.append(", pmoney=").append(pmoney);
        sb.append(", pdpicture='").append(pdpicture).append('\'');
        sb.append(", cat='").append(cat).append('\'');
        sb.append('}');
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
        PayOrderInterfaceCustom other = (PayOrderInterfaceCustom) that;
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