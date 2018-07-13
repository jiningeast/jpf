package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayCloudDfOrder implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private Long orderid;

    /**
     * df订单ID
     */
    private String foreignOrderid;

    /**
     * 交易金额
     */
    private BigDecimal applyamt;

    /**
     * 订单标准金额
     */
    private BigDecimal orderStdPrice;

    /**
     * 请求信息
     */
    private String requeststr;

    /**
     * 外来请求字符串
     */
    private String foreignRequest;

    /**
     * 银行编码
     */
    private String finacode;

    /**
     * 收款人银行账号
     */
    private String payeeacct;

    /**
     * 收款人银行户名
     */
    private String payeename;

    /**
     * 收款人账号属性
     */
    private String payeeacctattr;

    /**
     * 用户手机号
     */
    private String phoneno;

    /**
     * 身份证号
     */
    private String certno;

    /**
     * 添加时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 交易号
     */
    private String tranno;

    /**
     * 订单状态 1处理中
     */
    private Byte status;

    /**
     * 代付状态 00提交申请 01审核通过 02申请被拒绝 03已打批次 04提交到渠道 05代付成功 06代付失败
     */
    private String dfstatus;

    /**
     * 同步返回信息
     */
    private String returncontent;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getForeignOrderid() {
        return foreignOrderid;
    }

    public void setForeignOrderid(String foreignOrderid) {
        this.foreignOrderid = foreignOrderid == null ? null : foreignOrderid.trim();
    }

    public BigDecimal getApplyamt() {
        return applyamt;
    }

    public void setApplyamt(BigDecimal applyamt) {
        this.applyamt = applyamt;
    }

    public BigDecimal getOrderStdPrice() {
        return orderStdPrice;
    }

    public void setOrderStdPrice(BigDecimal orderStdPrice) {
        this.orderStdPrice = orderStdPrice;
    }

    public String getRequeststr() {
        return requeststr;
    }

    public void setRequeststr(String requeststr) {
        this.requeststr = requeststr == null ? null : requeststr.trim();
    }

    public String getForeignRequest() {
        return foreignRequest;
    }

    public void setForeignRequest(String foreignRequest) {
        this.foreignRequest = foreignRequest == null ? null : foreignRequest.trim();
    }

    public String getFinacode() {
        return finacode;
    }

    public void setFinacode(String finacode) {
        this.finacode = finacode == null ? null : finacode.trim();
    }

    public String getPayeeacct() {
        return payeeacct;
    }

    public void setPayeeacct(String payeeacct) {
        this.payeeacct = payeeacct == null ? null : payeeacct.trim();
    }

    public String getPayeename() {
        return payeename;
    }

    public void setPayeename(String payeename) {
        this.payeename = payeename == null ? null : payeename.trim();
    }

    public String getPayeeacctattr() {
        return payeeacctattr;
    }

    public void setPayeeacctattr(String payeeacctattr) {
        this.payeeacctattr = payeeacctattr == null ? null : payeeacctattr.trim();
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }

    public String getCertno() {
        return certno;
    }

    public void setCertno(String certno) {
        this.certno = certno == null ? null : certno.trim();
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

    public String getTranno() {
        return tranno;
    }

    public void setTranno(String tranno) {
        this.tranno = tranno == null ? null : tranno.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getDfstatus() {
        return dfstatus;
    }

    public void setDfstatus(String dfstatus) {
        this.dfstatus = dfstatus == null ? null : dfstatus.trim();
    }

    public String getReturncontent() {
        return returncontent;
    }

    public void setReturncontent(String returncontent) {
        this.returncontent = returncontent == null ? null : returncontent.trim();
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
        sb.append(", orderid=").append(orderid);
        sb.append(", foreignOrderid=").append(foreignOrderid);
        sb.append(", applyamt=").append(applyamt);
        sb.append(", orderStdPrice=").append(orderStdPrice);
        sb.append(", requeststr=").append(requeststr);
        sb.append(", foreignRequest=").append(foreignRequest);
        sb.append(", finacode=").append(finacode);
        sb.append(", payeeacct=").append(payeeacct);
        sb.append(", payeename=").append(payeename);
        sb.append(", payeeacctattr=").append(payeeacctattr);
        sb.append(", phoneno=").append(phoneno);
        sb.append(", certno=").append(certno);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", tranno=").append(tranno);
        sb.append(", status=").append(status);
        sb.append(", dfstatus=").append(dfstatus);
        sb.append(", returncontent=").append(returncontent);
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
        PayCloudDfOrder other = (PayCloudDfOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getForeignOrderid() == null ? other.getForeignOrderid() == null : this.getForeignOrderid().equals(other.getForeignOrderid()))
            && (this.getApplyamt() == null ? other.getApplyamt() == null : this.getApplyamt().equals(other.getApplyamt()))
            && (this.getOrderStdPrice() == null ? other.getOrderStdPrice() == null : this.getOrderStdPrice().equals(other.getOrderStdPrice()))
            && (this.getRequeststr() == null ? other.getRequeststr() == null : this.getRequeststr().equals(other.getRequeststr()))
            && (this.getForeignRequest() == null ? other.getForeignRequest() == null : this.getForeignRequest().equals(other.getForeignRequest()))
            && (this.getFinacode() == null ? other.getFinacode() == null : this.getFinacode().equals(other.getFinacode()))
            && (this.getPayeeacct() == null ? other.getPayeeacct() == null : this.getPayeeacct().equals(other.getPayeeacct()))
            && (this.getPayeename() == null ? other.getPayeename() == null : this.getPayeename().equals(other.getPayeename()))
            && (this.getPayeeacctattr() == null ? other.getPayeeacctattr() == null : this.getPayeeacctattr().equals(other.getPayeeacctattr()))
            && (this.getPhoneno() == null ? other.getPhoneno() == null : this.getPhoneno().equals(other.getPhoneno()))
            && (this.getCertno() == null ? other.getCertno() == null : this.getCertno().equals(other.getCertno()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
            && (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()))
            && (this.getTranno() == null ? other.getTranno() == null : this.getTranno().equals(other.getTranno()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDfstatus() == null ? other.getDfstatus() == null : this.getDfstatus().equals(other.getDfstatus()))
            && (this.getReturncontent() == null ? other.getReturncontent() == null : this.getReturncontent().equals(other.getReturncontent()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getForeignOrderid() == null) ? 0 : getForeignOrderid().hashCode());
        result = prime * result + ((getApplyamt() == null) ? 0 : getApplyamt().hashCode());
        result = prime * result + ((getOrderStdPrice() == null) ? 0 : getOrderStdPrice().hashCode());
        result = prime * result + ((getRequeststr() == null) ? 0 : getRequeststr().hashCode());
        result = prime * result + ((getForeignRequest() == null) ? 0 : getForeignRequest().hashCode());
        result = prime * result + ((getFinacode() == null) ? 0 : getFinacode().hashCode());
        result = prime * result + ((getPayeeacct() == null) ? 0 : getPayeeacct().hashCode());
        result = prime * result + ((getPayeename() == null) ? 0 : getPayeename().hashCode());
        result = prime * result + ((getPayeeacctattr() == null) ? 0 : getPayeeacctattr().hashCode());
        result = prime * result + ((getPhoneno() == null) ? 0 : getPhoneno().hashCode());
        result = prime * result + ((getCertno() == null) ? 0 : getCertno().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        result = prime * result + ((getTranno() == null) ? 0 : getTranno().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDfstatus() == null) ? 0 : getDfstatus().hashCode());
        result = prime * result + ((getReturncontent() == null) ? 0 : getReturncontent().hashCode());
        return result;
    }
}