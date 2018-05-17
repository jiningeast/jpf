package com.joiest.jpf.common.dto;

/**
 * 前台页面返回code
 */
public class YjResponseDto {

    /**
     * 状态码 默认"10000"为正常的响应码, 代表操作完成, 具体交易状态, 以交易业务状态码为准
     */
    private String Code = "10000";

    /**
     * 返回消息 默认正常响应下为"SUCCESS"
     */
    private String Info = "SUCCESS";

//    /**
//     * 状态码 默认"10000"为正常的响应码, 代表操作完成, 具体交易状态, 以交易业务状态码为准
//     */
//    private String front_retCode_success = "10000";
//
//    /**
//     * 失败
//     */
//    private String front_retCode_fail = "10008";
//
//    /**
//     * 未登录
//     */
//    private String front_retCode_notLogin = "10007";
//
//    /**
//     * 短信验证失败
//     */
//    private String front_retCode_smsValiFail = "10005";
//
//    /**
//     * 商户信息获取失败
//     */
//    private String front_retCode_merInfoFail = "10009";
//
//    /**
//     * 商户未绑定储蓄卡
//     */
//    private String front_retCode_notBindCard = "10006";
//
//    /**
//     * 用户未签约
//     */
//    private String front_retCode_userNotSign = "10010";
//
//    /**
//     * 用户已签约
//     */
//    private String front_retCode_userAlreadysign = "10011";


    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }
}
