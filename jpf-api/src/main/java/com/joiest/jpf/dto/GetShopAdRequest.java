package com.joiest.jpf.dto;

import com.joiest.jpf.common.po.PayShopAd;
import com.joiest.jpf.entity.CloudTaskInfo;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

public class GetShopAdRequest {
    /**
     *
     */
    private String id;

    /**
     * 图标文字
     */
    @NotBlank(message = "图片文字不能为空")
    //@Pattern( regexp = "[a-zA-Z0-9]{4,50}", message = "订单ID信息非法,请确认核对")
    private String title;

    /**
     * 图标地址
     */
    @NotBlank(message = "图片地址不能为空")
    private String imgUrl;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 排序 数越大越靠前
     */
    private Integer weight;

    /**
     * 广告类型 0=首页图标 1=首页轮播图 2=首页banner图片
     */
    //(message = "请选择广告类型")
    private Byte type;

    /**
     * 状态 0=不可见 1=可见
     */
    //@NotBlank(message = "请选择状态")
    private Byte status;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    private int page;

    private int rows;

    /**
     * 申请时间
     */
    private String addtimeStart;
    private String addtimeEnd;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}