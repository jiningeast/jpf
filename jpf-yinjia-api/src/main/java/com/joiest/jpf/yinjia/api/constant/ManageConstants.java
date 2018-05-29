package com.joiest.jpf.yinjia.api.constant;

public class ManageConstants {

    public static final String USERINFO_SESSION = "manage-userInfo";

    public static final String SKEY = "&*^&%^***()(";

    public static final String REFUND_URL_TEST = "http://testapi.7shengqian.com/index.php?r=YinjiaStage/PurchaseRefund";

    public static final String REFUND_URL_FORMAL = "https://api.7shengqian.com/index.php?r=YinjiaStage/PurchaseRefund";

    public static final String CANCEL_URL_TEST = "http://testapi.7shengqian.com/index.php?r=YinjiaStage/PurchaseCancel";

    public static final String CANCEL_URL_FORMAL = "https://api.7shengqian.com/index.php?r=YinjiaStage/PurchaseCancel";

    public static final String CHINAPAY_PAYBACKURL = "http://testapi.7shengqian.com/index.php?r=YinjiaStage/ChinaPayReturn";

    public static final String SIGN_URL_TEST = "http://testwebchats.7shengqian.com/?#/travel/userinfo/7PqHEZAq7CEG/6115271574692993";

    //银联接口请求测试地址
    public static final String CHINAPAY_URL_REQUEST_TEST = "http://vip2.7shengqian.com/trade/install/";
    public static final String CHINAPAY_URL_SELECT_TEST = "http://vip2.7shengqian.com/trade/api/";

    //银联接口请求地址
    public static final String CHINAPAY_URL_REQUEST = "http://vip2.7shengqian.com/trade/install/";
    public static final String CHINAPAY_URL_SELECT = "http://vip2.7shengqian.com/trade/api/";

    public static final String SIGN_URL_FORMAL = "http://webchats.7shengqian.com/?#/travel/userinfo/";

    public static final String TERMS_URL = "http://localhost:8080/#/HelloWorld/";

    // AES加密key
    public static final String AES_KEY = "tioB8c6esX1Cx84Y16NFcFascZQZXiGI";

    //支付回调url---测试
	public static final String ChinaPay_PayBackUrl = "http://testapi.7shengqian.com/index.php?r=YinjiaStage/ChinaPayReturn";


    // 银联签约成功后前台跳转地址
    public static final String CHINAPAY_SIGN_RETURN_URL = "http://localhost:8080/paycode/";

    // 银联签约后台通知地址
    public static final String CHINAPAY_SIGN_BACK_URL = "http://testapi.7shengqian.com/yinjia-api/yinjiastage/signNotify";

    // 银联签约地址
    public static final String ChinaPay_Rurl = "http://vip2.7shengqian.com/trade/install/";

    // 银联撤销交易后台回调地址
    public static  final String ChinaPay_PurCanBackUrl = "http://testapi.7shengqian.com/index.php?r=YinjiaStage/PurchaseCancelReturn";

    // 支持银行列表
    public static final String SUPPORTED_BANKNAMES = "[{\"bankName\":\"农业银行\"},{\"bankName\":\"中国银行\"},{\"bankName\":\"邮储银行\"},{\"bankName\":\"中信银行\"},{\"bankName\":\"光大银行\"},{\"bankName\":\"华夏银行\"},{\"bankName\":\"民生银行\"},{\"bankName\":\"浦发银行\"},{\"bankName\":\"上海银行\"},{\"bankName\":\"江苏银行\"},{\"bankName\":\"北京农商\"}]";

    //银联退款回调地址
    public static final String CHINAPAY_REFUND_BACK_URL = "http://testapi.7shengqian.com/index.php?r=YinjiaStage/PurchaseCancelReturn";//"http://localhost:8080/yinjia-api/yinjiastage/purchaseRefundReturn";

}
