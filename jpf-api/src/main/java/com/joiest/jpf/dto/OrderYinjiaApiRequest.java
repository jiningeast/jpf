package com.joiest.jpf.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class OrderYinjiaApiRequest {
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
     * 支付时间
     */
    private String paytime;

    /**
     * 支付时间的开始时间
     */
    private String paytimeStart;

    /**
     * 支付时间的结束时间
     */
    private String paytimeEnd;

    /**
     * 0:未支付；1:支付成功；2:支付失败
     */
    private Byte orderstatus;

    /**
     * 1:正常订单；2:退单处理；3:退款撤销；4:退单受理中，5:退单处理完毕
     */
    private Byte singlestatus;

    /**
     * 添加时间
     */
    private String addtime;

    /**
     * 添加时间的开始时间
     */
    private String addtimeStart;

    /**
     * 添加时间的结束时间
     */
    private String addtimeEnd;

    /**
     * 修改时间
     */
    private String updatetime;

    /**
     * 分期付款序列（json格式）
     */
    private String ordername;

    /**
     * 分页
     */
    private long page;

    /**
     * 查询数量
     */
    private long rows;

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

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public String getPaytimeStart() {
        return paytimeStart;
    }

    public void setPaytimeStart(String paytimeStart) {
        this.paytimeStart = paytimeStart;
    }

    public String getPaytimeEnd() {
        return paytimeEnd;
    }

    public void setPaytimeEnd(String paytimeEnd) {
        this.paytimeEnd = paytimeEnd;
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

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getAddtimeStart() {
        return addtimeStart;
    }

    public void setAddtimeStart(String addtimeStart) {
        this.addtimeStart = addtimeStart;
    }

    public String getAddtimeEnd() {
        return addtimeEnd;
    }

    public void setAddtimeEnd(String addtimeEnd) {
        this.addtimeEnd = addtimeEnd;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }
}
