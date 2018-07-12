package com.joiest.jpf.dto;

public class CloudRechargeNeedVoucherRequest {

    /**
     * 主键
     */
    private Long id;

    /**
     * 交易充值记录ID，唯一值，CZ+10位时间戳+6位随机码
     */
    private String fid;
    /**
     * 上传凭证
     */
    private String imgurl;
}
