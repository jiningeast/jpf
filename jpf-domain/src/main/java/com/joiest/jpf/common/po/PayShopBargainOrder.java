package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayShopBargainOrder implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 订单号：BO+毫秒时间戳+6位随机数
     */
    private String orderNo;

    /**
     * 买家发布的信息id
     */
    private String bargainRequestId;

    /**
     * 买家id
     */
    private String buyerCustomerId;

    /**
     * 买家昵称
     */
    private String buyerCustomerNickname;

    /**
     * 卖家id
     */
    private String sellerCustomerId;

    /**
     * 卖家昵称
     */
    private String sellerCustomerNickname;

    /**
     * 折损率
     */
    private Double offRate;

    /**
     * 最低限额豆数
     */
    private BigDecimal minDou;

    /**
     * 使用了多少豆
     */
    private BigDecimal dou;

    /**
     * 转让价
     */
    private BigDecimal transferPrice;

    /**
     * 收款人姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String idno;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 银行id
     */
    private String bankId;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行分行名称
     */
    private String bankBrank;

    /**
     * 银行卡卡号
     */
    private String bankNo;

    /**
     * 聚合所用银行编码
     */
    private String findcode;

    /**
     * 付款凭证图片
     */
    private String payImg;

    /**
     * 订单状态 0=未支付 1=运营已审核 2=财务打款中 3=打款成功 4=打款失败 5=取消订单
     */
    private Byte status;

    /**
     * 0:未匹配完成 1:匹配完成
     */
    private Byte isSuccess;

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 财务id
     */
    private String financeId;

    /**
     * 财务姓名
     */
    private String financeName;

    /**
     * 备注
     */
    private String memo;

    /**
     * 支付时间
     */
    private Date paytime;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
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

    public String getBargainRequestId() {
        return bargainRequestId;
    }

    public void setBargainRequestId(String bargainRequestId) {
        this.bargainRequestId = bargainRequestId == null ? null : bargainRequestId.trim();
    }

    public String getBuyerCustomerId() {
        return buyerCustomerId;
    }

    public void setBuyerCustomerId(String buyerCustomerId) {
        this.buyerCustomerId = buyerCustomerId == null ? null : buyerCustomerId.trim();
    }

    public String getBuyerCustomerNickname() {
        return buyerCustomerNickname;
    }

    public void setBuyerCustomerNickname(String buyerCustomerNickname) {
        this.buyerCustomerNickname = buyerCustomerNickname == null ? null : buyerCustomerNickname.trim();
    }

    public String getSellerCustomerId() {
        return sellerCustomerId;
    }

    public void setSellerCustomerId(String sellerCustomerId) {
        this.sellerCustomerId = sellerCustomerId == null ? null : sellerCustomerId.trim();
    }

    public String getSellerCustomerNickname() {
        return sellerCustomerNickname;
    }

    public void setSellerCustomerNickname(String sellerCustomerNickname) {
        this.sellerCustomerNickname = sellerCustomerNickname == null ? null : sellerCustomerNickname.trim();
    }

    public Double getOffRate() {
        return offRate;
    }

    public void setOffRate(Double offRate) {
        this.offRate = offRate;
    }

    public BigDecimal getMinDou() {
        return minDou;
    }

    public void setMinDou(BigDecimal minDou) {
        this.minDou = minDou;
    }

    public BigDecimal getDou() {
        return dou;
    }

    public void setDou(BigDecimal dou) {
        this.dou = dou;
    }

    public BigDecimal getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(BigDecimal transferPrice) {
        this.transferPrice = transferPrice;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno == null ? null : idno.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId == null ? null : bankId.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankBrank() {
        return bankBrank;
    }

    public void setBankBrank(String bankBrank) {
        this.bankBrank = bankBrank == null ? null : bankBrank.trim();
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }

    public String getFindcode() {
        return findcode;
    }

    public void setFindcode(String findcode) {
        this.findcode = findcode == null ? null : findcode.trim();
    }

    public String getPayImg() {
        return payImg;
    }

    public void setPayImg(String payImg) {
        this.payImg = payImg == null ? null : payImg.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Byte isSuccess) {
        this.isSuccess = isSuccess;
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

    public String getFinanceId() {
        return financeId;
    }

    public void setFinanceId(String financeId) {
        this.financeId = financeId == null ? null : financeId.trim();
    }

    public String getFinanceName() {
        return financeName;
    }

    public void setFinanceName(String financeName) {
        this.financeName = financeName == null ? null : financeName.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
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
        sb.append(", bargainRequestId=").append(bargainRequestId);
        sb.append(", buyerCustomerId=").append(buyerCustomerId);
        sb.append(", buyerCustomerNickname=").append(buyerCustomerNickname);
        sb.append(", sellerCustomerId=").append(sellerCustomerId);
        sb.append(", sellerCustomerNickname=").append(sellerCustomerNickname);
        sb.append(", offRate=").append(offRate);
        sb.append(", minDou=").append(minDou);
        sb.append(", dou=").append(dou);
        sb.append(", transferPrice=").append(transferPrice);
        sb.append(", realName=").append(realName);
        sb.append(", idno=").append(idno);
        sb.append(", phone=").append(phone);
        sb.append(", bankId=").append(bankId);
        sb.append(", bankName=").append(bankName);
        sb.append(", bankBrank=").append(bankBrank);
        sb.append(", bankNo=").append(bankNo);
        sb.append(", findcode=").append(findcode);
        sb.append(", payImg=").append(payImg);
        sb.append(", status=").append(status);
        sb.append(", isSuccess=").append(isSuccess);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", financeId=").append(financeId);
        sb.append(", financeName=").append(financeName);
        sb.append(", memo=").append(memo);
        sb.append(", paytime=").append(paytime);
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
        PayShopBargainOrder other = (PayShopBargainOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getBargainRequestId() == null ? other.getBargainRequestId() == null : this.getBargainRequestId().equals(other.getBargainRequestId()))
            && (this.getBuyerCustomerId() == null ? other.getBuyerCustomerId() == null : this.getBuyerCustomerId().equals(other.getBuyerCustomerId()))
            && (this.getBuyerCustomerNickname() == null ? other.getBuyerCustomerNickname() == null : this.getBuyerCustomerNickname().equals(other.getBuyerCustomerNickname()))
            && (this.getSellerCustomerId() == null ? other.getSellerCustomerId() == null : this.getSellerCustomerId().equals(other.getSellerCustomerId()))
            && (this.getSellerCustomerNickname() == null ? other.getSellerCustomerNickname() == null : this.getSellerCustomerNickname().equals(other.getSellerCustomerNickname()))
            && (this.getOffRate() == null ? other.getOffRate() == null : this.getOffRate().equals(other.getOffRate()))
            && (this.getMinDou() == null ? other.getMinDou() == null : this.getMinDou().equals(other.getMinDou()))
            && (this.getDou() == null ? other.getDou() == null : this.getDou().equals(other.getDou()))
            && (this.getTransferPrice() == null ? other.getTransferPrice() == null : this.getTransferPrice().equals(other.getTransferPrice()))
            && (this.getRealName() == null ? other.getRealName() == null : this.getRealName().equals(other.getRealName()))
            && (this.getIdno() == null ? other.getIdno() == null : this.getIdno().equals(other.getIdno()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getBankId() == null ? other.getBankId() == null : this.getBankId().equals(other.getBankId()))
            && (this.getBankName() == null ? other.getBankName() == null : this.getBankName().equals(other.getBankName()))
            && (this.getBankBrank() == null ? other.getBankBrank() == null : this.getBankBrank().equals(other.getBankBrank()))
            && (this.getBankNo() == null ? other.getBankNo() == null : this.getBankNo().equals(other.getBankNo()))
            && (this.getFindcode() == null ? other.getFindcode() == null : this.getFindcode().equals(other.getFindcode()))
            && (this.getPayImg() == null ? other.getPayImg() == null : this.getPayImg().equals(other.getPayImg()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getIsSuccess() == null ? other.getIsSuccess() == null : this.getIsSuccess().equals(other.getIsSuccess()))
            && (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
            && (this.getOperatorName() == null ? other.getOperatorName() == null : this.getOperatorName().equals(other.getOperatorName()))
            && (this.getFinanceId() == null ? other.getFinanceId() == null : this.getFinanceId().equals(other.getFinanceId()))
            && (this.getFinanceName() == null ? other.getFinanceName() == null : this.getFinanceName().equals(other.getFinanceName()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getPaytime() == null ? other.getPaytime() == null : this.getPaytime().equals(other.getPaytime()))
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
        result = prime * result + ((getBargainRequestId() == null) ? 0 : getBargainRequestId().hashCode());
        result = prime * result + ((getBuyerCustomerId() == null) ? 0 : getBuyerCustomerId().hashCode());
        result = prime * result + ((getBuyerCustomerNickname() == null) ? 0 : getBuyerCustomerNickname().hashCode());
        result = prime * result + ((getSellerCustomerId() == null) ? 0 : getSellerCustomerId().hashCode());
        result = prime * result + ((getSellerCustomerNickname() == null) ? 0 : getSellerCustomerNickname().hashCode());
        result = prime * result + ((getOffRate() == null) ? 0 : getOffRate().hashCode());
        result = prime * result + ((getMinDou() == null) ? 0 : getMinDou().hashCode());
        result = prime * result + ((getDou() == null) ? 0 : getDou().hashCode());
        result = prime * result + ((getTransferPrice() == null) ? 0 : getTransferPrice().hashCode());
        result = prime * result + ((getRealName() == null) ? 0 : getRealName().hashCode());
        result = prime * result + ((getIdno() == null) ? 0 : getIdno().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getBankId() == null) ? 0 : getBankId().hashCode());
        result = prime * result + ((getBankName() == null) ? 0 : getBankName().hashCode());
        result = prime * result + ((getBankBrank() == null) ? 0 : getBankBrank().hashCode());
        result = prime * result + ((getBankNo() == null) ? 0 : getBankNo().hashCode());
        result = prime * result + ((getFindcode() == null) ? 0 : getFindcode().hashCode());
        result = prime * result + ((getPayImg() == null) ? 0 : getPayImg().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getIsSuccess() == null) ? 0 : getIsSuccess().hashCode());
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getOperatorName() == null) ? 0 : getOperatorName().hashCode());
        result = prime * result + ((getFinanceId() == null) ? 0 : getFinanceId().hashCode());
        result = prime * result + ((getFinanceName() == null) ? 0 : getFinanceName().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getPaytime() == null) ? 0 : getPaytime().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}