package com.joiest.jpf.dto;

import java.math.BigDecimal;

public class ModifyChargeProductRequest {

    /**
     * 主键id
     */
    private String id;

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
    private String suppllierId;

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

    private String operatorId;

    private String operatorName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSuppllierId() {
        return suppllierId;
    }

    public void setSuppllierId(String suppllierId) {
        this.suppllierId = suppllierId;
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
}
