package com.joiest.jpf.entity;

import java.math.BigInteger;
import java.util.Date;

public class CloudIdenauthInfo {
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

}
