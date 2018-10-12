package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeCompanyInfo {
    /**
     * 主键id
     */
    private String id;

    /**
     * 商户号，生成规则：MC+时间戳+6位随机数
     */
    private String merchNo;

    /**
     * 商户名称
     */
    private String companyName;

    /**
     * 商户秘钥
     */
    private String privateKey;

    /**
     * 企业余额
     */
    private BigDecimal money;

    /**
     * 余额校验码
     */
    private String moneyCode;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人电话，可以是座机
     */
    private String contactPhone;

    /**
     * 后台添加人id
     */
    private String operatorId;

    /**
     * 后台添加人姓名
     */
    private String operatorName;

    /**
     * 是否冻结 0=未冻结 1=冻结
     */
    private Byte isFreeze;

    /**
     * 是否逻辑删除 0=不删除 1=删除
     */
    private Byte isDel;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getMoneyCode() {
        return moneyCode;
    }

    public void setMoneyCode(String moneyCode) {
        this.moneyCode = moneyCode;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
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

    public Byte getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(Byte isFreeze) {
        this.isFreeze = isFreeze;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
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
