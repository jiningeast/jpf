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
     * 总商品数量
     */
    private Integer productAmount;

    /**
     * 成功导入数量
     */
    private Integer importedAmount;

    /**
     * 运营操作人id
     */
    private String operatorId;

    /**
     * 运营操作人姓名
     */
    private String operatorName;

    /**
     * 审核人id
     */
    private String checkOperatorId;

    /**
     * 审核人姓名
     */
    private String checkOperatorName;

    /**
     * 审核时间
     */
    private Date checkTime;

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
     * 商品是否采购    1 是  2否
     */
    private Byte isUpload;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 修改时间
     */
    private Date updatetime;

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

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public Integer getImportedAmount() {
        return importedAmount;
    }

    public void setImportedAmount(Integer importedAmount) {
        this.importedAmount = importedAmount;
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

    public String getCheckOperatorId() {
        return checkOperatorId;
    }

    public void setCheckOperatorId(String checkOperatorId) {
        this.checkOperatorId = checkOperatorId == null ? null : checkOperatorId.trim();
    }

    public String getCheckOperatorName() {
        return checkOperatorName;
    }

    public void setCheckOperatorName(String checkOperatorName) {
        this.checkOperatorName = checkOperatorName == null ? null : checkOperatorName.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
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

    public Byte getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(Byte isUpload) {
        this.isUpload = isUpload;
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
        sb.append(", orderNo=").append(orderNo);
        sb.append(", money=").append(money);
        sb.append(", productAmount=").append(productAmount);
        sb.append(", importedAmount=").append(importedAmount);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", checkOperatorId=").append(checkOperatorId);
        sb.append(", checkOperatorName=").append(checkOperatorName);
        sb.append(", checkTime=").append(checkTime);
        sb.append(", status=").append(status);
        sb.append(", paywayId=").append(paywayId);
        sb.append(", paywayCn=").append(paywayCn);
        sb.append(", paytypeId=").append(paytypeId);
        sb.append(", paytypeCn=").append(paytypeCn);
        sb.append(", memo=").append(memo);
        sb.append(", ossUrl=").append(ossUrl);
        sb.append(", cardtime=").append(cardtime);
        sb.append(", isUpload=").append(isUpload);
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
        PayShopStockOrder other = (PayShopStockOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getProductAmount() == null ? other.getProductAmount() == null : this.getProductAmount().equals(other.getProductAmount()))
            && (this.getImportedAmount() == null ? other.getImportedAmount() == null : this.getImportedAmount().equals(other.getImportedAmount()))
            && (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
            && (this.getOperatorName() == null ? other.getOperatorName() == null : this.getOperatorName().equals(other.getOperatorName()))
            && (this.getCheckOperatorId() == null ? other.getCheckOperatorId() == null : this.getCheckOperatorId().equals(other.getCheckOperatorId()))
            && (this.getCheckOperatorName() == null ? other.getCheckOperatorName() == null : this.getCheckOperatorName().equals(other.getCheckOperatorName()))
            && (this.getCheckTime() == null ? other.getCheckTime() == null : this.getCheckTime().equals(other.getCheckTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getPaywayId() == null ? other.getPaywayId() == null : this.getPaywayId().equals(other.getPaywayId()))
            && (this.getPaywayCn() == null ? other.getPaywayCn() == null : this.getPaywayCn().equals(other.getPaywayCn()))
            && (this.getPaytypeId() == null ? other.getPaytypeId() == null : this.getPaytypeId().equals(other.getPaytypeId()))
            && (this.getPaytypeCn() == null ? other.getPaytypeCn() == null : this.getPaytypeCn().equals(other.getPaytypeCn()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getOssUrl() == null ? other.getOssUrl() == null : this.getOssUrl().equals(other.getOssUrl()))
            && (this.getCardtime() == null ? other.getCardtime() == null : this.getCardtime().equals(other.getCardtime()))
            && (this.getIsUpload() == null ? other.getIsUpload() == null : this.getIsUpload().equals(other.getIsUpload()))
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
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getProductAmount() == null) ? 0 : getProductAmount().hashCode());
        result = prime * result + ((getImportedAmount() == null) ? 0 : getImportedAmount().hashCode());
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getOperatorName() == null) ? 0 : getOperatorName().hashCode());
        result = prime * result + ((getCheckOperatorId() == null) ? 0 : getCheckOperatorId().hashCode());
        result = prime * result + ((getCheckOperatorName() == null) ? 0 : getCheckOperatorName().hashCode());
        result = prime * result + ((getCheckTime() == null) ? 0 : getCheckTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getPaywayId() == null) ? 0 : getPaywayId().hashCode());
        result = prime * result + ((getPaywayCn() == null) ? 0 : getPaywayCn().hashCode());
        result = prime * result + ((getPaytypeId() == null) ? 0 : getPaytypeId().hashCode());
        result = prime * result + ((getPaytypeCn() == null) ? 0 : getPaytypeCn().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getOssUrl() == null) ? 0 : getOssUrl().hashCode());
        result = prime * result + ((getCardtime() == null) ? 0 : getCardtime().hashCode());
        result = prime * result + ((getIsUpload() == null) ? 0 : getIsUpload().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}