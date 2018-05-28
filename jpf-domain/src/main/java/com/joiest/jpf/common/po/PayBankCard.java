package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayBankCard implements Serializable {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 发卡行名称
     */
    private String bankname;

    /**
     * 
     */
    private String title;

    /**
     * 机构代码
     */
    private String code;

    /**
     * 卡名
     */
    private String cardname;

    /**
     * 是否支持ATM机 1支持，0不支持
     */
    private String isatm;

    /**
     * 是否支持pos机 1支持 0不支持
     */
    private String ispos;

    /**
     * 卡长度
     */
    private Integer cardlen;

    /**
     * 
     */
    private String cardpre;

    /**
     * 卡前缀的长度
     */
    private String cardprelen;

    /**
     * 卡类型
     */
    private String type;

    /**
     * 状态  1正常   0失效
     */
    private Integer status;

    /**
     * 
     */
    private Date createtime;

    /**
     * 聚合支付所需code
     */
    private String findcode;

    /**
     * 单笔限额(易宝)
     */
    private BigDecimal singlelimit;

    /**
     * 单日限额（易宝）
     */
    private BigDecimal dailylimit;

    /**
     * 月限额（易宝）
     */
    private BigDecimal monthlyquota;

    /**
     * 排序
     */
    private String sort;

    /**
     * 来源
     */
    private String source;

    /**
     * 银行图片(大图标) 
     */
    private String images;

    /**
     * 银行小图片，小logo
     */
    private String smalimages;

    /**
     * 快捷code
     */
    private String yftcode;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname == null ? null : cardname.trim();
    }

    public String getIsatm() {
        return isatm;
    }

    public void setIsatm(String isatm) {
        this.isatm = isatm == null ? null : isatm.trim();
    }

    public String getIspos() {
        return ispos;
    }

    public void setIspos(String ispos) {
        this.ispos = ispos == null ? null : ispos.trim();
    }

    public Integer getCardlen() {
        return cardlen;
    }

    public void setCardlen(Integer cardlen) {
        this.cardlen = cardlen;
    }

    public String getCardpre() {
        return cardpre;
    }

    public void setCardpre(String cardpre) {
        this.cardpre = cardpre == null ? null : cardpre.trim();
    }

    public String getCardprelen() {
        return cardprelen;
    }

    public void setCardprelen(String cardprelen) {
        this.cardprelen = cardprelen == null ? null : cardprelen.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getFindcode() {
        return findcode;
    }

    public void setFindcode(String findcode) {
        this.findcode = findcode == null ? null : findcode.trim();
    }

    public BigDecimal getSinglelimit() {
        return singlelimit;
    }

    public void setSinglelimit(BigDecimal singlelimit) {
        this.singlelimit = singlelimit;
    }

    public BigDecimal getDailylimit() {
        return dailylimit;
    }

    public void setDailylimit(BigDecimal dailylimit) {
        this.dailylimit = dailylimit;
    }

    public BigDecimal getMonthlyquota() {
        return monthlyquota;
    }

    public void setMonthlyquota(BigDecimal monthlyquota) {
        this.monthlyquota = monthlyquota;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getSmalimages() {
        return smalimages;
    }

    public void setSmalimages(String smalimages) {
        this.smalimages = smalimages == null ? null : smalimages.trim();
    }

    public String getYftcode() {
        return yftcode;
    }

    public void setYftcode(String yftcode) {
        this.yftcode = yftcode == null ? null : yftcode.trim();
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
        sb.append(", bankname=").append(bankname);
        sb.append(", title=").append(title);
        sb.append(", code=").append(code);
        sb.append(", cardname=").append(cardname);
        sb.append(", isatm=").append(isatm);
        sb.append(", ispos=").append(ispos);
        sb.append(", cardlen=").append(cardlen);
        sb.append(", cardpre=").append(cardpre);
        sb.append(", cardprelen=").append(cardprelen);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", createtime=").append(createtime);
        sb.append(", findcode=").append(findcode);
        sb.append(", singlelimit=").append(singlelimit);
        sb.append(", dailylimit=").append(dailylimit);
        sb.append(", monthlyquota=").append(monthlyquota);
        sb.append(", sort=").append(sort);
        sb.append(", source=").append(source);
        sb.append(", images=").append(images);
        sb.append(", smalimages=").append(smalimages);
        sb.append(", yftcode=").append(yftcode);
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
        PayBankCard other = (PayBankCard) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBankname() == null ? other.getBankname() == null : this.getBankname().equals(other.getBankname()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getCardname() == null ? other.getCardname() == null : this.getCardname().equals(other.getCardname()))
            && (this.getIsatm() == null ? other.getIsatm() == null : this.getIsatm().equals(other.getIsatm()))
            && (this.getIspos() == null ? other.getIspos() == null : this.getIspos().equals(other.getIspos()))
            && (this.getCardlen() == null ? other.getCardlen() == null : this.getCardlen().equals(other.getCardlen()))
            && (this.getCardpre() == null ? other.getCardpre() == null : this.getCardpre().equals(other.getCardpre()))
            && (this.getCardprelen() == null ? other.getCardprelen() == null : this.getCardprelen().equals(other.getCardprelen()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getFindcode() == null ? other.getFindcode() == null : this.getFindcode().equals(other.getFindcode()))
            && (this.getSinglelimit() == null ? other.getSinglelimit() == null : this.getSinglelimit().equals(other.getSinglelimit()))
            && (this.getDailylimit() == null ? other.getDailylimit() == null : this.getDailylimit().equals(other.getDailylimit()))
            && (this.getMonthlyquota() == null ? other.getMonthlyquota() == null : this.getMonthlyquota().equals(other.getMonthlyquota()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getImages() == null ? other.getImages() == null : this.getImages().equals(other.getImages()))
            && (this.getSmalimages() == null ? other.getSmalimages() == null : this.getSmalimages().equals(other.getSmalimages()))
            && (this.getYftcode() == null ? other.getYftcode() == null : this.getYftcode().equals(other.getYftcode()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBankname() == null) ? 0 : getBankname().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getCardname() == null) ? 0 : getCardname().hashCode());
        result = prime * result + ((getIsatm() == null) ? 0 : getIsatm().hashCode());
        result = prime * result + ((getIspos() == null) ? 0 : getIspos().hashCode());
        result = prime * result + ((getCardlen() == null) ? 0 : getCardlen().hashCode());
        result = prime * result + ((getCardpre() == null) ? 0 : getCardpre().hashCode());
        result = prime * result + ((getCardprelen() == null) ? 0 : getCardprelen().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getFindcode() == null) ? 0 : getFindcode().hashCode());
        result = prime * result + ((getSinglelimit() == null) ? 0 : getSinglelimit().hashCode());
        result = prime * result + ((getDailylimit() == null) ? 0 : getDailylimit().hashCode());
        result = prime * result + ((getMonthlyquota() == null) ? 0 : getMonthlyquota().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getImages() == null) ? 0 : getImages().hashCode());
        result = prime * result + ((getSmalimages() == null) ? 0 : getSmalimages().hashCode());
        result = prime * result + ((getYftcode() == null) ? 0 : getYftcode().hashCode());
        return result;
    }
}