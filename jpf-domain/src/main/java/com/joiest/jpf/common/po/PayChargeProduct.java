package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayChargeProduct implements Serializable {
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

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        this.wnProductId = wnProductId == null ? null : wnProductId.trim();
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
        this.brandId = brandId == null ? null : brandId.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getSuppllierId() {
        return suppllierId;
    }

    public void setSuppllierId(String suppllierId) {
        this.suppllierId = suppllierId == null ? null : suppllierId.trim();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
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
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
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
        sb.append(", name=").append(name);
        sb.append(", value=").append(value);
        sb.append(", bidPrice=").append(bidPrice);
        sb.append(", salePrice=").append(salePrice);
        sb.append(", wnProductId=").append(wnProductId);
        sb.append(", forceInterface=").append(forceInterface);
        sb.append(", interfaceType=").append(interfaceType);
        sb.append(", brandId=").append(brandId);
        sb.append(", brandName=").append(brandName);
        sb.append(", suppllierId=").append(suppllierId);
        sb.append(", supplierName=").append(supplierName);
        sb.append(", typeId=").append(typeId);
        sb.append(", typeName=").append(typeName);
        sb.append(", isOnSale=").append(isOnSale);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
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
        PayChargeProduct other = (PayChargeProduct) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getBidPrice() == null ? other.getBidPrice() == null : this.getBidPrice().equals(other.getBidPrice()))
            && (this.getSalePrice() == null ? other.getSalePrice() == null : this.getSalePrice().equals(other.getSalePrice()))
            && (this.getWnProductId() == null ? other.getWnProductId() == null : this.getWnProductId().equals(other.getWnProductId()))
            && (this.getForceInterface() == null ? other.getForceInterface() == null : this.getForceInterface().equals(other.getForceInterface()))
            && (this.getInterfaceType() == null ? other.getInterfaceType() == null : this.getInterfaceType().equals(other.getInterfaceType()))
            && (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()))
            && (this.getBrandName() == null ? other.getBrandName() == null : this.getBrandName().equals(other.getBrandName()))
            && (this.getSuppllierId() == null ? other.getSuppllierId() == null : this.getSuppllierId().equals(other.getSuppllierId()))
            && (this.getSupplierName() == null ? other.getSupplierName() == null : this.getSupplierName().equals(other.getSupplierName()))
            && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
            && (this.getTypeName() == null ? other.getTypeName() == null : this.getTypeName().equals(other.getTypeName()))
            && (this.getIsOnSale() == null ? other.getIsOnSale() == null : this.getIsOnSale().equals(other.getIsOnSale()))
            && (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
            && (this.getOperatorName() == null ? other.getOperatorName() == null : this.getOperatorName().equals(other.getOperatorName()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getBidPrice() == null) ? 0 : getBidPrice().hashCode());
        result = prime * result + ((getSalePrice() == null) ? 0 : getSalePrice().hashCode());
        result = prime * result + ((getWnProductId() == null) ? 0 : getWnProductId().hashCode());
        result = prime * result + ((getForceInterface() == null) ? 0 : getForceInterface().hashCode());
        result = prime * result + ((getInterfaceType() == null) ? 0 : getInterfaceType().hashCode());
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        result = prime * result + ((getBrandName() == null) ? 0 : getBrandName().hashCode());
        result = prime * result + ((getSuppllierId() == null) ? 0 : getSuppllierId().hashCode());
        result = prime * result + ((getSupplierName() == null) ? 0 : getSupplierName().hashCode());
        result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
        result = prime * result + ((getTypeName() == null) ? 0 : getTypeName().hashCode());
        result = prime * result + ((getIsOnSale() == null) ? 0 : getIsOnSale().hashCode());
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getOperatorName() == null) ? 0 : getOperatorName().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}