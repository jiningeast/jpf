package com.joiest.jpf.dto;

import org.hibernate.validator.constraints.NotBlank;

public class DfApiInterfaceRequest {

    /**
     * 批次号
     */
    @NotBlank(message = "批次号不能为空")
    private String batchid;

    /**
     * 单独id
     */
    private String dfid;

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }

    public String getDfid() {
        return dfid;
    }

    public void setDfid(String dfid) {
        this.dfid = dfid;
    }
}
