package com.joiest.jpf.dto;

import com.joiest.jpf.common.po.PayShopOrder;

import java.math.BigDecimal;
import java.util.Date;

public class GetShopStockOrderRequest {
    /**
     * 主键id
     */
    private String id;

    /**
     * 采购订单号
     */
    private String orderNo;

    /**
     * 订单金额
     */
    private BigDecimal money;

    /**
     * 后台操作人id
     */
    private String operatorId;

    /**
     * 后台操作人姓名
     */
    private String operatorName;

    /**
     * 0=取消 1=新建，待提交 2=已提交，待审批 3=已审批，待付款 4=已付款，完成
     */
    private Byte status;

    /**
     * 付款方式id
     */
    private Integer paywayId;

    /**
     * 付款方式名称
     */
    private String paywayCn;

    /**
     *
     */
    private Integer paytypeId;

    /**
     *
     */
    private String paytypeCn;

    /**
     * 备注
     */
    private String memo;

    /**
     *
     */
    private Date addtime;

    /**
     * 页码
     */
    private Long page;

    /**
     * 总条数
     */
    private Long rows;

    /**
     * 开始时间
     */
    private String addtimeStart;

    /**
     * 结束时间
     */
    private String addtimeEnd;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getPaywayId() {
        return paywayId;
    }

    public void setPaywayId(Integer paywayId) {
        this.paywayId = paywayId;
    }

    public String getPaywayCn() {
        return paywayCn;
    }

    public void setPaywayCn(String paywayCn) {
        this.paywayCn = paywayCn == null ? null : paywayCn.trim();
    }

    public Integer getPaytypeId() {
        return paytypeId;
    }

    public void setPaytypeId(Integer paytypeId) {
        this.paytypeId = paytypeId;
    }

    public String getPaytypeCn() {
        return paytypeCn;
    }

    public void setPaytypeCn(String paytypeCn) {
        this.paytypeCn = paytypeCn == null ? null : paytypeCn.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }
}
