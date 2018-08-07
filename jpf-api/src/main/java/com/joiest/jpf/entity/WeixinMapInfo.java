package com.joiest.jpf.entity;

import java.util.Date;

public class WeixinMapInfo {

    /**
     *
     */
    private Long id;

    /**
     * id加密值 md5后大写
     */
    private String encrypt;

    /**
     * 开发者ID(AppID)
     */
    private String appid;

    /**
     * 开发者密码(AppSecret)
     */
    private String appsecret;

    /**
     * 服务器配置令牌token
     */
    private String token;

    /**
     * 接口所需token
     */
    private String accesstoken;

    /**
     * token有效期截止
     */
    private Date tokenexpires;

    /**
     * 商户号，微信商户平台
     */
    private String merchant;

    /**
     * 商户对应key
     */
    private String merkey;

    /**
     *
     */
    private Date created;

    /**
     *
     */
    private Date updated;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt == null ? null : encrypt.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret == null ? null : appsecret.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken == null ? null : accesstoken.trim();
    }

    public Date getTokenexpires() {
        return tokenexpires;
    }

    public void setTokenexpires(Date tokenexpires) {
        this.tokenexpires = tokenexpires;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant == null ? null : merchant.trim();
    }

    public String getMerkey() {
        return merkey;
    }

    public void setMerkey(String merkey) {
        this.merkey = merkey == null ? null : merkey.trim();
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

}
