package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class PayOrderCpsingle implements Serializable {
    /**
     * 自增ID
     */
    private BigInteger id;

    /**
     * 退单ID
     */
    private String tdorderid;

    /**
     * 商品订单ID
     */
    private String orderid;

    /**
     * 退单金额
     */
    private BigDecimal tdorderprice;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 1:支付后退单，2:未支付撤单
     */
    private Byte singletype;

    /**
     * 财务|运营|商服退款审核：0:待审核; 1:审核通过，银联退款中；2：审核驳回；3：银联退款成功；4：银联退款失败
     */
    private Byte singlestatus;

    /**
     * 财务通过/驳回原因
     */
    private String operateContent;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 
     */
    private String refundContent;

    private static final long serialVersionUID = 1L;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTdorderid() {
        return tdorderid;
    }

    public void setTdorderid(String tdorderid) {
        this.tdorderid = tdorderid == null ? null : tdorderid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public BigDecimal getTdorderprice() {
        return tdorderprice;
    }

    public void setTdorderprice(BigDecimal tdorderprice) {
        this.tdorderprice = tdorderprice;
    }

    public Long getMtsid() {
        return mtsid;
    }

    public void setMtsid(Long mtsid) {
        this.mtsid = mtsid;
    }

    public Byte getSingletype() {
        return singletype;
    }

    public void setSingletype(Byte singletype) {
        this.singletype = singletype;
    }

    public Byte getSinglestatus() {
        return singlestatus;
    }

    public void setSinglestatus(Byte singlestatus) {
        this.singlestatus = singlestatus;
    }

    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent == null ? null : operateContent.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getRefundContent() {
        return refundContent;
    }

    public void setRefundContent(String refundContent) {
        this.refundContent = refundContent == null ? null : refundContent.trim();
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
        sb.append(", tdorderid=").append(tdorderid);
        sb.append(", orderid=").append(orderid);
        sb.append(", tdorderprice=").append(tdorderprice);
        sb.append(", mtsid=").append(mtsid);
        sb.append(", singletype=").append(singletype);
        sb.append(", singlestatus=").append(singlestatus);
        sb.append(", operateContent=").append(operateContent);
        sb.append(", addtime=").append(addtime);
        sb.append(", refundContent=").append(refundContent);
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
        PayOrderCpsingle other = (PayOrderCpsingle) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTdorderid() == null ? other.getTdorderid() == null : this.getTdorderid().equals(other.getTdorderid()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getTdorderprice() == null ? other.getTdorderprice() == null : this.getTdorderprice().equals(other.getTdorderprice()))
            && (this.getMtsid() == null ? other.getMtsid() == null : this.getMtsid().equals(other.getMtsid()))
            && (this.getSingletype() == null ? other.getSingletype() == null : this.getSingletype().equals(other.getSingletype()))
            && (this.getSinglestatus() == null ? other.getSinglestatus() == null : this.getSinglestatus().equals(other.getSinglestatus()))
            && (this.getOperateContent() == null ? other.getOperateContent() == null : this.getOperateContent().equals(other.getOperateContent()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getRefundContent() == null ? other.getRefundContent() == null : this.getRefundContent().equals(other.getRefundContent()));
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
        result = prime * result + ((getOperateContent() == null) ? 0 : getOperateContent().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getRefundContent() == null) ? 0 : getRefundContent().hashCode());
        return result;
    }
}