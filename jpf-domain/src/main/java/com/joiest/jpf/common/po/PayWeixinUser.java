package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayWeixinUser implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 隶属公众号id
     */
    private Long mpid;

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
     * 
     */
    private String qrScene;

    /**
     * 
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
        sb.append(", mpid=").append(mpid);
        sb.append(", openid=").append(openid);
        sb.append(", nickname=").append(nickname);
        sb.append(", nicknameencode=").append(nicknameencode);
        sb.append(", sex=").append(sex);
        sb.append(", language=").append(language);
        sb.append(", city=").append(city);
        sb.append(", province=").append(province);
        sb.append(", country=").append(country);
        sb.append(", headimgurl=").append(headimgurl);
        sb.append(", serverheadimg=").append(serverheadimg);
        sb.append(", subscribetime=").append(subscribetime);
        sb.append(", unionid=").append(unionid);
        sb.append(", remark=").append(remark);
        sb.append(", groupid=").append(groupid);
        sb.append(", tagidList=").append(tagidList);
        sb.append(", subscribeScene=").append(subscribeScene);
        sb.append(", qrScene=").append(qrScene);
        sb.append(", qrSceneStr=").append(qrSceneStr);
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
        PayWeixinUser other = (PayWeixinUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMpid() == null ? other.getMpid() == null : this.getMpid().equals(other.getMpid()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getNicknameencode() == null ? other.getNicknameencode() == null : this.getNicknameencode().equals(other.getNicknameencode()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getLanguage() == null ? other.getLanguage() == null : this.getLanguage().equals(other.getLanguage()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()))
            && (this.getHeadimgurl() == null ? other.getHeadimgurl() == null : this.getHeadimgurl().equals(other.getHeadimgurl()))
            && (this.getServerheadimg() == null ? other.getServerheadimg() == null : this.getServerheadimg().equals(other.getServerheadimg()))
            && (this.getSubscribetime() == null ? other.getSubscribetime() == null : this.getSubscribetime().equals(other.getSubscribetime()))
            && (this.getUnionid() == null ? other.getUnionid() == null : this.getUnionid().equals(other.getUnionid()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getGroupid() == null ? other.getGroupid() == null : this.getGroupid().equals(other.getGroupid()))
            && (this.getTagidList() == null ? other.getTagidList() == null : this.getTagidList().equals(other.getTagidList()))
            && (this.getSubscribeScene() == null ? other.getSubscribeScene() == null : this.getSubscribeScene().equals(other.getSubscribeScene()))
            && (this.getQrScene() == null ? other.getQrScene() == null : this.getQrScene().equals(other.getQrScene()))
            && (this.getQrSceneStr() == null ? other.getQrSceneStr() == null : this.getQrSceneStr().equals(other.getQrSceneStr()))
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
        result = prime * result + ((getMpid() == null) ? 0 : getMpid().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getNicknameencode() == null) ? 0 : getNicknameencode().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getLanguage() == null) ? 0 : getLanguage().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
        result = prime * result + ((getHeadimgurl() == null) ? 0 : getHeadimgurl().hashCode());
        result = prime * result + ((getServerheadimg() == null) ? 0 : getServerheadimg().hashCode());
        result = prime * result + ((getSubscribetime() == null) ? 0 : getSubscribetime().hashCode());
        result = prime * result + ((getUnionid() == null) ? 0 : getUnionid().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getGroupid() == null) ? 0 : getGroupid().hashCode());
        result = prime * result + ((getTagidList() == null) ? 0 : getTagidList().hashCode());
        result = prime * result + ((getSubscribeScene() == null) ? 0 : getSubscribeScene().hashCode());
        result = prime * result + ((getQrScene() == null) ? 0 : getQrScene().hashCode());
        result = prime * result + ((getQrSceneStr() == null) ? 0 : getQrSceneStr().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        return result;
    }
}