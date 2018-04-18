package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PaySystemlog implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 0:前台；1:后台
     */
    private Integer logtype;

    /**
     * 登录用户ID
     */
    private Integer operatorUid;

    /**
     * 用户账号
     */
    private String operatorName;

    /**
     * ip
     */
    private String ip;

    /**
     * 唯一识别码
     */
    private String ip1;

    /**
     * 客户端
     */
    private Integer clients;

    /**
     * 操作表名称
     */
    private String tablename;

    /**
     * 操作人ID
     */
    private String record;

    /**
     * 操作类型：删除数据，修改数据，添加数据，等
     */
    private String action;

    /**
     * 数据记录，sql语句集合
     */
    private String content;

    /**
     * 时间
     */
    private Date created;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLogtype() {
        return logtype;
    }

    public void setLogtype(Integer logtype) {
        this.logtype = logtype;
    }

    public Integer getOperatorUid() {
        return operatorUid;
    }

    public void setOperatorUid(Integer operatorUid) {
        this.operatorUid = operatorUid;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getIp1() {
        return ip1;
    }

    public void setIp1(String ip1) {
        this.ip1 = ip1 == null ? null : ip1.trim();
    }

    public Integer getClients() {
        return clients;
    }

    public void setClients(Integer clients) {
        this.clients = clients;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename == null ? null : tablename.trim();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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
        sb.append(", logtype=").append(logtype);
        sb.append(", operatorUid=").append(operatorUid);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", ip=").append(ip);
        sb.append(", ip1=").append(ip1);
        sb.append(", clients=").append(clients);
        sb.append(", tablename=").append(tablename);
        sb.append(", record=").append(record);
        sb.append(", action=").append(action);
        sb.append(", content=").append(content);
        sb.append(", created=").append(created);
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
        PaySystemlog other = (PaySystemlog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLogtype() == null ? other.getLogtype() == null : this.getLogtype().equals(other.getLogtype()))
            && (this.getOperatorUid() == null ? other.getOperatorUid() == null : this.getOperatorUid().equals(other.getOperatorUid()))
            && (this.getOperatorName() == null ? other.getOperatorName() == null : this.getOperatorName().equals(other.getOperatorName()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getIp1() == null ? other.getIp1() == null : this.getIp1().equals(other.getIp1()))
            && (this.getClients() == null ? other.getClients() == null : this.getClients().equals(other.getClients()))
            && (this.getTablename() == null ? other.getTablename() == null : this.getTablename().equals(other.getTablename()))
            && (this.getRecord() == null ? other.getRecord() == null : this.getRecord().equals(other.getRecord()))
            && (this.getAction() == null ? other.getAction() == null : this.getAction().equals(other.getAction()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLogtype() == null) ? 0 : getLogtype().hashCode());
        result = prime * result + ((getOperatorUid() == null) ? 0 : getOperatorUid().hashCode());
        result = prime * result + ((getOperatorName() == null) ? 0 : getOperatorName().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getIp1() == null) ? 0 : getIp1().hashCode());
        result = prime * result + ((getClients() == null) ? 0 : getClients().hashCode());
        result = prime * result + ((getTablename() == null) ? 0 : getTablename().hashCode());
        result = prime * result + ((getRecord() == null) ? 0 : getRecord().hashCode());
        result = prime * result + ((getAction() == null) ? 0 : getAction().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        return result;
    }
}