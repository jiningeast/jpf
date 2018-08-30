package com.joiest.jpf.entity;

import com.joiest.jpf.common.po.PayShopCustomer;
import com.joiest.jpf.common.po.PayShopStockLog;

import java.math.BigDecimal;
import java.util.Date;

public class ShopStockLogInfo {
    /**
     * 主键id
     */
    private String id;


    /**
     * 采购订单id
     */
    private String stockOrderId;

    /**
     * 采购订单单号
     */
    private String stockOrderNo;

    /**
     * 产品id
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 消费时顾客id
     */
    private String customerId;

    /**
     * 消费时顾客姓名
     */
    private String customerName;

    /**
     * 进货时操作人id
     */
    private String operatorId;

    /**
     * 进货时操作人姓名
     */
    private String operatorName;

    /**
     * 期初结存金额
     */
    private BigDecimal initMoney;

    /**
     * 期初结存数量
     */
    private Integer initAmount;

    /**
     * 日常进货金额
     */
    private BigDecimal dailyInMoney;

    /**
     * 日常进货数量
     */
    private Integer dailyInAmount;

    /**
     * 日常出货金额
     */
    private BigDecimal dailyOutMoney;

    /**
     * 日常出货数量
     */
    private Integer dailyOutAmount;

    /**
     * 期末结存金额
     */
    private BigDecimal finalMoney;

    /**
     * 期末结存数量
     */
    private Integer finalAmount;

    /**
     * 添加时间
     */
    private Date addtime;


    //自定义字段

    //总数
    private Integer count;
    //进货总数
    private Integer inAmount;
    //出货总数
    private Integer outAmount;
    //进货总金额
    private BigDecimal inMoney;
    //出货总金额
    private BigDecimal outMoney;

    //期初结存数量
    private Integer beginCount;

    //期初金额
    private BigDecimal beginMoney;

    //期末结存数量
    private Integer finishCount;

    //期末金额
    private BigDecimal finishMoney;


    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
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

    public BigDecimal getInitMoney() {
        return initMoney;
    }

    public void setInitMoney(BigDecimal initMoney) {
        this.initMoney = initMoney;
    }

    public Integer getInitAmount() {
        return initAmount;
    }

    public void setInitAmount(Integer initAmount) {
        this.initAmount = initAmount;
    }

    public BigDecimal getDailyInMoney() {
        return dailyInMoney;
    }

    public void setDailyInMoney(BigDecimal dailyInMoney) {
        this.dailyInMoney = dailyInMoney;
    }

    public Integer getDailyInAmount() {
        return dailyInAmount;
    }

    public void setDailyInAmount(Integer dailyInAmount) {
        this.dailyInAmount = dailyInAmount;
    }

    public BigDecimal getDailyOutMoney() {
        return dailyOutMoney;
    }

    public void setDailyOutMoney(BigDecimal dailyOutMoney) {
        this.dailyOutMoney = dailyOutMoney;
    }

    public Integer getDailyOutAmount() {
        return dailyOutAmount;
    }

    public void setDailyOutAmount(Integer dailyOutAmount) {
        this.dailyOutAmount = dailyOutAmount;
    }

    public BigDecimal getFinalMoney() {
        return finalMoney;
    }

    public void setFinalMoney(BigDecimal finalMoney) {
        this.finalMoney = finalMoney;
    }

    public Integer getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Integer finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getStockOrderId() {
        return stockOrderId;
    }

    public void setStockOrderId(String stockOrderId) {
        this.stockOrderId = stockOrderId;
    }

    public String getStockOrderNo() {
        return stockOrderNo;
    }

    public void setStockOrderNo(String stockOrderNo) {
        this.stockOrderNo = stockOrderNo;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getInAmount() {
        return inAmount;
    }

    public void setInAmount(Integer inAmount) {
        this.inAmount = inAmount;
    }

    public Integer getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(Integer outAmount) {
        this.outAmount = outAmount;
    }

    public BigDecimal getInMoney() {
        return inMoney;
    }

    public void setInMoney(BigDecimal inMoney) {
        this.inMoney = inMoney;
    }

    public BigDecimal getOutMoney() {
        return outMoney;
    }

    public void setOutMoney(BigDecimal outMoney) {
        this.outMoney = outMoney;
    }

    public Integer getBeginCount() {
        return beginCount;
    }

    public void setBeginCount(Integer beginCount) {
        this.beginCount = beginCount;
    }

    public BigDecimal getBeginMoney() {
        return beginMoney;
    }

    public void setBeginMoney(BigDecimal beginMoney) {
        this.beginMoney = beginMoney;
    }

    public Integer getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }

    public BigDecimal getFinishMoney() {
        return finishMoney;
    }

    public void setFinishMoney(BigDecimal finishMoney) {
        this.finishMoney = finishMoney;
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
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", customerId=").append(customerId);
        sb.append(", customerName=").append(customerName);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", initMoney=").append(initMoney);
        sb.append(", initAmount=").append(initAmount);
        sb.append(", dailyInMoney=").append(dailyInMoney);
        sb.append(", dailyInAmount=").append(dailyInAmount);
        sb.append(", dailyOutMoney=").append(dailyOutMoney);
        sb.append(", dailyOutAmount=").append(dailyOutAmount);
        sb.append(", finalMoney=").append(finalMoney);
        sb.append(", finalAmount=").append(finalAmount);
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
        PayShopStockLog other = (PayShopStockLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
                && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
                && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
                && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
                && (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
                && (this.getOperatorName() == null ? other.getOperatorName() == null : this.getOperatorName().equals(other.getOperatorName()))
                && (this.getInitMoney() == null ? other.getInitMoney() == null : this.getInitMoney().equals(other.getInitMoney()))
                && (this.getInitAmount() == null ? other.getInitAmount() == null : this.getInitAmount().equals(other.getInitAmount()))
                && (this.getDailyInMoney() == null ? other.getDailyInMoney() == null : this.getDailyInMoney().equals(other.getDailyInMoney()))
                && (this.getDailyInAmount() == null ? other.getDailyInAmount() == null : this.getDailyInAmount().equals(other.getDailyInAmount()))
                && (this.getDailyOutMoney() == null ? other.getDailyOutMoney() == null : this.getDailyOutMoney().equals(other.getDailyOutMoney()))
                && (this.getDailyOutAmount() == null ? other.getDailyOutAmount() == null : this.getDailyOutAmount().equals(other.getDailyOutAmount()))
                && (this.getFinalMoney() == null ? other.getFinalMoney() == null : this.getFinalMoney().equals(other.getFinalMoney()))
                && (this.getFinalAmount() == null ? other.getFinalAmount() == null : this.getFinalAmount().equals(other.getFinalAmount()))
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
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getOperatorName() == null) ? 0 : getOperatorName().hashCode());
        result = prime * result + ((getInitMoney() == null) ? 0 : getInitMoney().hashCode());
        result = prime * result + ((getInitAmount() == null) ? 0 : getInitAmount().hashCode());
        result = prime * result + ((getDailyInMoney() == null) ? 0 : getDailyInMoney().hashCode());
        result = prime * result + ((getDailyInAmount() == null) ? 0 : getDailyInAmount().hashCode());
        result = prime * result + ((getDailyOutMoney() == null) ? 0 : getDailyOutMoney().hashCode());
        result = prime * result + ((getDailyOutAmount() == null) ? 0 : getDailyOutAmount().hashCode());
        result = prime * result + ((getFinalMoney() == null) ? 0 : getFinalMoney().hashCode());
        result = prime * result + ((getFinalAmount() == null) ? 0 : getFinalAmount().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        return result;
    }
}
