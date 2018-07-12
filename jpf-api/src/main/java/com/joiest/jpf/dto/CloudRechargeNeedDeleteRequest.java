package com.joiest.jpf.dto;

public class CloudRechargeNeedDeleteRequest {

    /**
     * 主键
     */
    private Long id;

    /**
     * 交易充值记录ID，唯一值，CZ+10位时间戳+6位随机码
     */
    private String fid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    @Override
    public String toString() {
        return "CloudRechargeNeedDeleteRequest{" +
                "id=" + id +
                ", fid='" + fid + '\'' +
                '}';
    }
}
