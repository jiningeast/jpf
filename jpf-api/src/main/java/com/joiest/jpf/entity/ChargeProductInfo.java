package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeProductInfo {
    /**
     * 主键id
     */
    private String id;

    /**
     * 产品类型 0=话费充值 1=油卡充值
     */
    private Integer type;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品面值
     */
    private BigDecimal value;

    /**
     * 产品进价
     */
    private BigDecimal bidPrice;

    /**
     * 产品标准售价
     */
    private BigDecimal salePrice;

    /**
     * 威能产品id
     */
    private String wnProductId;

    /**
     * 威能给我们的价格
     */
    private BigDecimal wnProductPrice;

    /**
     * 欧飞油卡产品id
     */
    private String ofProductId;

    /**
     * 欧飞给我们的价格
     */
    private BigDecimal ofProductPrice;

    /**
     * 是否强制使用某接口 0=否 1=是
     */
    private Byte forceInterface;

    /**
     * 强制使用接口时的接口类型 0=欧非 1=威能
     */
    private Byte interfaceType;

    /**
     * 品牌id
     */
    private String brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 供应商id
     */
    private String supplierId;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 类型id
     */
    private String typeId;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 运营商类型 1=移动 2=联通 3=电信
     */
    private Byte mobileType;

    /**
     * 上下架 0=下架 1=上架
     */
    private Byte isOnSale;

    /**
     * 后台添加人id
     */
    private String operatorId;

    /**
     * 后台添加人姓名
     */
    private String operatorName;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public String getWnProductId() {
        return wnProductId;
    }

    public void setWnProductId(String wnProductId) {
        this.wnProductId = wnProductId;
    }

    public BigDecimal getWnProductPrice() {
        return wnProductPrice;
    }

    public void setWnProductPrice(BigDecimal wnProductPrice) {
        this.wnProductPrice = wnProductPrice;
    }

    public String getOfProductId() {
        return ofProductId;
    }

    public void setOfProductId(String ofProductId) {
        this.ofProductId = ofProductId;
    }

    public BigDecimal getOfProductPrice() {
        return ofProductPrice;
    }

    public void setOfProductPrice(BigDecimal ofProductPrice) {
        this.ofProductPrice = ofProductPrice;
    }

    public Byte getForceInterface() {
        return forceInterface;
    }

    public void setForceInterface(Byte forceInterface) {
        this.forceInterface = forceInterface;
    }

    public Byte getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(Byte interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Byte getMobileType() {
        return mobileType;
    }

    public void setMobileType(Byte mobileType) {
        this.mobileType = mobileType;
    }

    public Byte getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Byte isOnSale) {
        this.isOnSale = isOnSale;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
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
