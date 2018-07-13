package com.joiest.jpf.entity;

import java.math.BigInteger;
import java.util.Date;

public class CloudBankcheckInfo {

    /**
     *
     */
    private BigInteger id;

    /**
     * 银行卡号
     */
    private String accountno;

    /**
     * 身份证
     */
    private String num;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 姓名
     */
    private String name;

    /**
     * 1 四要素鉴权   2 三要素鉴权
     */
    private Byte type;

    /**
     * 1 验证成功   2验证失败
     */
    private Byte status;

    /**
     * 鉴权次数
     */
    private Integer count;

    /**
     * 请求参数
     */
    private String requestparam;

    /**
     * 阿里云接口返回参数
     */
    private String responseparam;

    /**
     * 接口返回数据
     */
    private String apiparam;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno == null ? null : accountno.trim();
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public String getRequestparam() {
        return requestparam;
    }

    public void setRequestparam(String requestparam) {
        this.requestparam = requestparam == null ? null : requestparam.trim();
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

}
