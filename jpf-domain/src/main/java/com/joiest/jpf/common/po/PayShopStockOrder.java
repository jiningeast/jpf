package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayShopStockOrder implements Serializable {
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
     * 上传的excel文件远程路径
     */
    private String ossUrl;

    /**
     * 商品采购入库时间【导入excel时间】
     */
    private Date cardtime;

    /**
     * 
     */
    private Date addtime;

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

    public String getOssUrl() {
        return ossUrl;
    }

    public void setOssUrl(String ossUrl) {
        this.ossUrl = ossUrl == null ? null : ossUrl.trim();
    }

    public Date getCardtime() {
        return cardtime;
    }

    public void setCardtime(Date cardtime) {
        this.cardtime = cardtime;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
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
        sb.append(", orderNo=").append(orderNo);
        sb.append(", money=").append(money);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", status=").append(status);
        sb.append(", paywayId=").append(paywayId);
        sb.append(", paywayCn=").append(paywayCn);
        sb.append(", paytypeId=").append(paytypeId);
        sb.append(", paytypeCn=").append(paytypeCn);
        sb.append(", memo=").append(memo);
        sb.append(", ossUrl=").append(ossUrl);
        sb.append(", cardtime=").append(cardtime);
        sb.append(", addtime=").append(addtime);
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
        PayShopStockOrder other = (PayShopStockOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
            && (this.getOperatorName() == null ? other.getOperatorName() == null : this.getOperatorName().equals(other.getOperatorName()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getPaywayId() == null ? other.getPaywayId() == null : this.getPaywayId().equals(other.getPaywayId()))
            && (this.getPaywayCn() == null ? other.getPaywayCn() == null : this.getPaywayCn().equals(other.getPaywayCn()))
            && (this.getPaytypeId() == null ? other.getPaytypeId() == null : this.getPaytypeId().equals(other.getPaytypeId()))
            && (this.getPaytypeCn() == null ? other.getPaytypeCn() == null : this.getPaytypeCn().equals(other.getPaytypeCn()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getOssUrl() == null ? other.getOssUrl() == null : this.getOssUrl().equals(other.getOssUrl()))
            && (this.getCardtime() == null ? other.getCardtime() == null : this.getCardtime().equals(other.getCardtime()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getOperatorName() == null) ? 0 : getOperatorName().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getPaywayId() == null) ? 0 : getPaywayId().hashCode());
        result = prime * result + ((getPaywayCn() == null) ? 0 : getPaywayCn().hashCode());
        result = prime * result + ((getPaytypeId() == null) ? 0 : getPaytypeId().hashCode());
        result = prime * result + ((getPaytypeCn() == null) ? 0 : getPaytypeCn().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getOssUrl() == null) ? 0 : getOssUrl().hashCode());
        result = prime * result + ((getCardtime() == null) ? 0 : getCardtime().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        return result;
    }
}