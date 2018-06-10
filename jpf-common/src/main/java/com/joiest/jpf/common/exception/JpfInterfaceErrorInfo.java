package com.joiest.jpf.common.exception;

/**
 * 错误码信息
 * Created by zjf1650 on 07/07/2017.
 */
public enum JpfInterfaceErrorInfo {
/*
    INVALID_PARAMETER("参数无效"),
    NOT_ENOUGH("余额不足"),
    ORDER_CLOSED("订单已关闭"),
    OUT_ORDER_NO_USED("订单号重复"),
    ORDER_NOT_EXIST("订单不存在"),
    FLOW_REQNO_REPEAT("流水请求号重复"),
    FLOW_NOT_EXIST("下单流水不存在"),
    ORDER_PAID("订单已支付"),
    ORDER_PAYERROR("订单已支付失败"),
    ORDER_REVERSED("订单已撤销"),
    ORDER_STATUS_ERROR("订单状态异常"),
    FLOW_STATUS_ERROR("流水状态异常"),
    ORDER_PAY_REPEAT("订单重复支付"),
    ORDER_REFUND("订单已转入退款"),
    REVERSE_ERROR("撤销失败"),
    REFUND_ERROR("退款失败"),
    RECORD_REPEAT("记录重复"),
    RECORD_NOT_FOUND("记录未找到"),
    SIGN_ERROR("签名错误"),
    MCHNO_NOT_EXIST("商户号不存在"),
    NO_AUTH("无此接口权限"),
    RECORD_ALREADY_EXIST("记录已经存在"),
    STATUS_ERROR("状态异常"),

    PASSWORD_ERROR("密码错误"),

    ROUTE_ERROR("交易路由异常"),

    CHANNEL_ERROR("渠道服务异常"),

    DAL_ERROR("数据层操作异常"),
    SYSTEM_ERROR("系统异常"),*/

    SUCCESS("10000", "SUCCESS"),
    FAIL("10008", "失败"),
    NOTlOGIN("10007", "未登录"),
    MER_GETINFO_FAIL("10009", "商户信息获取失败"),
    SMS_VALI_FAIL("10005", "短信验证失败"),
    MER_CARD_NOTBIND("10006", "商户储蓄卡未绑定"),
    MER_SIGE_NOT("10010", "商户未签约"),
    MER_SIGE_YES("10011", "商户已签约"),
    INVALID_PARAMETER("10012", "参数无效"),
    DECRYPT_FAIL("10013", "TOKEN无效"),
    RECORD_NOT_EXIST("10014", "信息不存在"),
    ILLEGAL_PUBLICKEY("10015", "私钥错误"),
    INCORRECT_SIGN("10016","验签失败"),
    NO_SIGN("10017", "缺少签名参数"),
    ORDER_CLOSED("10018", "订单已关闭"),

    // 获取分期错误参数 10020开头
    NO_TERMS_CONFIGURATION("10020", "商户尚未配置银联信用卡分期支付信息"),

    // 创建订单错误参数 10030开头
    WRONG_TOTAL_PRICE("10030", "金额有误"),
    CREATE_ORDER_FAILED("10031", "订单生成失败"),
    RETURNURL_ENCODING_ERROR("10034", "returnUrl编码错误"),
    NOTIFYURL_ENCODING_ERROR("10036", "notifyUrl编码错误"),
    SIGNURL_ENCODING_ERROR("10035", "返回signUrl编码错误"),
    INCORRECT_PAYTYPE("10036", "payType值有误，请检查"),
    UNSUPPORT_PAYTYPE("10037", "商户无此支付方式"),

    // H5 第一步 获取商户支付方式等信息
    INCORRECT_DATA("10040","加密信息有误"),

    // H5 第二步 选择分期数点击确认付款
    ILLEGAL_TERM("10050", "非法的分期期数"),
    UNSUPPORT_TERM("10051", "此商户不支持该分期数"),
    UPDATE_ORDERNAME_FAILED("10052", "保存分期期数失败"),

    // H5 第三步 点击签约页的提交
    USER_NOT_SIGNED("10060", "用户未签约"),
    USER_SIGNED("10061", "用户已签约"),
    UPDATE_SIGN_ORDER_ERROR("10062", "更新签约信息失败"),
    SIGN_FAILED("10063", "签约接口返回失败");

    private final String desc;

    private final String code;


    JpfInterfaceErrorInfo(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return code;
    }
}
