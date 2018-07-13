package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayCloudCompactStaff implements Serializable {
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
        this.ticketcontent = ticketcontent == null ? null : ticketcontent.trim();
    }

    public String getEntryid() {
        return entryid;
    }

    public void setEntryid(String entryid) {
        this.entryid = entryid == null ? null : entryid.trim();
    }

    public String getEntryname() {
        return entryname;
    }

    public void setEntryname(String entryname) {
        this.entryname = entryname == null ? null : entryname.trim();
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
        sb.append(", compactNo=").append(compactNo);
        sb.append(", staffid=").append(staffid);
        sb.append(", dfid=").append(dfid);
        sb.append(", pactno=").append(pactno);
        sb.append(", content=").append(content);
        sb.append(", compactActive=").append(compactActive);
        sb.append(", compactid=").append(compactid);
        sb.append(", ticketid=").append(ticketid);
        sb.append(", ticketcontent=").append(ticketcontent);
        sb.append(", entryid=").append(entryid);
        sb.append(", entryname=").append(entryname);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
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
        PayCloudCompactStaff other = (PayCloudCompactStaff) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompactNo() == null ? other.getCompactNo() == null : this.getCompactNo().equals(other.getCompactNo()))
            && (this.getStaffid() == null ? other.getStaffid() == null : this.getStaffid().equals(other.getStaffid()))
            && (this.getDfid() == null ? other.getDfid() == null : this.getDfid().equals(other.getDfid()))
            && (this.getPactno() == null ? other.getPactno() == null : this.getPactno().equals(other.getPactno()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCompactActive() == null ? other.getCompactActive() == null : this.getCompactActive().equals(other.getCompactActive()))
            && (this.getCompactid() == null ? other.getCompactid() == null : this.getCompactid().equals(other.getCompactid()))
            && (this.getTicketid() == null ? other.getTicketid() == null : this.getTicketid().equals(other.getTicketid()))
            && (this.getTicketcontent() == null ? other.getTicketcontent() == null : this.getTicketcontent().equals(other.getTicketcontent()))
            && (this.getEntryid() == null ? other.getEntryid() == null : this.getEntryid().equals(other.getEntryid()))
            && (this.getEntryname() == null ? other.getEntryname() == null : this.getEntryname().equals(other.getEntryname()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
            && (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompactNo() == null) ? 0 : getCompactNo().hashCode());
        result = prime * result + ((getStaffid() == null) ? 0 : getStaffid().hashCode());
        result = prime * result + ((getDfid() == null) ? 0 : getDfid().hashCode());
        result = prime * result + ((getPactno() == null) ? 0 : getPactno().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCompactActive() == null) ? 0 : getCompactActive().hashCode());
        result = prime * result + ((getCompactid() == null) ? 0 : getCompactid().hashCode());
        result = prime * result + ((getTicketid() == null) ? 0 : getTicketid().hashCode());
        result = prime * result + ((getTicketcontent() == null) ? 0 : getTicketcontent().hashCode());
        result = prime * result + ((getEntryid() == null) ? 0 : getEntryid().hashCode());
        result = prime * result + ((getEntryname() == null) ? 0 : getEntryname().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        return result;
    }
}