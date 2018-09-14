package com.joiest.jpf.entity;

import java.util.Date;

public class ShopBargainRequestInfo {
    /**
     * 主键id
     */
    private String id;

    /**
     * 发布人id
     */
    private String customerId;

    /**
     * 折损率
     */
    private Double offRate;

    /**
     * 最低豆数
     */
    private Integer minDou;

    /**
     * 是否开启此收豆信息 0=不开启 1=开启
     */
    private Byte status;

    /**
     * 添加时间
     */
    private Date addtime;


    /**
     * 微信昵称
     */
    private String nickname;


    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public Double getOffRate() {
        return offRate;
    }

    public void setOffRate(Double offRate) {
        this.offRate = offRate;
    }

    public Integer getMinDou() {
        return minDou;
    }

    public void setMinDou(Integer minDou) {
        this.minDou = minDou;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
