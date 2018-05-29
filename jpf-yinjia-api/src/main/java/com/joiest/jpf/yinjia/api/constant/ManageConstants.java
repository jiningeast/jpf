package com.joiest.jpf.yinjia.api.constant;

public class ManageConstants {

    // 银联接口请求地址
    public static final String CHINAPAY_URL_REQUEST = "http://vip2.7shengqian.com/trade/install/";

    // 银联支付异步回调地址
    public static final String CHINAPAY_PAYBACKURL = "http://testapi.7shengqian.com/index.php?r=YinjiaStage/ChinaPayReturn";

    // 银联签约成功后前台跳转地址
    public static final String CHINAPAY_SIGN_RETURN_URL = "https://tcpchats.7shengqian.com/paycode/";

    // 银联签约后台通知地址
    public static final String CHINAPAY_SIGN_BACK_URL = "http://testapi.7shengqian.com/yinjia-api/yinjiastage/signNotify";

    // 银联接口查询接口
    public static final String CHINAPAY_URL_SELECT = "http://vip2.7shengqian.com/trade/api/";

    // 银联退款异步回调地址
    public static final String CHINAPAY_REFUND_BACK_URL = "https://testcpapi.7shengqian.com/yinjia-api/yinjiastage/purchaseRefundReturn";

    // H5首页地址（选择分期页）
    public static final String TERMS_URL = "https://tcpchats.7shengqian.com/instalment/";

    // AES加密key
    public static final String AES_KEY = "tioB8c6esX1Cx84Y16NFcFascZQZXiGI";

    // 支持银行列表
    public static final String SUPPORTED_BANKNAMES = "[{\"bankName\":\"农业银行\"},{\"bankName\":\"中国银行\"},{\"bankName\":\"邮储银行\"},{\"bankName\":\"中信银行\"},{\"bankName\":\"光大银行\"},{\"bankName\":\"华夏银行\"},{\"bankName\":\"民生银行\"},{\"bankName\":\"浦发银行\"},{\"bankName\":\"上海银行\"},{\"bankName\":\"江苏银行\"},{\"bankName\":\"北京农商\"}]";


}
