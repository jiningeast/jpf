package com.joiest.jpf.common.exception;

/**
 * 错误码信息
 * Created by zjf1650 on 07/07/2017.
 */
public enum JpfInterfaceErrorInfo {

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
    USER_COUPON_NOTBIND("10019", "用户未激活券"),

    //商户信息验证失败
    MERCH_UNAVAILABLE("10071", "商户未审核"),
    MERCH_FREEZEUP("10072", "商户被冻结"),

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
    MERCH_RATE_ERROR("10038", "商户费率信息错误"),

    // H5 第一步 获取商户支付方式等信息
    INCORRECT_DATA("10040","加密信息有误"),

    // H5 第二步 选择分期数点击确认付款
    ILLEGAL_TERM("10050", "非法的分期期数"),
    UNSUPPORT_TERM("10051", "此商户不支持该分期数"),
    UPDATE_ORDERNAME_FAILED("10052", "保存分期期数失败"),
    STAGE_RATE_ERROR("10053","尚未配置分期费率"),

    // H5 第三步 点击签约页的提交
    USER_NOT_SIGNED("10060", "用户未签约"),
    USER_SIGNED("10061", "用户已签约"),
    UPDATE_SIGN_ORDER_ERROR("10062", "更新签约信息失败"),
    SIGN_FAILED("10063", "签约接口返回失败"),

    //云帐户
    //个人相关
    USERINFO_VALID_FAIL("20001", "个人信息校验失败"),
    USERINFO_NOT_EXIST("20002", "个人信息不存在"),
    USERINFO_DATE_ERROR("20003","日期格式错误"),

    //云帐户
    //企业相关
    COMPANYINFO_VALID_FAIL("40001", "企业信息校验失败"),
    COMPANYINFO_NOT_EXIST("40002", "企业信息不存在"),

    //代付接口
    DF_SIGN_ERROR("30001","签名错误"),
    DF_BATCHNO_NOTEMPTY("30002","批次号不能为空"),
    DF_INFOLIST_EMPTY("30003","待打款信息列表为空"),
    DF_LISTFILTER_EMPTY("30004","待打款列表过滤后为空"),

    //云市场 begin
    //下单
    MK_PRODUCT_NOFOUND("50001", "商品不存在"),
    MK_ORDERMONEY_DIFF("50002", "商品金额不一致"),
    MK_ORDER_NOT_EXIST("50003", "订单不存在"),
    CURR_DOU_TOTAL_ZERO("50004", "无可用欣豆"),
    USER_DOU_CODE_ERROR("50005", "当前余额异常"),
    GOODLIST_IS_EMPTY("50006", "无对应商品信息"),
    BRANDLIST_IS_EMPTY("50007", "无品牌信息"),
    USER_IS_FREEZE("50008", "您的帐户已被冻结"),
    USER_DOU_NOT_SUFFICIENT("50009", "帐户余额不足"),
    ADD_STREAM_ERROR("50010", "添加流水失败");

    private final String desc;

    private final String code;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public void clearData() {
        this.data = null;
    }

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
