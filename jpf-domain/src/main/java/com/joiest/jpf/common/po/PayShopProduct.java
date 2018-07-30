package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayShopProduct implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private Integer productType;

    /**
     * 
     */
    private String productTypeCn;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 金额
     */
    private BigDecimal money;

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
     * 商品状态：0=下架 1=上架
     */
    private Byte status;

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
     * 创建时间
     */
    private Date addtime;

    /**
     * 
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProductTypeCn() {
        return productTypeCn;
    }

    public void setProductTypeCn(String productTypeCn) {
        this.productTypeCn = productTypeCn == null ? null : productTypeCn.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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
        this.intro = intro == null ? null : intro.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
        sb.append(", productType=").append(productType);
        sb.append(", productTypeCn=").append(productTypeCn);
        sb.append(", name=").append(name);
        sb.append(", image=").append(image);
        sb.append(", money=").append(money);
        sb.append(", bid=").append(bid);
        sb.append(", dou=").append(dou);
        sb.append(", intro=").append(intro);
        sb.append(", status=").append(status);
        sb.append(", stored=").append(stored);
        sb.append(", storedSafe=").append(storedSafe);
        sb.append(", storedType=").append(storedType);
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
        PayShopProduct other = (PayShopProduct) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductType() == null ? other.getProductType() == null : this.getProductType().equals(other.getProductType()))
            && (this.getProductTypeCn() == null ? other.getProductTypeCn() == null : this.getProductTypeCn().equals(other.getProductTypeCn()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getBid() == null ? other.getBid() == null : this.getBid().equals(other.getBid()))
            && (this.getDou() == null ? other.getDou() == null : this.getDou().equals(other.getDou()))
            && (this.getIntro() == null ? other.getIntro() == null : this.getIntro().equals(other.getIntro()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getStored() == null ? other.getStored() == null : this.getStored().equals(other.getStored()))
            && (this.getStoredSafe() == null ? other.getStoredSafe() == null : this.getStoredSafe().equals(other.getStoredSafe()))
            && (this.getStoredType() == null ? other.getStoredType() == null : this.getStoredType().equals(other.getStoredType()))
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
        result = prime * result + ((getProductType() == null) ? 0 : getProductType().hashCode());
        result = prime * result + ((getProductTypeCn() == null) ? 0 : getProductTypeCn().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getBid() == null) ? 0 : getBid().hashCode());
        result = prime * result + ((getDou() == null) ? 0 : getDou().hashCode());
        result = prime * result + ((getIntro() == null) ? 0 : getIntro().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getStored() == null) ? 0 : getStored().hashCode());
        result = prime * result + ((getStoredSafe() == null) ? 0 : getStoredSafe().hashCode());
        result = prime * result + ((getStoredType() == null) ? 0 : getStoredType().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}