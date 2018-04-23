package com.joiest.jpf.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OrderCpsingleRequest {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 退单ID
     */
    private Long tdorderid;

    /**
     * 商品订单ID
     */
    private Long orderid;

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
     * 财务|运营|商服退款审核：0:待审核; 1:退单退款成功；2：退单退款失败
     */
    private Byte singlestatus;

    /**
     * 财务通过/驳回原因
     */
    private String operateContent;

    /**
     * 添加时间
     */
    private Date addtime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTdorderid() {
        return tdorderid;
    }

    public void setTdorderid(Long tdorderid) {
        this.tdorderid = tdorderid;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
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
        this.operateContent = operateContent == null ? null : operateContent.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}
