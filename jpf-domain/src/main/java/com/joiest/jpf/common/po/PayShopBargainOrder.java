package com.joiest.jpf.common.po;

import java.io.Serializable;
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
     * 卖家id
     */
    private String sellerCustomerId;

    /**
     * 折损率
     */
    private Double offRate;

    /**
     * 最低限额豆数
     */
    private Integer minDou;

    /**
     * 使用了多少豆
     */
    private Integer dou;

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
     * 订单状态 0=未支付 1=运营已审核 2=财务打款中 3=打款成功 4=打款失败 5=取消订单
     */
    private Byte status;

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 操作人姓名
     */
    private String operatorName;

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

    public String getSellerCustomerId() {
        return sellerCustomerId;
    }

    public void setSellerCustomerId(String sellerCustomerId) {
        this.sellerCustomerId = sellerCustomerId == null ? null : sellerCustomerId.trim();
    }

    public Double getOffRate() {
        return offRate;
    }

    public void setOffRate(Double offRate) {
        this.offRate = offRate;
    }

    public Integer getMinDou() {
        return minDou;
    }

    public void setMinDou(Integer minDou) {
        this.minDou = minDou;
    }

    public Integer getDou() {
        return dou;
    }

    public void setDou(Integer dou) {
        this.dou = dou;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
        sb.append(", sellerCustomerId=").append(sellerCustomerId);
        sb.append(", offRate=").append(offRate);
        sb.append(", minDou=").append(minDou);
        sb.append(", dou=").append(dou);
        sb.append(", realName=").append(realName);
        sb.append(", idno=").append(idno);
        sb.append(", phone=").append(phone);
        sb.append(", bankId=").append(bankId);
        sb.append(", bankBrank=").append(bankBrank);
        sb.append(", bankNo=").append(bankNo);
        sb.append(", findcode=").append(findcode);
        sb.append(", status=").append(status);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", memo=").append(memo);
        sb.append(", paytime=").append(paytime);
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
        PayShopBargainOrder other = (PayShopBargainOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getBargainRequestId() == null ? other.getBargainRequestId() == null : this.getBargainRequestId().equals(other.getBargainRequestId()))
            && (this.getBuyerCustomerId() == null ? other.getBuyerCustomerId() == null : this.getBuyerCustomerId().equals(other.getBuyerCustomerId()))
            && (this.getSellerCustomerId() == null ? other.getSellerCustomerId() == null : this.getSellerCustomerId().equals(other.getSellerCustomerId()))
            && (this.getOffRate() == null ? other.getOffRate() == null : this.getOffRate().equals(other.getOffRate()))
            && (this.getMinDou() == null ? other.getMinDou() == null : this.getMinDou().equals(other.getMinDou()))
            && (this.getDou() == null ? other.getDou() == null : this.getDou().equals(other.getDou()))
            && (this.getRealName() == null ? other.getRealName() == null : this.getRealName().equals(other.getRealName()))
            && (this.getIdno() == null ? other.getIdno() == null : this.getIdno().equals(other.getIdno()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getBankId() == null ? other.getBankId() == null : this.getBankId().equals(other.getBankId()))
            && (this.getBankBrank() == null ? other.getBankBrank() == null : this.getBankBrank().equals(other.getBankBrank()))
            && (this.getBankNo() == null ? other.getBankNo() == null : this.getBankNo().equals(other.getBankNo()))
            && (this.getFindcode() == null ? other.getFindcode() == null : this.getFindcode().equals(other.getFindcode()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
            && (this.getOperatorName() == null ? other.getOperatorName() == null : this.getOperatorName().equals(other.getOperatorName()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getPaytime() == null ? other.getPaytime() == null : this.getPaytime().equals(other.getPaytime()))
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
        result = prime * result + ((getBargainRequestId() == null) ? 0 : getBargainRequestId().hashCode());
        result = prime * result + ((getBuyerCustomerId() == null) ? 0 : getBuyerCustomerId().hashCode());
        result = prime * result + ((getSellerCustomerId() == null) ? 0 : getSellerCustomerId().hashCode());
        result = prime * result + ((getOffRate() == null) ? 0 : getOffRate().hashCode());
        result = prime * result + ((getMinDou() == null) ? 0 : getMinDou().hashCode());
        result = prime * result + ((getDou() == null) ? 0 : getDou().hashCode());
        result = prime * result + ((getRealName() == null) ? 0 : getRealName().hashCode());
        result = prime * result + ((getIdno() == null) ? 0 : getIdno().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getBankId() == null) ? 0 : getBankId().hashCode());
        result = prime * result + ((getBankBrank() == null) ? 0 : getBankBrank().hashCode());
        result = prime * result + ((getBankNo() == null) ? 0 : getBankNo().hashCode());
        result = prime * result + ((getFindcode() == null) ? 0 : getFindcode().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getOperatorName() == null) ? 0 : getOperatorName().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getPaytime() == null) ? 0 : getPaytime().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        return result;
    }
}