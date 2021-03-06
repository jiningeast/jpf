package com.joiest.jpf.dto;

public class ModifyMerPayTypeRequest {

    /**
     * 自增ID
     */
    private Long id;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 支付类型
     */
    private Integer tpid;

    /**
     * 支付类型对应的参数
     */
    //============= 微信支付参数 Begin ==================
    //商户号
    private String wx_merSubMchid;

    //支付限额
    private String wx_payLimit;
    //=============微信支付参数 Begin ==================

    //分期类型集合
    private String[] bankcatid;

//    private List<String> bankcatid;

    //=============银联分期支付参数 Begin ==================
    /**
     * 商户号
     */
    private String cp_MerchaNo;

    /**
     * 渠道编码
     */
    private String cp_Code;

    /**
     * 渠道账户编号
     */
    private String cp_Acctid;

    /**
     * 商户签名秘钥
     */
    private String cp_Salt;

    /**
     * 属性配置2
     */
    private String yl_rate;
    //=============银联分期支付参数 End ==================

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

    public String getWx_merSubMchid() {
        return wx_merSubMchid;
    }

    public void setWx_merSubMchid(String wx_merSubMchid) {
        this.wx_merSubMchid = wx_merSubMchid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String[] getBankcatid() {
        return bankcatid;
    }

    public void setBankcatid(String[] bankcatid) {
        this.bankcatid = bankcatid;
    }

    public String getCp_MerchaNo() {
        return cp_MerchaNo;
    }

    public void setCp_MerchaNo(String cp_MerchaNo) {
        this.cp_MerchaNo = cp_MerchaNo;
    }

    public String getCp_Code() {
        return cp_Code;
    }

    public void setCp_Code(String cp_Code) {
        this.cp_Code = cp_Code;
    }

    public String getCp_Acctid() {
        return cp_Acctid;
    }

    public void setCp_Acctid(String cp_Acctid) {
        this.cp_Acctid = cp_Acctid;
    }

    public String getCp_Salt() {
        return cp_Salt;
    }

    public void setCp_Salt(String cp_Salt) {
        this.cp_Salt = cp_Salt;
    }

    public String getWx_payLimit() {
        return wx_payLimit;
    }

    public void setWx_payLimit(String wx_payLimit) {
        this.wx_payLimit = wx_payLimit;
    }

    public String getYl_rate() {
        return yl_rate;
    }

    public void setYl_rate(String yl_rate) {
        this.yl_rate = yl_rate;
    }
}
