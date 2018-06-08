package com.joiest.jpf.dto;

import java.util.HashMap;
import java.util.Map;

public class YinjiaSignNotifyRequest {

    /**
     * 合作商户的商户号
     */
    private String sysMerchNo;

    /**
     * 交互信息时使用的编码字符集，默认为UTF-8
     */
    private String inputCharset;

    /**
     * 签名类型
     */
    private String signType;

    /**
     * 数据的加密校验字符串
     */
    private String sign;
    /**
     * 交易流水号
     */
    private String tranNo;

    /**
     * 商户订单号
     */
    private String outOrderNo;

    /**
     * 签约状态
     */
    private String signStatus;

    /**
     * 渠道编码
     */
    private String chnCode;

    /**
     * 渠道账户编号
     */
    private String chnAcctId;

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

    public String getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }

    public String getChnCode() {
        return chnCode;
    }

    public void setChnCode(String chnCode) {
        this.chnCode = chnCode;
    }

    public String getChnAcctId() {
        return chnAcctId;
    }

    public void setChnAcctId(String chnAcctId) {
        this.chnAcctId = chnAcctId;
    }

    public Map<String, Object> toMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("signStatus", signStatus);
        map.put("sysMerchNo", sysMerchNo);
        map.put("sign", sign);
        map.put("signType", signType);
        map.put("chnCode", chnCode);
        map.put("inputCharset", inputCharset);
        map.put("tranNo", tranNo);
        map.put("chnAcctId", chnAcctId);
        map.put("outOrderNo", outOrderNo);

        /*TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(map);*/

        return map;
    }
}
