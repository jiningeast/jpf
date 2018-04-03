package com.joiest.jpf.dto;

public class AddMerPayTypeRequest {

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 支付类型
     */
    private Integer tpid;

    /**
     * 支付类型catpath
     */
    private String catpath;

    public Long getMtsid() {
        return mtsid;
    }

    public void setMtsid(Long mtsid) {
        this.mtsid = mtsid;
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public String getCatpath() {
        return catpath;
    }

    public void setCatpath(String catpath) {
        this.catpath = catpath;
    }
}
