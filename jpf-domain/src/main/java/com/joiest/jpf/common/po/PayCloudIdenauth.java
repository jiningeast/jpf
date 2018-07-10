package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class PayCloudIdenauth implements Serializable {
    /**
     * 
     */
    private BigInteger id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号码
     */
    private String num;

    /**
     * 认证状态1 成功，2失败
     */
    private Byte status;

    /**
     * 认证次数
     */
    private Integer count;

    /**
     * 阿里云接口返回参数
     */
    private String responseparam;

    /**
     * 接口返回数据
     */
    private String apiparam;

    /**
     * 
     */
    private Date addtime;

    /**
     * 
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getResponseparam() {
        return responseparam;
    }

    public void setResponseparam(String responseparam) {
        this.responseparam = responseparam == null ? null : responseparam.trim();
    }

    public String getApiparam() {
        return apiparam;
    }

    public void setApiparam(String apiparam) {
        this.apiparam = apiparam == null ? null : apiparam.trim();
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
        sb.append(", status=").append(status);
        sb.append(", count=").append(count);
        sb.append(", responseparam=").append(responseparam);
        sb.append(", apiparam=").append(apiparam);
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
        PayCloudIdenauth other = (PayCloudIdenauth) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCount() == null ? other.getCount() == null : this.getCount().equals(other.getCount()))
            && (this.getResponseparam() == null ? other.getResponseparam() == null : this.getResponseparam().equals(other.getResponseparam()))
            && (this.getApiparam() == null ? other.getApiparam() == null : this.getApiparam().equals(other.getApiparam()))
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
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCount() == null) ? 0 : getCount().hashCode());
        result = prime * result + ((getResponseparam() == null) ? 0 : getResponseparam().hashCode());
        result = prime * result + ((getApiparam() == null) ? 0 : getApiparam().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}