package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BankCardInfo {
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
    private Boolean status;

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
        this.bankname = bankname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getIsatm() {
        return isatm;
    }

    public void setIsatm(String isatm) {
        this.isatm = isatm;
    }

    public String getIspos() {
        return ispos;
    }

    public void setIspos(String ispos) {
        this.ispos = ispos;
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
        this.cardpre = cardpre;
    }

    public String getCardprelen() {
        return cardprelen;
    }

    public void setCardprelen(String cardprelen) {
        this.cardprelen = cardprelen;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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
        this.findcode = findcode;
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
        this.sort = sort;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getSmalimages() {
        return smalimages;
    }

    public void setSmalimages(String smalimages) {
        this.smalimages = smalimages;
    }

    public String getYftcode() {
        return yftcode;
    }

    public void setYftcode(String yftcode) {
        this.yftcode = yftcode;
    }
}
