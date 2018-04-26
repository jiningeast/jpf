package com.joiest.jpf.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TdorderRequest {

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
     * 条数
     */
    private long rows;

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
