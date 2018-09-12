package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ShopProductInterfaceInfo {

    /**
     *
     */
    private String id;

    /**
     * 商品基础信息id
     */
    private Integer productInfoId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品编号
     */
    private String cardid;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品售价
     */
    private BigDecimal money;

    /**
     * 充值面额
     */
    private Integer rechargeMoney;

    /**
     * 商品进价
     */
    private BigDecimal bid;

    /**
     * 豆
     */
    private Integer dou;

    /**
     * 产品简介
     */
    private String intro;

    /**
     * 库存数量
     */
    private Integer stored;

    /**
     * 安全库存 当商品数量到达这个数时不可交易
     */
    private Integer storedSafe;

    /**
     * 出售类型 0=库存形式 1=接口形式
     */
    private Byte storedType;

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
     * 充值类型 0=直冲 1=代充 2=卡密 3=混合
     */
    private Byte type;

    /**
     * 创建时间
     */
    private Date addtime;

    /**
     *
     */
    private Date updatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getProductInfoId() {
        return productInfoId;
    }

    public void setProductInfoId(Integer productInfoId) {
        this.productInfoId = productInfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Integer rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public Integer getDou() {
        return dou;
    }

    public void setDou(Integer dou) {
        this.dou = dou;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getStored() {
        return stored;
    }

    public void setStored(Integer stored) {
        this.stored = stored;
    }

    public Integer getStoredSafe() {
        return storedSafe;
    }

    public void setStoredSafe(Integer storedSafe) {
        this.storedSafe = storedSafe;
    }

    public Byte getStoredType() {
        return storedType;
    }

    public void setStoredType(Byte storedType) {
        this.storedType = storedType;
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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    //================ 自定义字段 begin ================
    /**
     * 商品基础信息id
     */
    private Integer infoId;
    /**
     * 商品分类id
     */
    private Integer typeId;

    /**
     * 商品分类
     */
    private String typeName;

    /**
     *
     */
    private Integer supplierId;

    /**
     * 供应商
     */
    private String supplierName;

    /**
     *
     */
    private Integer brandId;

    /**
     * 品牌
     */
    private String brandName;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     *
     */
    private String contactPhone;

    /**
     *
     */
    private String contactEmail;

    /**
     * 备注
     */
    private String remark;

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    //================ 自定义字段 end ================
}
