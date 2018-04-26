package com.joiest.jpf.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class OrderCpsingleRequest {
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
     * 页码
     */
    private long page;

    /**
     * 查询条数
     */
    private long rows;

    private static final long serialVersionUID = 1L;

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
        this.operateContent = operateContent == null ? null : operateContent.trim();
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

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
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
