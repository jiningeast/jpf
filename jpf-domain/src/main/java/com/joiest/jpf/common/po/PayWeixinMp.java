package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayWeixinMp implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * id加密值 md5后大写
     */
    private String encrypt;

    /**
     * 公众号名称
     */
    private String name;

    /**
     * 公众号关注二维码
     */
    private String followqr;

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
     * 关注回复
     */
    private String followreply;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFollowqr() {
        return followqr;
    }

    public void setFollowqr(String followqr) {
        this.followqr = followqr == null ? null : followqr.trim();
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

    public String getFollowreply() {
        return followreply;
    }

    public void setFollowreply(String followreply) {
        this.followreply = followreply == null ? null : followreply.trim();
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
        sb.append(", encrypt=").append(encrypt);
        sb.append(", name=").append(name);
        sb.append(", followqr=").append(followqr);
        sb.append(", appid=").append(appid);
        sb.append(", appsecret=").append(appsecret);
        sb.append(", token=").append(token);
        sb.append(", accesstoken=").append(accesstoken);
        sb.append(", tokenexpires=").append(tokenexpires);
        sb.append(", followreply=").append(followreply);
        sb.append(", merchant=").append(merchant);
        sb.append(", merkey=").append(merkey);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
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
        PayWeixinMp other = (PayWeixinMp) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEncrypt() == null ? other.getEncrypt() == null : this.getEncrypt().equals(other.getEncrypt()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getFollowqr() == null ? other.getFollowqr() == null : this.getFollowqr().equals(other.getFollowqr()))
            && (this.getAppid() == null ? other.getAppid() == null : this.getAppid().equals(other.getAppid()))
            && (this.getAppsecret() == null ? other.getAppsecret() == null : this.getAppsecret().equals(other.getAppsecret()))
            && (this.getToken() == null ? other.getToken() == null : this.getToken().equals(other.getToken()))
            && (this.getAccesstoken() == null ? other.getAccesstoken() == null : this.getAccesstoken().equals(other.getAccesstoken()))
            && (this.getTokenexpires() == null ? other.getTokenexpires() == null : this.getTokenexpires().equals(other.getTokenexpires()))
            && (this.getFollowreply() == null ? other.getFollowreply() == null : this.getFollowreply().equals(other.getFollowreply()))
            && (this.getMerchant() == null ? other.getMerchant() == null : this.getMerchant().equals(other.getMerchant()))
            && (this.getMerkey() == null ? other.getMerkey() == null : this.getMerkey().equals(other.getMerkey()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
            && (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEncrypt() == null) ? 0 : getEncrypt().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getFollowqr() == null) ? 0 : getFollowqr().hashCode());
        result = prime * result + ((getAppid() == null) ? 0 : getAppid().hashCode());
        result = prime * result + ((getAppsecret() == null) ? 0 : getAppsecret().hashCode());
        result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
        result = prime * result + ((getAccesstoken() == null) ? 0 : getAccesstoken().hashCode());
        result = prime * result + ((getTokenexpires() == null) ? 0 : getTokenexpires().hashCode());
        result = prime * result + ((getFollowreply() == null) ? 0 : getFollowreply().hashCode());
        result = prime * result + ((getMerchant() == null) ? 0 : getMerchant().hashCode());
        result = prime * result + ((getMerkey() == null) ? 0 : getMerkey().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        return result;
    }
}