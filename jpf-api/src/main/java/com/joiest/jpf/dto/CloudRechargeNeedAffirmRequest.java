package com.joiest.jpf.dto;

public class CloudRechargeNeedAffirmRequest {

    /**
     * 主键
     */
    private Long id;

    /**
     * 交易充值记录ID，唯一值，CZ+10位时间戳+6位随机码
     */
    private String fid;

    /**
     * 需求确认：1默认，2:确认需求
     */
    private Byte pactstatus;
}
