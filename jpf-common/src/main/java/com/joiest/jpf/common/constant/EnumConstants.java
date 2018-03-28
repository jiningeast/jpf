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
}
