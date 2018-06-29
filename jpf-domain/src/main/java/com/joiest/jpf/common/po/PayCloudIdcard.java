package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayCloudIdcard implements Serializable {
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

    /**
     *
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", num=").append(num);
        sb.append(", nationality=").append(nationality);
        sb.append(", address=").append(address);
        sb.append(", birth=").append(birth);
        sb.append(", sex=").append(sex);
        sb.append(", faceimglocal=").append(faceimglocal);
        sb.append(", faceimgserver=").append(faceimgserver);
        sb.append(", frequestId=").append(frequestId);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", issue=").append(issue);
        sb.append(", brequestId=").append(brequestId);
        sb.append(", backimglocal=").append(backimglocal);
        sb.append(", backimgserver=").append(backimgserver);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append("]");
        return sb.toString();
    }

    /**
     *
     * @param that
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PayCloudIdcard other = (PayCloudIdcard) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getNationality() == null ? other.getNationality() == null : this.getNationality().equals(other.getNationality()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getBirth() == null ? other.getBirth() == null : this.getBirth().equals(other.getBirth()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getFaceimglocal() == null ? other.getFaceimglocal() == null : this.getFaceimglocal().equals(other.getFaceimglocal()))
            && (this.getFaceimgserver() == null ? other.getFaceimgserver() == null : this.getFaceimgserver().equals(other.getFaceimgserver()))
            && (this.getFrequestId() == null ? other.getFrequestId() == null : this.getFrequestId().equals(other.getFrequestId()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getIssue() == null ? other.getIssue() == null : this.getIssue().equals(other.getIssue()))
            && (this.getBrequestId() == null ? other.getBrequestId() == null : this.getBrequestId().equals(other.getBrequestId()))
            && (this.getBackimglocal() == null ? other.getBackimglocal() == null : this.getBackimglocal().equals(other.getBackimglocal()))
            && (this.getBackimgserver() == null ? other.getBackimgserver() == null : this.getBackimgserver().equals(other.getBackimgserver()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getNationality() == null) ? 0 : getNationality().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getBirth() == null) ? 0 : getBirth().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getFaceimglocal() == null) ? 0 : getFaceimglocal().hashCode());
        result = prime * result + ((getFaceimgserver() == null) ? 0 : getFaceimgserver().hashCode());
        result = prime * result + ((getFrequestId() == null) ? 0 : getFrequestId().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getIssue() == null) ? 0 : getIssue().hashCode());
        result = prime * result + ((getBrequestId() == null) ? 0 : getBrequestId().hashCode());
        result = prime * result + ((getBackimglocal() == null) ? 0 : getBackimglocal().hashCode());
        result = prime * result + ((getBackimgserver() == null) ? 0 : getBackimgserver().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}