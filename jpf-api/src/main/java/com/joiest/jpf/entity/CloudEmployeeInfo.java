package com.joiest.jpf.entity;

import java.util.Date;

public class CloudEmployeeInfo{
    /**
     *
     */
    private Integer uid;

    /**
     * 聚合商户编号
     */
    private String merchNo;

    /**
     * 手机号
     */
    private String linkphone;

    /**
     * 邮箱
     */
    private String linkemail;

    /**
     * 角色ID集合
     */
    private String roles;

    /**
     * 状态：-1:禁闭。1:正常
     */
    private Byte status;

    /**
     *
     */
    private String regip;

    /**
     *
     */
    private Integer regdate;

    /**
     * 企业登录密码
     */
    private String cloudloginpwd;

    /**
     * 联系人姓名
     */
    private String linkname;

    /**
     * 省份
     */
    private Long province;

    /**
     * 城市
     */
    private Long city;

    /**
     * 地址详情
     */
    private String address;

    /**
     * 创建时间
     */
    private Date created;

    private static final long serialVersionUID = 1L;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo == null ? null : merchNo.trim();
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone == null ? null : linkphone.trim();
    }

    public String getLinkemail() {
        return linkemail;
    }

    public void setLinkemail(String linkemail) {
        this.linkemail = linkemail == null ? null : linkemail.trim();
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles == null ? null : roles.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRegip() {
        return regip;
    }

    public void setRegip(String regip) {
        this.regip = regip == null ? null : regip.trim();
    }

    public Integer getRegdate() {
        return regdate;
    }

    public void setRegdate(Integer regdate) {
        this.regdate = regdate;
    }

    public String getCloudloginpwd() {
        return cloudloginpwd;
    }

    public void setCloudloginpwd(String cloudloginpwd) {
        this.cloudloginpwd = cloudloginpwd == null ? null : cloudloginpwd.trim();
    }

    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname == null ? null : linkname.trim();
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
