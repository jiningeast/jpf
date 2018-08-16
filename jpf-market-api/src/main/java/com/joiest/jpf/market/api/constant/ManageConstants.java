package com.joiest.jpf.market.api.constant;

import java.util.HashMap;
import java.util.Map;

public class ManageConstants {

    public static final Map<String, String> USER_OPERATE_STATUS = new HashMap<String, String>() {
        {
            put("0", "订单生成,未选分期");
            put("1", "订单生成,已选分期,待签约");
            put("2", "签约通知成功,待返回签约成功");
            put("3", "签约通知失败");
            put("4", "签约返回成功,待获取支付短信");
            put("5", "签约返回失败");
            put("6", "用户曾已签约,待获取支付短信");
            put("7", "短信发送成功,待支付");
            put("8", "短信发送失败");
            put("9", "支付通知成功,待返回成功");
            put("10", "支付通知失败");
            put("11", "支付返回成功,支付结束");
            put("12", "支付返回失败");
        }
    };
    public static final Map<String, String> rechargeStatusCn_map = new HashMap<String, String>(){
        {
            put("0","充值中");
            put("1","充值成功");
            put("9","撤消(充值失败)");
        }
    };

}
