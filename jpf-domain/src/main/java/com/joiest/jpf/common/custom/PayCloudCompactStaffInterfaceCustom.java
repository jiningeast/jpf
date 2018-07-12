package com.joiest.jpf.common.custom;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayCloudCompactStaffInterfaceCustom implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 自由职业者合同编号：身份证后六位+时间戳
     */
    private String compactNo;

    /**
     * 自由职业者ID
     */
    private Long staffid;

    /**
     * 自由职业者代付ID
     */
    private Long dfid;

    /**
     * 企业发布需求合同编号
     */
    private String pactno;

    /**
     * 合同内容
     */
    private String content;

    /**
     * 用户状态  0 未签协议合同  1  已签协议合同
     */
    private Byte compactActive;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 签约时间
     */
    private Date updated;

    //============== 自定义添加 Begin ===================
    /**
     * 收款人
     */
    private String banknickname;

    /**
     * 发放金额
     */
    private BigDecimal commoney;

    /**
     * 公司名称
     */
    private String name;

    public String getBanknickname() {
        return banknickname;
    }

    public void setBanknickname(String banknickname) {
        this.banknickname = banknickname;
    }

    public BigDecimal getCommoney() {
        return commoney;
    }

    public void setCommoney(BigDecimal commoney) {
        this.commoney = commoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    //============== 自定义添加 End ===================

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompactNo() {
        return compactNo;
    }

    public void setCompactNo(String compactNo) {
        this.compactNo = compactNo == null ? null : compactNo.trim();
    }

    public Long getStaffid() {
        return staffid;
    }

    public void setStaffid(Long staffid) {
        this.staffid = staffid;
    }

    public Long getDfid() {
        return dfid;
    }

    public void setDfid(Long dfid) {
        this.dfid = dfid;
    }

    public String getPactno() {
        return pactno;
    }

    public void setPactno(String pactno) {
        this.pactno = pactno == null ? null : pactno.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getCompactActive() {
        return compactActive;
    }

    public void setCompactActive(Byte compactActive) {
        this.compactActive = compactActive;
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
}