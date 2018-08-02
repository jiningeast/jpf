package com.joiest.jpf.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class ShopProductInfoRequest {

    /**
     *
     */
    private String id;

    /**
     *
     */
    @NotBlank(message = "请选择商品分类")
    private String typeId;

    /**
     * 商品分类
     */
    @NotBlank(message = "商品分类不能为空")
    private String typeName;

    /**
     *
     */
    @NotBlank(message = "请选择供应商")
    private String supplierId;

    /**
     * 供应商
     */
    @NotBlank(message = "供应商名称不能为空")
    private String supplierName;

    /**
     *
     */
    @NotBlank(message = "请选择品牌")
    private String brandId;

    /**
     * 品牌
     */
    @NotBlank(message = "品牌名称不能为空")
    private String brandName;

    /**
     * 联系人姓名
     */
    @NotBlank(message = "请填写联系人姓名")
    private String contactName;

    /**
     *
     */
    @NotBlank(message = "请填写联系人电话")
    private String contactPhone;

    /**
     *
     */
    @NotBlank(message = "请填写联系人邮箱")
    private String contactEmail;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作人
     */
    private Integer operatorId;

    /**
     * 操作人
     */
    private String operatorName;

    /**
     * 状态 0:显示 1:不显示
     */
    private Byte status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
