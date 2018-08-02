package com.joiest.jpf.entity;

import java.util.Date;

public class ShopProductTypeInfo {

    /**
     *
     */
    private Integer pid;

    /**
     * 商品类型名称
     */
    private String pname;

    /**
     * 图片地址
     */
    private String imgurl;

    /**
     * 商品类型状态 0:显示 1:不显示
     */
    private Byte status;

    /**
     * 操作人
     */
    private Integer operatorId;

    /**
     * 操作人
     */
    private String operatorName;

    /**
     *
     */
    private Date addtime;

    /**
     *
     */
    private Date updatetime;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
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
