package com.joiest.jpf.entity;

import java.util.Date;

public class CloudCompanyStaffInfo {
    /**
     * 自增ID
     */
    private String id;

    /**
     * 企业添加人ID
     */
    private String uid;

    /**
     * 员工名称
     */
    private String nickname;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 聚合商户企业编号
     */
    private String merchNo;

    /**
     * 删除状态：1:正常，-1:离职, -2:彻底删除
     */
    private Byte status;

    /**
     * 是否签约 0=未签约 1=已签约
     */
    private Byte isActive;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 新用户 激活验证码
     */
    private String code;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 对应pay_cloud_idcard用户身份证信息id
     */
    private String ucardid;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改时间
     */
    private Date updated;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getUcardid() {
        return ucardid;
    }

    public void setUcardid(String ucardid) {
        this.ucardid = ucardid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}


