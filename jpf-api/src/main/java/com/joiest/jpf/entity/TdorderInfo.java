package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class TdorderInfo {
    /**
     * 自增ID
     */
    private BigInteger id;

    /**
     * 退单ID
     */
    private String tdorderid;

    /**
     * 商品订单ID
     */
    private String orderid;

    /**
     * 退单金额
     */
    private BigDecimal tdorderprice;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 1:支付后退单，2:未支付撤单
     */
    private Byte singletype;

    /**
     * 运营审核：0：审核中；1:审核成功；2：审核失败，驳回
     */
    private Byte singlestatus;

    /**
     * 运营驳回理由
     */
    private String operateContent;

    /**
     * 图片地址 json
     */
    private String urla;

    /**
     * 申请理由
     */
    private String content;

    /**
     * 用户申请退单的时间
     */
    private Date lasttime;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 聚合商户名称
     */
    private String merchName;

    /**
     * 聚合公司名称
     */
    private String companyname;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTdorderid() {
        return tdorderid;
    }

    public void setTdorderid(String tdorderid) {
        this.tdorderid = tdorderid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public BigDecimal getTdorderprice() {
        return tdorderprice;
    }

    public void setTdorderprice(BigDecimal tdorderprice) {
        this.tdorderprice = tdorderprice;
    }

    public Long getMtsid() {
        return mtsid;
    }

    public void setMtsid(Long mtsid) {
        this.mtsid = mtsid;
    }

    public Byte getSingletype() {
        return singletype;
    }

    public void setSingletype(Byte singletype) {
        this.singletype = singletype;
    }

    public Byte getSinglestatus() {
        return singlestatus;
    }

    public void setSinglestatus(Byte singlestatus) {
        this.singlestatus = singlestatus;
    }

    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }

    public String getUrla() {
        return urla;
    }

    public void setUrla(String urla) {
        this.urla = urla;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
}
