package com.joiest.jpf.manage.web.constant;

import java.util.HashMap;
import java.util.Map;

public class ManageConstants {

    public static final String USERINFO_SESSION = "manage-userInfo";

    public static final String SKEY = "&*^&%^***()(";

    public static final String REFUND_URL_TEST = "http://testapi.7shengqian.com/index.php?r=YinjiaStage/PurchaseRefund";

    public static final String REFUND_URL_FORMAL = "https://api.7shengqian.com/index.php?r=YinjiaStage/PurchaseRefund";

    public static final String CANCEL_URL_TEST = "http://testapi.7shengqian.com/index.php?r=YinjiaStage/PurchaseCancel";

    public static final String CANCEL_URL_FORMAL = "https://api.7shengqian.com/index.php?r=YinjiaStage/PurchaseCancel";

    public static final String CHINAPAY_PAYBACKURL = "http://testapi.7shengqian.com/index.php?r=YinjiaStage/ChinaPayReturn";

    //银联接口请求测试地址
    public static final String CHINAPAY_URL_REQUEST_TEST = "http://vip2.7shengqian.com/trade/install/";
    public static final String CHINAPAY_URL_SELECT_TEST = "http://vip2.7shengqian.com/trade/api/";

    //银联接口请求正式地址
    public static final String CHINAPAY_URL_REQUEST = "http://vip2.7shengqian.com/trade/install/";
    public static final String CHINAPAY_URL_SELECT = "http://vip2.7shengqian.com/trade/api/";

    //后台商户认证发送短信
    public static final String SEND_SMS_URL =  "https://api.7shengqian.com/index.php?r=Sms/YinjiaStageSend";

    public static final Map<String,String> USER_OPERATE_STATUS = new HashMap<String,String>(){
        {
            put("0","订单生成,未选分期");
            put("1","订单生成,已选分期,待签约");
            put("2","签约通知成功,待返回签约成功");
            put("3","签约通知失败");
            put("4","签约返回成功,待获取支付短信");
            put("5","签约返回失败");
            put("6","用户曾已签约,待获取支付短信");
            put("7","短信发送成功,待支付");
            put("8","短信发送失败");
            put("9","支付通知成功,待返回成功");
            put("10","支付通知失败");
            put("11","支付返回成功,支付结束");
            put("12","支付返回失败");
        }
    };

    public static final Map<String,String> REFUND_STATUS = new HashMap<String, String>(){
        {
            put("1","未申请退款");
            put("2","退款通知成功,待接收异步返回信息");
            put("3","退款通知失败");
            put("4","异步返回成功,退款成功");
            put("5","异步返回失败，退款失败");
        }
    };

    public static final Map<String,String> PAY_DETAIL = new HashMap<String,String>(){
        {
            put("stageType_cn", "分期期数：");
            put("payType_cn", "支付方式：");
        }
    };
}
