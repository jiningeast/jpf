package com.joiest.jpf.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class GetShopBargainOrderRequest {
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
     * 订单状态 0=未支付 1=已支付 2=取消
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



    /**
     * 页码
     */
    private Integer page;

    /**
     * 条数
     */
    private Integer rows;


    /**
     * 添加时间搜索
     */
    private String addtimeStart;

    private String addtimeEnd;

    /**
     * 状态数组
     */
    private List<Byte> statusArr;


    /**
     * 转让价
     */
    private BigDecimal transferPrice;

    /**
     * 付款凭证图片
     */
    private String payImg;


    /**
     * 财务id
     */
    private String financeId;

    /**
     * 财务姓名
     */
    private String financeName;

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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Byte> getStatusArr() {
        return statusArr;
    }

    public void setStatusArr(List<Byte> statusArr) {
        this.statusArr = statusArr;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(BigDecimal transferPrice) {
        this.transferPrice = transferPrice;
    }

    public String getPayImg() {
        return payImg;
    }

    public void setPayImg(String payImg) {
        this.payImg = payImg;
    }

    public String getFinanceId() {
        return financeId;
    }

    public void setFinanceId(String financeId) {
        this.financeId = financeId;
    }

    public String getFinanceName() {
        return financeName;
    }

    public void setFinanceName(String financeName) {
        this.financeName = financeName;
    }
}
