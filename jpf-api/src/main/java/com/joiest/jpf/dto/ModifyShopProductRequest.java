package com.joiest.jpf.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

public class ModifyShopProductRequest {
    /**
     *
     */
    private String id;

    /**
     * 商品基础信息id
     */
    @NotBlank(message = "请选择商品基础信息")
    private String productInfoId;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String name;

    /**
     * 商品编号
     */
    private String cardid;

    /**
     * 商品图片
     */
    @NotBlank(message = "请上传商品图片")
    private String image;

    /**
     * 金额
     */
//    @Pattern(regexp = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$", message = "商品金额格式错误")
//    private String money;

    /**
     * 商品进价
     */
//    @Pattern(regexp = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$", message = "商品进价格式错误")
//    private String bid;

    /**
     * 豆
     */
    @Digits(integer=5,fraction=0, message = "欣豆只能为整数")
    private String dou;

    /**
     * 产品简介
     */
    private String intro;

    /**
     * 库存数量
     */
    private String stored;

    /**
     * 安全库存 当商品数量到达这个数时不可交易
     */
    @NotNull(message = "请填写安全库存")
    private String storedSafe;

    /**
     * 出售类型 0=库存形式 1=接口形式
     */
//    @NotNull(message = "请选择商品出售形式")
//    private Byte storedType;

    /**
     * 操作人
     */
    private Integer operatorId;

    /**
     * 操作人
     */
    private String operatorName;

    /**
     * 商品状态：0=下架 1=上架
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date addtime;

    /**
     *
     */
    private Date updatetime;

    /**
     * 充值面额
     */
    @Digits(integer=5,fraction=0, message = "充值面额只能为整数")
    private String rechargeMoney;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductInfoId() {
        return productInfoId;
    }

    public void setProductInfoId(String productInfoId) {
        this.productInfoId = productInfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDou() {
        return dou;
    }

    public void setDou(String dou) {
        this.dou = dou;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getStored() {
        return stored;
    }

    public void setStored(String stored) {
        this.stored = stored;
    }

    public String getStoredSafe() {
        return storedSafe;
    }

    public void setStoredSafe(String storedSafe) {
        this.storedSafe = storedSafe;
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

    public String getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(String rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }
}
