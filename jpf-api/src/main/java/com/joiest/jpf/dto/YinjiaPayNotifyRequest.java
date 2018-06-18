package com.joiest.jpf.dto;

/**
 * 银嘉分期支付接口请求类
 */
public class YinjiaPayNotifyRequest {

    /**
     * 商户号
     */
    private String sysMerchNo;

    /**
     * 编码字符集
     */
    private String inputCharset;

    /**
     * 签名类型
     */
    private String signType;

    /**
     * 签名值
     */
    private String sign;

    //业务参数:
    /**
     * 交易流水号
     */
    private String tranNo;

    /**
     * 商户订单号
     */
    private String outOrderNo;

    /**
     * 交易完成时间
     */
    private String finishTime;

    /**
     * 交易类型
     */
    private String tranType;

    /**
     * 交易金额
     */
    private String tranAmt;

    /**
     * 交易结果
     */
    private String tranResult;

    /**
     * 原商户订单号
     * 原商户订单号。tranType为13/14时存在
     */
    private String oriOrderNo;

    public String getSysMerchNo() {
        return sysMerchNo;
    }

    public void setSysMerchNo(String sysMerchNo) {
        this.sysMerchNo = sysMerchNo;
    }

    public String getInputCharset() {
        return inputCharset;
    }

    public void setInputCharset(String inputCharset) {
        this.inputCharset = inputCharset;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTranNo() {
        return tranNo;
    }

    public void setTranNo(String tranNo) {
        this.tranNo = tranNo;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(String tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getTranResult() {
        return tranResult;
    }

    public void setTranResult(String tranResult) {
        this.tranResult = tranResult;
    }

    public String getOriOrderNo() {
        return oriOrderNo;
    }

    public void setOriOrderNo(String oriOrderNo) {
        this.oriOrderNo = oriOrderNo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("YinjiaPayNotifyRequest{");
        sb.append("sysMerchNo='").append(sysMerchNo).append('\'');
        sb.append(", inputCharset='").append(inputCharset).append('\'');
        sb.append(", signType='").append(signType).append('\'');
        sb.append(", sign='").append(sign).append('\'');
        sb.append(", tranNo='").append(tranNo).append('\'');
        sb.append(", outOrderNo='").append(outOrderNo).append('\'');
        sb.append(", finishTime='").append(finishTime).append('\'');
        sb.append(", tranType='").append(tranType).append('\'');
        sb.append(", tranAmt='").append(tranAmt).append('\'');
        sb.append(", tranResult='").append(tranResult).append('\'');
        sb.append(", oriOrderNo='").append(oriOrderNo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
