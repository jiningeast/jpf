package com.joiest.jpf.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

public class CloudRechargeNeedReleaseRequest {
    /**
     * 需求ID ','隔开
     */
    @NotEmpty(message = "needid不能为空！")
    private String needid;

    /**
     * catpath 集合，','隔开
     */
    @NotEmpty(message = "needcatpath不能为空！")
    private String needcatpath;

    /**
     * 代理聚合商户编号 merch_no
     */
    @NotEmpty(message = "agentNo不能为空！")
    private String agentNo;

    /**
     * 到账聚合商户编号 merch_no
     */
    @NotEmpty(message = "merchNo不能为空！")
    private String merchNo;

    /**
     * 支付方式 1=线下
     */
    @NotNull(message = "payway不能为空！")
    @Pattern(regexp = "1", message = "payway错误")
    private Byte payway;

    /**
     * 操作人ID
     */
    private Long employeeUid;

    /**
     * 操作人手机号
     */
    private String linkphone;

    /**
     * 操作人邮箱
     */
    private String linkemail;

    /**
     * 金额
     */
    @NotNull(message = "money不能为空")
    @Digits(integer = 7, fraction = 2, message = "金额单位为元,不能超过1千万")
    @DecimalMin(value = "0.01", message = "金额不能小于1分")
    private BigDecimal money;

    /**
     * 汇款总金额
     */
    @NotNull(message = "realmoney不能为空")
    @Digits(integer = 7, fraction = 2, message = "汇款总金额单位为元,不能超过1千万")
    @DecimalMin(value = "0.01", message = "金额不能小于1分")
    private BigDecimal realmoney;

    /**
     * 服务费
     */
    @NotNull(message = "feemoney不能为空")
    @Digits(integer = 7, fraction = 2, message = "服务费单位为元,不能超过1千万")
    @DecimalMin(value = "0.01", message = "金额不能小于1分")
    private BigDecimal feemoney;

    /**
     * 需求确认时间
     */
    @NotEmpty(message = "pacttime不能为空！")
    @Pattern(regexp = "[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s+(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d", message = "pacttime格式错误，正确的格式：yyyy-MM-dd HH:mm:ss")
    private String pacttime;


    public String getNeedid() {
        return needid;
    }

    public void setNeedid(String needid) {
        this.needid = needid;
    }

    public String getNeedcatpath() {
        return needcatpath;
    }

    public void setNeedcatpath(String needcatpath) {
        this.needcatpath = needcatpath;
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

    public Byte getPayway() {
        return payway;
    }

    public void setPayway(Byte payway) {
        this.payway = payway;
    }

    public Long getEmployeeUid() {
        return employeeUid;
    }

    public void setEmployeeUid(Long employeeUid) {
        this.employeeUid = employeeUid;
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone;
    }

    public String getLinkemail() {
        return linkemail;
    }

    public void setLinkemail(String linkemail) {
        this.linkemail = linkemail;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getRealmoney() {
        return realmoney;
    }

    public void setRealmoney(BigDecimal realmoney) {
        this.realmoney = realmoney;
    }

    public BigDecimal getFeemoney() {
        return feemoney;
    }

    public void setFeemoney(BigDecimal feemoney) {
        this.feemoney = feemoney;
    }

    public String getPacttime() {
        return pacttime;
    }

    public void setPacttime(String pacttime) {
        this.pacttime = pacttime;
    }

    @Override
    public String toString() {
        return "CloudRechargeNeedReleaseRequest{" +
                "needid='" + needid + '\'' +
                ", needcatpath='" + needcatpath + '\'' +
                ", agentNo='" + agentNo + '\'' +
                ", merchNo='" + merchNo + '\'' +
                ", payway=" + payway +
                ", employeeUid=" + employeeUid +
                ", linkphone='" + linkphone + '\'' +
                ", linkemail='" + linkemail + '\'' +
                ", money=" + money +
                ", realmoney=" + realmoney +
                ", feemoney=" + feemoney +
                ", pacttime=" + pacttime +
                '}';
    }
}
