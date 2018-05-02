package com.joiest.jpf.dto;


import javax.validation.constraints.NotNull;
import java.util.List;

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
    @NotNull(message="微信商户号不能为空")
    private String wx_merSubMchid;

    private String[] bankcatid;

//    private List<String> bankcatid;

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
}
