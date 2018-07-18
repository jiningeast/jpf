package com.joiest.jpf.entity;

import java.util.Date;

public class CloudCompactStaffInfo {
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
     * 合同模板ID
     */
    private Long compactid;

    /**
     * 服务内容id
     */
    private Integer ticketid;

    /**
     * 服务内容
     */
    private String ticketcontent;

    /**
     * 项目名称id
     */
    private String entryid;

    /**
     * 项目名称
     */
    private String entryname;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 签约时间
     */
    private Date updated;

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
        this.compactNo = compactNo;
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
        this.pactno = pactno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getCompactActive() {
        return compactActive;
    }

    public void setCompactActive(Byte compactActive) {
        this.compactActive = compactActive;
    }

    public Long getCompactid() {
        return compactid;
    }

    public void setCompactid(Long compactid) {
        this.compactid = compactid;
    }

    public Integer getTicketid() {
        return ticketid;
    }

    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
    }

    public String getTicketcontent() {
        return ticketcontent;
    }

    public void setTicketcontent(String ticketcontent) {
        this.ticketcontent = ticketcontent;
    }

    public String getEntryid() {
        return entryid;
    }

    public void setEntryid(String entryid) {
        this.entryid = entryid;
    }

    public String getEntryname() {
        return entryname;
    }

    public void setEntryname(String entryname) {
        this.entryname = entryname;
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
