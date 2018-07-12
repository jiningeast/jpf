package com.joiest.jpf.common.constant;

/**
 * 公共枚举常量
 * Created by zjf1650 on 09/07/2017.
 */
public abstract class EnumConstants {

    /**
     * 最近流水标识     0 是, 1 不是
     *
     */
    public enum LatestFlag {
        YES(0), NO(1);

        private final int value;

        LatestFlag(int value) {
            this.value = value;
        }

        public final int value() {
            return value;
        }

    }

    /**
     * 交易结果 发送交易通知时候使用
     */
    public enum TranResult {

        SUCCESS("S"),
        FAIL("F"),
        CANCELED("C");

        private final String value;

        TranResult(String value)
        {
            this.value = value;
        }

        public final String value() {
            return value;
        }
    }

    public enum UserStatus{
        normal("0"),forbid("1");

        private String value;

        UserStatus(String value) {
            this.value = value;
        }
        public final String value(){return value;}


    }

    public enum ProductStatus{
        normal((byte)1),forbid((byte)0);

        private Byte value;

        ProductStatus(Byte value) {
            this.value = value;
        }
        public final Byte value(){return value;}
    }

    /**
     * 充值状态（需求）
     */
    public enum RechargeStatus{
        APPLYING((byte)1,"申请中(需求提交，合同待审核first) "),
        AUDIT((byte)2,"已审核(已签约，待上传付款凭证)"),
        PAY((byte)3,"已支付(已上传凭证)"),
        RECHARGE_AND_TICKET_OPENING((byte)4,"已充值开票中(第二次审核)"),
        RECHARGE_AND_TICKET((byte)5,"已充值已开票"),
        DELIVERED((byte)6,"已发货"),
        COMPLETE((byte)7,"已完成(客户收到发票)"),
        CANCEL((byte)0,"已取消")
        ;

        private Byte value;

        private String desc;

        RechargeStatus(Byte value,String desc){
            this.value = value;
            this.desc = desc;
        }

        public final Byte value() {
            return value;
        }

        public final String desc() {
            return desc;
        }

    }

    /**
     * 充值-确认需求状态
     */
    public enum RechargePactStatus{
        UNCONFIRMED((byte)1,"默认，未确认需求"),
        CONFIRMED((byte)2,"已经确认需求")
        ;

        private Byte value;

        private String desc;

        RechargePactStatus(Byte value,String desc){
            this.value = value;
            this.desc = desc;
        }

        public final Byte value() {
            return value;
        }

        public final String desc() {
            return desc;
        }

    }



}
