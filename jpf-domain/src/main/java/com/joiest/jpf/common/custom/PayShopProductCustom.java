package com.joiest.jpf.common.custom;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayShopProductCustom implements Serializable {
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
     * 充值类型 0=直冲 1=代充 2=卡密 3=混合
     */
    private Byte type;

    /**
     * 商品状态：0=下架 1=上架
     */
    private Byte status;

    /**
     * 微能移动产品id
     */
    private Integer cmccProductId;

    /**
     * 微能联通产品id
     */
    private Integer cuccProductId;

    /**
     * 微能电信产品id
     */
    private Integer ctcProductId;

    /**
     * 创建时间
     */
    private Date addtime;

    /**
     * 
     */
    private Date updatetime;

    /**
     * 商品详情ID
     */
    private String productContentId;

    /**
     * 商品详情
     */
    private String productContent;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
        this.name = name == null ? null : name.trim();
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
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
        this.intro = intro == null ? null : intro.trim();
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
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getCmccProductId() {
        return cmccProductId;
    }

    public void setCmccProductId(Integer cmccProductId) {
        this.cmccProductId = cmccProductId;
    }

    public Integer getCuccProductId() {
        return cuccProductId;
    }

    public void setCuccProductId(Integer cuccProductId) {
        this.cuccProductId = cuccProductId;
    }

    public Integer getCtcProductId() {
        return ctcProductId;
    }

    public void setCtcProductId(Integer ctcProductId) {
        this.ctcProductId = ctcProductId;
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

    public String getProductContentId() {
        return productContentId;
    }

    public void setProductContentId(String productContentId) {
        this.productContentId = productContentId == null ? null : productContentId.trim();
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
        sb.append(", productInfoId=").append(productInfoId);
        sb.append(", name=").append(name);
        sb.append(", cardid=").append(cardid);
        sb.append(", image=").append(image);
        sb.append(", money=").append(money);
        sb.append(", rechargeMoney=").append(rechargeMoney);
        sb.append(", bid=").append(bid);
        sb.append(", dou=").append(dou);
        sb.append(", intro=").append(intro);
        sb.append(", stored=").append(stored);
        sb.append(", storedSafe=").append(storedSafe);
        sb.append(", storedType=").append(storedType);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", cmccProductId=").append(cmccProductId);
        sb.append(", cuccProductId=").append(cuccProductId);
        sb.append(", ctcProductId=").append(ctcProductId);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", productContentId=").append(productContentId);
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
        PayShopProductCustom other = (PayShopProductCustom) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductInfoId() == null ? other.getProductInfoId() == null : this.getProductInfoId().equals(other.getProductInfoId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCardid() == null ? other.getCardid() == null : this.getCardid().equals(other.getCardid()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getRechargeMoney() == null ? other.getRechargeMoney() == null : this.getRechargeMoney().equals(other.getRechargeMoney()))
            && (this.getBid() == null ? other.getBid() == null : this.getBid().equals(other.getBid()))
            && (this.getDou() == null ? other.getDou() == null : this.getDou().equals(other.getDou()))
            && (this.getIntro() == null ? other.getIntro() == null : this.getIntro().equals(other.getIntro()))
            && (this.getStored() == null ? other.getStored() == null : this.getStored().equals(other.getStored()))
            && (this.getStoredSafe() == null ? other.getStoredSafe() == null : this.getStoredSafe().equals(other.getStoredSafe()))
            && (this.getStoredType() == null ? other.getStoredType() == null : this.getStoredType().equals(other.getStoredType()))
            && (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
            && (this.getOperatorName() == null ? other.getOperatorName() == null : this.getOperatorName().equals(other.getOperatorName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCmccProductId() == null ? other.getCmccProductId() == null : this.getCmccProductId().equals(other.getCmccProductId()))
            && (this.getCuccProductId() == null ? other.getCuccProductId() == null : this.getCuccProductId().equals(other.getCuccProductId()))
            && (this.getCtcProductId() == null ? other.getCtcProductId() == null : this.getCtcProductId().equals(other.getCtcProductId()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getProductContentId() == null ? other.getProductContentId() == null : this.getProductContentId().equals(other.getProductContentId()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductInfoId() == null) ? 0 : getProductInfoId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCardid() == null) ? 0 : getCardid().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getRechargeMoney() == null) ? 0 : getRechargeMoney().hashCode());
        result = prime * result + ((getBid() == null) ? 0 : getBid().hashCode());
        result = prime * result + ((getDou() == null) ? 0 : getDou().hashCode());
        result = prime * result + ((getIntro() == null) ? 0 : getIntro().hashCode());
        result = prime * result + ((getStored() == null) ? 0 : getStored().hashCode());
        result = prime * result + ((getStoredSafe() == null) ? 0 : getStoredSafe().hashCode());
        result = prime * result + ((getStoredType() == null) ? 0 : getStoredType().hashCode());
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getOperatorName() == null) ? 0 : getOperatorName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCmccProductId() == null) ? 0 : getCmccProductId().hashCode());
        result = prime * result + ((getCuccProductId() == null) ? 0 : getCuccProductId().hashCode());
        result = prime * result + ((getCtcProductId() == null) ? 0 : getCtcProductId().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getProductContentId() == null) ? 0 : getProductContentId().hashCode());
        return result;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }
}