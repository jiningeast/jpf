package com.joiest.jpf.entity;

import java.util.Date;

public class BankInfo {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 银行名称
     */
    private String paybankname;

    /**
     * 银行类型
     */
    private String tpid;

    /**
     * 银行编码
     */
    private String bankcode;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改时间
     */
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaybankname() {
        return paybankname;
    }

    public void setPaybankname(String paybankname) {
        this.paybankname = paybankname;
    }

    public String getTpid() {
        return tpid;
    }

    public void setTpid(String tpid) {
        this.tpid = tpid;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
