package com.joiest.jpf.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class GetWeixinMpRequest {
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
    @NotBlank(message = "公众号名称不能为空")
    private String name;

    /**
     * 公众号关注二维码
     */
    @NotBlank(message = "二维码不能为空")
    private String followqr;

    /**
     * 开发者ID(AppID)
     */
    @NotBlank(message = "开发者ID不能为空")
    private String appid;

    /**
     * 开发者密码(AppSecret)
     */
    @NotBlank(message = "开发者密码不能为空")
    private String appsecret;

    /**
     * 服务器配置令牌token
     */
    @NotBlank(message = "令牌token不能为空")
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

    private long rows;

    private long page;

    /**
     * 注册时间
     */
    private String addtimeStart;

    private String addtimeEnd;


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

    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollowqr() {
        return followqr;
    }

    public void setFollowqr(String followqr) {
        this.followqr = followqr;
    }

    public String getAddtimeStart() {
        return addtimeStart;
    }

    public void setAddtimeStart(String addtimeStart) {
        this.addtimeStart = addtimeStart;
    }

    public String getAddtimeEnd() {
        return addtimeEnd;
    }

    public void setAddtimeEnd(String addtimeEnd) {
        this.addtimeEnd = addtimeEnd;
    }
}
