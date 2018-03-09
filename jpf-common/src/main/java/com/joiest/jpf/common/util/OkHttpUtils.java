package com.joiest.jpf.common.util;


import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.net.ssl.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zjf1650 on 31/07/2017.
 */
public class OkHttpUtils {

    private static final Logger logger = LogManager.getLogger(OkHttpUtils.class);

    public static final String UTF_8 = "UTF-8";

    private static final OkHttpClient okHttpClient;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);
        X509TrustManager trustAllCerts = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        SSLSocketFactory sslSocketFactory = createSSLSocketFactory(trustAllCerts);
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts);
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        okHttpClient = builder.build();
    }


    private static SSLSocketFactory createSSLSocketFactory(X509TrustManager x509TrustManager) {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ssfFactory;
    }

    public static String get(String url) {
        Request request = new Request.Builder()
                .url(url).build();
        String text = null;
        try {
            logger.info("get提交, 请求url:{}", url);
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                new JpfException(JpfErrorInfo.CHANNEL_ERROR, "远程响应异常");
            }
            text = response.body().string();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            new JpfException(JpfErrorInfo.CHANNEL_ERROR, "通讯异常");
        }
        logger.info("get提交, 请求url:{}, 返回数据:{}", url, text);
        return text;
    }


    public static String postJson(String url, String json) {
        return postJson(url, UTF_8, json);
    }

    public static String postJson(String url, String charset, String json) {
        MediaType mediaType
                = MediaType.parse("application/json; charset="+charset);
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(mediaType, json))
                .build();
        String text = null;
        try {
            logger.info("post提交json, 请求url:{}, 请求数据:{}", url, json);
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                new JpfException(JpfErrorInfo.CHANNEL_ERROR, "远程响应异常");
            }
            text = response.body().string();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            new JpfException(JpfErrorInfo.CHANNEL_ERROR, "通讯异常");
        }
        logger.info("post提交json, 请求url:{}, 返回数据:{}", url, text);
        return text;
    }

    public static String postForm(String url, Map<String, Object> parmsMap) {
        return postForm(url, UTF_8, false, parmsMap);
    }

    public static String postForm(String url, String charset, Map<String, Object> parmsMap) {
        return postForm(url, charset, false, parmsMap);
    }

    public static String postForm(String url, String charset, boolean isEncoded, Map<String, Object> parmsMap) {
        String content = getContent(charset, isEncoded, parmsMap);
        logger.info("提交form表单, 请求url:{}, 请求数据:{}", url, content);
        MediaType mediaType
                = MediaType.parse("application/x-www-form-urlencoded; charset="+charset);
        RequestBody formBody = FormBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        String text = null;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                new JpfException(JpfErrorInfo.CHANNEL_ERROR, "远程响应异常");
            }
            text = response.body().string();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            new JpfException(JpfErrorInfo.CHANNEL_ERROR, "通讯异常");
        }
        logger.info("提交form表单, 请求url:{}, 返回数据:{}", url, text);
        return text;
    }

    private static String getContent( String charset, boolean isEncoded, Map<String, Object> parmsMap) {
        StringBuilder sb = new StringBuilder();
        String key = null;
        String value = null;
        int pos = 0;
        try {
            for (Map.Entry<String, Object> entry : parmsMap.entrySet()) {
                key = entry.getKey();
                value = entry.getValue() == null ? "" : entry.getValue().toString();
                if (pos > 0) {
                    sb.append("&");
                }
                if(isEncoded){
                    sb.append(key).append("=").append(URLEncoder.encode(value, charset));
                }else{
                    sb.append(key).append("=").append(value);
                }
                pos++;

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("a", "aa");
        m.put("b", "bb");
        m.put("c", "cc");
        m.put("d", "dd");
        m.put("e", "ee");
 //       System.out.println(getContent(m));
//        Request request = new Request.Builder()
//                .url("http://192.168.102.7:8082/shutdown")
//                .post(RequestBody.create(MEDIA_TYPE_JSON, ""))
//                .build();
//        OkHttpUtils.okHttpClient.newCall(request);
        FormBody formBody = new FormBody.Builder()
                .add("", "")
                .addEncoded("", "")
                .build();
        System.out.println(OkHttpUtils.postJson("http://192.168.102.7:8082/shutdown", ""));
    }
}

