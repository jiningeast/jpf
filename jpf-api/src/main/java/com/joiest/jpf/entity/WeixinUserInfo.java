package com.joiest.jpf.entity;

import java.util.Date;

public class WeixinUserInfo {
    /**
     *
     */
    private Long id;

    /**
     * 隶属公众号id
     */
    private Long mpid;

    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    private Byte subscribe;

    /**
     * 公众号唯一标识
     */
    private String openid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * utf_encode 加密值
     */
    private String nicknameencode;

    /**
     * 1 男,2 女，0未知
     */
    private Byte sex;

    /**
     * 用户的语言，简体中文为zh_CN,zh_TW 繁体，en 英语
     */
    private String language;

    /**
     * 市区
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * 地区
     */
    private String country;

    /**
     * 微信头像
     */
    private String headimgurl;

    /**
     * oss头像地址
     */
    private String serverheadimg;

    /**
     * 关注时间
     */
    private Date subscribetime;

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    private String unionid;

    /**
     * 公众号运营者对粉丝的备注
     */
    private String remark;

    /**
     * 用户所在的分组ID
     */
    private Byte groupid;

    /**
     * 用户被打上的标签ID列表
     */
    private String tagidList;

    /**
     * 返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     */
    private String subscribeScene;

    /**
     * 二维码扫码场景
     */
    private String qrScene;

    /**
     * 二维码扫码场景描述
     */
    private String qrSceneStr;

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

    public Long getMpid() {
        return mpid;
    }

    public void setMpid(Long mpid) {
        this.mpid = mpid;
    }

    public Byte getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Byte subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getNicknameencode() {
        return nicknameencode;
    }

    public void setNicknameencode(String nicknameencode) {
        this.nicknameencode = nicknameencode == null ? null : nicknameencode.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public String getServerheadimg() {
        return serverheadimg;
    }

    public void setServerheadimg(String serverheadimg) {
        this.serverheadimg = serverheadimg == null ? null : serverheadimg.trim();
    }

    public Date getSubscribetime() {
        return subscribetime;
    }

    public void setSubscribetime(Date subscribetime) {
        this.subscribetime = subscribetime;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getGroupid() {
        return groupid;
    }

    public void setGroupid(Byte groupid) {
        this.groupid = groupid;
    }

    public String getTagidList() {
        return tagidList;
    }

    public void setTagidList(String tagidList) {
        this.tagidList = tagidList == null ? null : tagidList.trim();
    }

    public String getSubscribeScene() {
        return subscribeScene;
    }

    public void setSubscribeScene(String subscribeScene) {
        this.subscribeScene = subscribeScene == null ? null : subscribeScene.trim();
    }

    public String getQrScene() {
        return qrScene;
    }

    public void setQrScene(String qrScene) {
        this.qrScene = qrScene == null ? null : qrScene.trim();
    }

    public String getQrSceneStr() {
        return qrSceneStr;
    }

    public void setQrSceneStr(String qrSceneStr) {
        this.qrSceneStr = qrSceneStr == null ? null : qrSceneStr.trim();
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
