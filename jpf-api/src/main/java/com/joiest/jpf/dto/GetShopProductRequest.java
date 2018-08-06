package com.joiest.jpf.dto;

import java.math.BigDecimal;
import java.util.Date;

public class GetShopProductRequest {

    /**
     *
     */
    private String id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 商品状态：0=下架 1=上架
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date addtime;

    private long page;

    private long rows;

    //品牌id
    private String brandId;

    //商品类型id
    private String ptype;

    //供应商id
    private String supplierId;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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
