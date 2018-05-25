package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class OrderInterfaceInfo {
    /**
     * 自增ID
     */
    private BigInteger id;

    /**
     * 订单ID
     */
    private String orderid;

    /**
     * 外来订单ID
     */
    private String foreign_orderid;

    /**
     * 签约订单ID
     */
    private String sign_orderid;

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
        this.orderid = orderid;
    }

    public String getForeignOrderid() {
        return foreign_orderid;
    }

    public void setForeignOrderid(String foreign_orderid) {
        this.foreign_orderid = foreign_orderid;
    }
    public String getSignOrderid() {
        return sign_orderid;
    }

    public void setSignOrderid(String sign_orderid) {
        this.sign_orderid = sign_orderid;
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
        this.ordername = ordername;
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

    public Long getBpid() {
        return bpid;
    }

    public void setBpid(Long bpid) {
        this.bpid = bpid;
    }
}
