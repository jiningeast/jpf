package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ShopBargainOrderInfo {
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
    private Integer minDou;

    /**
     * 使用了多少豆
     */
    private Integer dou;

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

    public String getFinanceId() { return financeId;
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

}
