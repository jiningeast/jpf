package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ShopBatchInfo {
    /**
     * 主键id
     */
    private String id;

    /**
     * 企业id
     */
    private String companyId;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 批次号：BA+MD5(UUID)
     */
    private String batchNo;

    /**
     * 总面额
     */
    private BigDecimal money;

    /**
     * 面额兑换豆比例 1即1元兑换1豆
     */
    private Double scale;

    /**
     * 券总数量
     */
    private Integer count;

    /**
     * 有效期
     */
    private Integer expireMonth;

    /**
     * 批次状态 0=生成券码中 1=生成完毕，待发券  2=已发券 3=已取消
     */
    private Byte status;

    /**
     * 批次详情
     */
    private String batchContent;

    /**
     * 激活数量
     */
    private Integer activetedNum;

    /**
     * 所属销售
     */
    private Integer salesId;

    /**
     * 销售名称
     */
    private String salesName;

    /**
     * 接收人
     */
    private String receiveName;

    /**
     * 接收人邮箱
     */
    private String receiveEmail;

    /**
     * 接收人电话
     */
    private String receivePhone;

    /**
     * 压缩包阿里云文件服务器地址
     */
    private String ossUrl;

    /**
     * 压缩包密码
     */
    private String zipPassword;

    /**
     * 邮件内容
     */
    private String emailContent;

    /**
     * 邮件发送时间
     */
    private Date emailTime;

    /**
     * 邮件发送 0::未发送 1:已发送 2:发送失败
     */
    private Byte emailStatus;

    /**
     * 短信发送时间
     */
    private Date smsTime;

    /**
     * 短信发送 0::未发送 1:已发送 2:发送失败
     */
    private Byte smsStatus;

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 群发给个人时运营上传的excel文件地址
     */
    private String excelUrl;

    /**
     * 群发给个人的时间
     */
    private Date sendTime;

    /**
     * 分发方式 0=email发给接收人 1=群发给个人
     */
    private Byte sendType;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 短信内容
     */
    private String smsContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(Integer expireMonth) {
        this.expireMonth = expireMonth;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getBatchContent() {
        return batchContent;
    }

    public void setBatchContent(String batchContent) {
        this.batchContent = batchContent;
    }

    public Integer getActivetedNum() {
        return activetedNum;
    }

    public void setActivetedNum(Integer activetedNum) {
        this.activetedNum = activetedNum;
    }

    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getOssUrl() {
        return ossUrl;
    }

    public void setOssUrl(String ossUrl) {
        this.ossUrl = ossUrl;
    }

    public String getZipPassword() {
        return zipPassword;
    }

    public void setZipPassword(String zipPassword) {
        this.zipPassword = zipPassword;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    public Date getEmailTime() {
        return emailTime;
    }

    public void setEmailTime(Date emailTime) {
        this.emailTime = emailTime;
    }

    public Byte getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(Byte emailStatus) {
        this.emailStatus = emailStatus;
    }

    public Date getSmsTime() {
        return smsTime;
    }

    public void setSmsTime(Date smsTime) {
        this.smsTime = smsTime;
    }

    public Byte getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(Byte smsStatus) {
        this.smsStatus = smsStatus;
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

    public String getExcelUrl() {
        return excelUrl;
    }

    public void setExcelUrl(String excelUrl) {
        this.excelUrl = excelUrl;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Byte getSendType() {
        return sendType;
    }

    public void setSendType(Byte sendType) {
        this.sendType = sendType;
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

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }
}
