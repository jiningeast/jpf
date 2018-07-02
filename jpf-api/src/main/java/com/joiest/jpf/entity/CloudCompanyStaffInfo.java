package com.joiest.jpf.entity;

import java.math.BigInteger;
import java.util.Date;

public class CloudCompanyStaffInfo {

    /**
     * 自增ID
     */
    private BigInteger id;

    /**
     * 企业添加人ID
     */
    private Long uid;

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
     * 部门ID
     */
    private String dsid;

    /**
     * 职位ID
     */
    private Long jid;

    /**
     * 工号
     */
    private String jobno;

    /**
     * 删除状态：1:正常，-1:离职, -2:彻底删除
     */
    private String status;

    /**
     * 是否签约 0=未签约 1=已签约
     */
    private String isActive;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 1:男；2:女
     */
    private Boolean gender;

    /**
     * 入职日期
     */
    private Date entrydate;

    /**
     * 修改时间
     */
    private Date updated;

    /**
     * 生日
     */
    private Date birthday;

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
    private Integer ucardid;

    /**
     * 员工登录密码
     */
    private String password;

    private static final long serialVersionUID = 1L;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo == null ? null : merchNo.trim();
    }

    public String getDsid() {
        return dsid;
    }

    public void setDsid(String dsid) {
        this.dsid = dsid == null ? null : dsid.trim();
    }

    public Long getJid() {
        return jid;
    }

    public void setJid(Long jid) {
        this.jid = jid;
    }

    public String getJobno() {
        return jobno;
    }

    public void setJobno(String jobno) {
        this.jobno = jobno == null ? null : jobno.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive == null ? null : isActive.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(Date entrydate) {
        this.entrydate = entrydate;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public Integer getUcardid() {
        return ucardid;
    }

    public void setUcardid(Integer ucardid) {
        this.ucardid = ucardid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

}


