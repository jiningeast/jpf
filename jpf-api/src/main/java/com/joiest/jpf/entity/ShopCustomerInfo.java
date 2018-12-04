package com.joiest.jpf.entity;

import com.joiest.jpf.common.po.PayShopCustomer;

import java.math.BigDecimal;
import java.util.Date;

public class ShopCustomerInfo {
    /**
     *
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 微信昵称
     */
    private String nickname;

    /**
     * 所属公司id
     */
    private Long companyId;

    /**
     * 所属公司名称
     */
    private String companyName;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 身份证号
     */
    private String idno;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 是否实名认证
     */
    private Byte isVerify;

    /**
     * 用户类型 0=正常用户 1=特殊用户
     */
    private Byte type;

    /**
     * 客户状态 0=冻结 1=正常
     */
    private Byte status;

    /**
     * 欣豆数量
     */
    private BigDecimal dou;

    /**
     * 冻结欣豆
     */
    private BigDecimal freezeDou;

    /**
     * 充值校验码
     */
    private String code;

    /**
     *
     */
    private Date addtime;

    /**
     *
     */
    private Date updatetime;

    /**
     * 可转让豆
     */
    private BigDecimal saleDou;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
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

    public Byte getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Byte isVerify) {
        this.isVerify = isVerify;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public BigDecimal getDou() {
        return dou;
    }

    public void setDou(BigDecimal dou) {
        this.dou = dou;
    }

    public BigDecimal getFreezeDou() {
        return freezeDou;
    }

    public void setFreezeDou(BigDecimal freezeDou) {
        this.freezeDou = freezeDou;
    }

    public BigDecimal getSaleDou() {
        return saleDou;
    }

    public void setSaleDou(BigDecimal saleDou) {
        this.saleDou = saleDou;
    }
}
