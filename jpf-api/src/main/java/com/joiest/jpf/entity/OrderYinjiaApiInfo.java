package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class OrderYinjiaApiInfo {
    /**
     * 自增ID
     */
    private BigInteger id;

    /**
     * 订单ID
     */
    private String orderid;

    /**
     * 外来订单号
     */
    private String foreignOrderid;

    /**
     * 签约订单号，对应pay_order_cp的orderid字段
     */
    private Long signOrderid;

    /**
     * 外来请求字符串
     */
    private String foreignRequest;

    /**
     *
     */
    private String returnUrl;

    /**
     * 异步回调
     */
    private String notifyUrl;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 支付方式：pay_merchants_type
     */
    private Integer paytype;

    /**
     * 订单实际支付金额(用户实付)
     */
    private BigDecimal orderPayPrice;

    /**
     * 订单标准金额(未折扣订单原价)
     */
    private BigDecimal orderStdPrice;

    /**
     * 订单商品数量
     */
    private Integer productAccount;

    /**
     * 分期付款序列（json格式）
     */
    private String payDetail;

    /**
     * 支付时间
     */
    private Date paytime;

    /**
     * 0:未支付；1:支付成功；2:支付失败
     */
    private Byte payStatus;

    /**
     * 1:正常订单；2:退单处理；3:退款撤销；4:运营已审核,待财务审核，5:财务已审核，银联退款中, 6:审核驳回,7:银联退款成功,8:银联退款失败
     */
    private Byte refundStatus;

    /**
     * 用户操作状态 0:订单生成,未选分期;1:订单生成,已选分期,待签约;2:签约通知成功,待返回签约成功;3:签约通知失败;4:签约返回成功,待获取支付短信;5:签约返回失败;6:用户曾已签约,待获取支付短信;7:短信发送成功,待支付;8:短信发送失败;9:支付通知成功,待返回成功;10:支付通知失败;11:支付返回成功,支付结束;12:支付返回失败
     */
    private Byte userOperateStatus;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    //=========商户信息 for 后台列表展示 Begin==============

    /**
     * 聚合商户名称
     */
    private String merchName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 企业名称
     */
    private String companyname;
    //=========商户信息 End==============
    //=========支付方式 Begin==============

    private String cat;

    //=========支付方式 End==============

    //=========后台页面展示需要 BEGIN =========
    //用户操作状态
    private String userOperateStatus_cn;

    //退款状态
    private String refundStatus_cn;

    private String productName;

    public String getUserOperateStatus_cn() {
        return userOperateStatus_cn;
    }

    public void setUserOperateStatus_cn(String userOperateStatus_cn) {
        this.userOperateStatus_cn = userOperateStatus_cn;
    }

    public String getRefundStatus_cn() {
        return refundStatus_cn;
    }

    public void setRefundStatus_cn(String refundStatus_cn) {
        this.refundStatus_cn = refundStatus_cn;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    //=========后台页面展示需要 End =========



    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getForeignOrderid() {
        return foreignOrderid;
    }

    public void setForeignOrderid(String foreignOrderid) {
        this.foreignOrderid = foreignOrderid;
    }

    public Long getSignOrderid() {
        return signOrderid;
    }

    public void setSignOrderid(Long signOrderid) {
        this.signOrderid = signOrderid;
    }

    public String getForeignRequest() {
        return foreignRequest;
    }

    public void setForeignRequest(String foreignRequest) {
        this.foreignRequest = foreignRequest;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public Long getMtsid() {
        return mtsid;
    }

    public void setMtsid(Long mtsid) {
        this.mtsid = mtsid;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public BigDecimal getOrderPayPrice() {
        return orderPayPrice;
    }

    public void setOrderPayPrice(BigDecimal orderPayPrice) {
        this.orderPayPrice = orderPayPrice;
    }

    public BigDecimal getOrderStdPrice() {
        return orderStdPrice;
    }

    public void setOrderStdPrice(BigDecimal orderStdPrice) {
        this.orderStdPrice = orderStdPrice;
    }

    public Integer getProductAccount() {
        return productAccount;
    }

    public void setProductAccount(Integer productAccount) {
        this.productAccount = productAccount;
    }

    public String getPayDetail() {
        return payDetail;
    }

    public void setPayDetail(String payDetail) {
        this.payDetail = payDetail;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    public Byte getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Byte refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Byte getUserOperateStatus() {
        return userOperateStatus;
    }

    public void setUserOperateStatus(Byte userOperateStatus) {
        this.userOperateStatus = userOperateStatus;
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

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
}
