package com.joiest.jpf.common.po;

import java.io.Serializable;

public class PayTdorderWithBLOBs extends PayTdorder implements Serializable {
    /**
     * 运营驳回理由
     */
    private String refuseContent;

    /**
     * 申请理由
     */
    private String content;

    private static final long serialVersionUID = 1L;

    public String getRefuseContent() {
        return refuseContent;
    }

    public void setRefuseContent(String refuseContent) {
        this.refuseContent = refuseContent == null ? null : refuseContent.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
        sb.append(", refuseContent=").append(refuseContent);
        sb.append(", content=").append(content);
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
        PayTdorderWithBLOBs other = (PayTdorderWithBLOBs) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTdorderid() == null ? other.getTdorderid() == null : this.getTdorderid().equals(other.getTdorderid()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getTdorderprice() == null ? other.getTdorderprice() == null : this.getTdorderprice().equals(other.getTdorderprice()))
            && (this.getMtsid() == null ? other.getMtsid() == null : this.getMtsid().equals(other.getMtsid()))
            && (this.getSingletype() == null ? other.getSingletype() == null : this.getSingletype().equals(other.getSingletype()))
            && (this.getSinglestatus() == null ? other.getSinglestatus() == null : this.getSinglestatus().equals(other.getSinglestatus()))
            && (this.getUrla() == null ? other.getUrla() == null : this.getUrla().equals(other.getUrla()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getRefuseContent() == null ? other.getRefuseContent() == null : this.getRefuseContent().equals(other.getRefuseContent()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTdorderid() == null) ? 0 : getTdorderid().hashCode());
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getTdorderprice() == null) ? 0 : getTdorderprice().hashCode());
        result = prime * result + ((getMtsid() == null) ? 0 : getMtsid().hashCode());
        result = prime * result + ((getSingletype() == null) ? 0 : getSingletype().hashCode());
        result = prime * result + ((getSinglestatus() == null) ? 0 : getSinglestatus().hashCode());
        result = prime * result + ((getUrla() == null) ? 0 : getUrla().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getRefuseContent() == null) ? 0 : getRefuseContent().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }
}