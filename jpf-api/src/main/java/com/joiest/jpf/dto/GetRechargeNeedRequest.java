package com.joiest.jpf.dto;

import java.util.List;

public class GetRechargeNeedRequest {
    /**
     * 主键
     */
    private String id;

    /**
     * 交易充值记录ID，唯一值，CZ+10位时间戳+6位随机码
     */
    private String fid;

    /**
     * 合同编号
     */
    private String pactno;

    /**
     * 代理聚合商户编号 merch_no
     */
    private String agentNo;

    /**
     * 到账聚合商户编号 merch_no
     */
    private String merchNo;

    /**
     * 支付方式 1=线下
     */
    private String payway;

    /**
     * 操作人ID
     */
    private String employeeUid;

    /**
     * 状态 ：1:申请中(需求提交，合同待审核first) 2:已审核(已签约，待上传付款凭证) 3:已支付(已上传凭证) 4:已充值开票中(第二次审核); 5:已充值已开票; 6:已发货; 7:已完成(客户收到发票) 0:已取消
     */
    private String status;

    private List<String> statusList;

    /**
     * 申请时间
     */
    private String addtimeStart;
    private String addtimeEnd;

    /**
     * 审核时间或取消时间
     */
    private String shenhetimeStart;
    private String shenhetimeEnd;

    /**
     * 充值时间
     */
    private String chargetimeStart;
    private String chargetimeEnd;

    /**
     * 需求确认时间
     */
    private String pacttimeStart;
    private String pacttimeEnd;

    /**
     * 需求确认：1默认，2:确认需求
     */
    private String pactstatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getPactno() {
        return pactno;
    }

    public void setPactno(String pactno) {
        this.pactno = pactno;
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getEmployeeUid() {
        return employeeUid;
    }

    public void setEmployeeUid(String employeeUid) {
        this.employeeUid = employeeUid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getAddtimeStart() {
        return addtimeStart;
    }

    public void setAddtimeStart(String addtimeStart) {
        this.addtimeStart = addtimeStart;
    }

    public String getAddtimeEnd() {
        return addtimeEnd;
    }

    public void setAddtimeEnd(String addtimeEnd) {
        this.addtimeEnd = addtimeEnd;
    }

    public String getShenhetimeStart() {
        return shenhetimeStart;
    }

    public void setShenhetimeStart(String shenhetimeStart) {
        this.shenhetimeStart = shenhetimeStart;
    }

    public String getShenhetimeEnd() {
        return shenhetimeEnd;
    }

    public void setShenhetimeEnd(String shenhetimeEnd) {
        this.shenhetimeEnd = shenhetimeEnd;
    }

    public String getChargetimeStart() {
        return chargetimeStart;
    }

    public void setChargetimeStart(String chargetimeStart) {
        this.chargetimeStart = chargetimeStart;
    }

    public String getChargetimeEnd() {
        return chargetimeEnd;
    }

    public void setChargetimeEnd(String chargetimeEnd) {
        this.chargetimeEnd = chargetimeEnd;
    }

    public String getPacttimeStart() {
        return pacttimeStart;
    }

    public void setPacttimeStart(String pacttimeStart) {
        this.pacttimeStart = pacttimeStart;
    }

    public String getPacttimeEnd() {
        return pacttimeEnd;
    }

    public void setPacttimeEnd(String pacttimeEnd) {
        this.pacttimeEnd = pacttimeEnd;
    }

    public String getPactstatus() {
        return pactstatus;
    }

    public void setPactstatus(String pactstatus) {
        this.pactstatus = pactstatus;
    }

    @Override
    public String toString() {
        return "GetRechargeNeedRequest{" +
                "id='" + id + '\'' +
                ", fid='" + fid + '\'' +
                ", pactno='" + pactno + '\'' +
                ", agentNo='" + agentNo + '\'' +
                ", merchNo='" + merchNo + '\'' +
                ", payway='" + payway + '\'' +
                ", employeeUid='" + employeeUid + '\'' +
                ", status='" + status + '\'' +
                ", statusList=" + statusList +
                ", addtimeStart='" + addtimeStart + '\'' +
                ", addtimeEnd='" + addtimeEnd + '\'' +
                ", shenhetimeStart='" + shenhetimeStart + '\'' +
                ", shenhetimeEnd='" + shenhetimeEnd + '\'' +
                ", chargetimeStart='" + chargetimeStart + '\'' +
                ", chargetimeEnd='" + chargetimeEnd + '\'' +
                ", pacttimeStart='" + pacttimeStart + '\'' +
                ", pacttimeEnd='" + pacttimeEnd + '\'' +
                ", pactstatus='" + pactstatus + '\'' +
                '}';
    }
}
