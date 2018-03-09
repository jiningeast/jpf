package com.joiest.jpf.common.exception;

/**
 * 错误码信息
 * Created by zjf1650 on 07/07/2017.
 */
public enum JpfErrorInfo {

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

    ROUTE_ERROR("交易路由异常"),

    CHANNEL_ERROR("渠道服务异常"),

    DAL_ERROR("数据层操作异常"),
    SYSTEM_ERROR("系统异常");

    private final String desc;

    private JpfErrorInfo(String desc) {
        this.desc= desc;
    }

    public String desc() {
        return desc;
    }
}
