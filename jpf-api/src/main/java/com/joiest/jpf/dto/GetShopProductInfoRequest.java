package com.joiest.jpf.dto;

import java.math.BigDecimal;
import java.util.Date;

public class GetShopProductInfoRequest {
    /**
     *
     */
    private Integer id;

    /**
     *
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

    /**
     *
     */
    private Date addtime;

    /**
     *
     */
    private Date updatetime;

    //自定义字段 商品基础信息str
    private String contentId;

    private String content;

    /**
     * 品牌名称
     */
    private String title;

    /**
     * 品牌图片
     */
    private String imgurl;

    /**
     * 价格范围区间
     */
    private String moneyscope;

    /**
     * 分页
     */
    private long page;

    /**
     * 查询数量
     */
    private long rows;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getMoneyscope() {
        return moneyscope;
    }

    public void setMoneyscope(String moneyscope) {
        this.moneyscope = moneyscope;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }
}
