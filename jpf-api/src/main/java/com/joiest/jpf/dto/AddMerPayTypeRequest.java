package com.joiest.jpf.dto;

import java.util.List;

public class AddMerPayTypeRequest {

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 支付类型
     */
    private List<Integer> tpid;

    public Long getMtsid() {
        return mtsid;
    }

    public void setMtsid(Long mtsid) {
        this.mtsid = mtsid;
    }

    public List<Integer> getTpid() {
        return tpid;
    }

    public void setTpid(List<Integer> tpid) {
        this.tpid = tpid;
    }
}
