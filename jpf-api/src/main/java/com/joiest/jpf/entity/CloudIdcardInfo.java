package com.joiest.jpf.entity;

import java.util.Date;

public class CloudIdcardInfo {

    /**
     *
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String num;

    /**
     * 民族
     */
    private String nationality;

    /**
     * 地址
     */
    private String address;

    /**
     * 生日
     */
    private String birth;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身份证正面照片(本地服务器)
     */
    private String faceimglocal;

    /**
     * 身份证正面照片(阿里云图片服务器)
     */
    private String faceimgserver;

    /**
     * ocr正面请求id
     */
    private String frequestId;

    /**
     * 有效期开始日期
     */
    private String startDate;

    /**
     * 有效期结束日期
     */
    private String endDate;

    /**
     * 发证机关
     */
    private String issue;

    /**
     * ocr正面请求id
     */
    private String brequestId;

    /**
     * 身份证背面照片(本地服务器)
     */
    private String backimglocal;

    /**
     * 身份证背面照片(阿里云图片服务器)
     */
    private String backimgserver;

    /**
     *
     */
    private Date addtime;

    /**
     *
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth == null ? null : birth.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getFaceimglocal() {
        return faceimglocal;
    }

    public void setFaceimglocal(String faceimglocal) {
        this.faceimglocal = faceimglocal == null ? null : faceimglocal.trim();
    }

    public String getFaceimgserver() {
        return faceimgserver;
    }

    public void setFaceimgserver(String faceimgserver) {
        this.faceimgserver = faceimgserver == null ? null : faceimgserver.trim();
    }

    public String getFrequestId() {
        return frequestId;
    }

    public void setFrequestId(String frequestId) {
        this.frequestId = frequestId == null ? null : frequestId.trim();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue == null ? null : issue.trim();
    }

    public String getBrequestId() {
        return brequestId;
    }

    public void setBrequestId(String brequestId) {
        this.brequestId = brequestId == null ? null : brequestId.trim();
    }

    public String getBackimglocal() {
        return backimglocal;
    }

    public void setBackimglocal(String backimglocal) {
        this.backimglocal = backimglocal == null ? null : backimglocal.trim();
    }

    public String getBackimgserver() {
        return backimgserver;
    }

    public void setBackimgserver(String backimgserver) {
        this.backimgserver = backimgserver == null ? null : backimgserver.trim();
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
