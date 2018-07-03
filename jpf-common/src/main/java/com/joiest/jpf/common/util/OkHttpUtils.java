package com.joiest.jpf.common.util;


import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.HttpClient;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.http.client.methods.HttpPost;


import javax.net.ssl.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
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

    /**
     * 身份证、姓名实名认证 Post String
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */

    /**
     * post form
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param bodys
     * @return
     * @throws Exception
     */
    public static HttpResponse httpPostIdenAuth(String host, String path, String method,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      Map<String, String> bodys)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPost request = new HttpPost(initUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (bodys != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

            for (String key : bodys.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
            formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            request.setEntity(formEntity);
        }

        return httpClient.execute(request);
    }
    /*public static HttpResponse httpPostIdenAuth(String host, String path, String method,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      String body)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPost request = new HttpPost(initUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (StringUtils.isNotBlank(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }

        return httpClient.execute(request);
    }*/

    /**
     * OCR Http POST 字符串
     * @param host
     * @param path
     * @param connectTimeout
     * @param headers
     * @param querys
     * @param body
     * @param signHeaderPrefixList
     * @param appKey
     * @param appSecret
     * @return
     * @throws Exception
     */
    public static HttpResponse httpPostOcr(String host, String path, int connectTimeout, Map<String, String> headers, Map<String, String> querys, String body, List<String> signHeaderPrefixList, String appKey, String appSecret)
            throws Exception {

        headers = initialBasicHeader("POST", path, headers, querys, null, signHeaderPrefixList, appKey, appSecret);

        HttpClient httpClient = wrapClient(host);
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectTimeout);

        HttpPost post = new HttpPost(initUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            post.addHeader(e.getKey(), MessageDigestUtil.utf8ToIso88591(e.getValue()));
        }
        if (StringUtils.isNotBlank(body)) {

            post.setEntity(new StringEntity(body, "UTF-8"));
        }
        HttpResponse httpResponse=  httpClient.execute(post);
        return httpResponse;
    }
    private static HttpClient wrapClient(String host) {

        HttpClient httpClient = new DefaultHttpClient();

        if (host.startsWith("https://")) {
            sslClient(httpClient);
        }
        return httpClient;
    }

    private static void sslClient(HttpClient httpClient) {

        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] xcs, String str) {

                }
                public void checkServerTrusted(X509Certificate[] xcs, String str) {

                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            org.apache.http.conn.ssl.SSLSocketFactory ssf = new org.apache.http.conn.ssl.SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = httpClient.getConnectionManager();
            SchemeRegistry registry = ccm.getSchemeRegistry();
            registry.register(new Scheme("https", ssf,443));
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * OCR 初始化地址
     * */
    private static String initUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (!StringUtils.isBlank(path)) {
            sbUrl.append(path);
        }
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StringUtils.isBlank(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StringUtils.isBlank(query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode(query.getValue(), "UTF-8"));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }

        return sbUrl.toString();
    }
    /**
     * OCR 初始化基础Header
     * @param method
     * @param path
     * @param headers
     * @param querys
     * @param bodys
     * @param signHeaderPrefixList
     * @param appKey
     * @param appSecret
     * @return
     * @throws MalformedURLException
     */
    private static Map<String, String> initialBasicHeader(String method, String path,
                                                          Map<String, String> headers,
                                                          Map<String, String> querys,
                                                          Map<String, String> bodys,
                                                          List<String> signHeaderPrefixList,
                                                          String appKey, String appSecret)
            throws MalformedURLException {
        if (headers == null) {
            headers = new HashMap<String, String>();
        }
        headers.put("X-Ca-Timestamp", String.valueOf(new Date().getTime()));
        //headers.put(SystemHeader.X_CA_NONCE, UUID.randomUUID().toString());
        headers.put("X-Ca-Key", appKey);
        headers.put("X-Ca-Signature", SignUtils.OcrSign(appSecret, method, path, headers, querys, bodys, signHeaderPrefixList));

        return headers;
    }


    /**
     * 梦网短信发送 使用post请求
     * @param obj  请求参数对象
     * @param httpUrl  请求URL地址
     * @return 请求网关的返回值
     * @throws Exception
     */
    public static String executePost(Object obj, String httpUrl) throws Exception {

        String result = "";
        Class cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        //设置请求参数
        String fieldName = null;
        String fieldNameUpper = null;
        java.lang.reflect.Method getMethod = null;
        String value = null;
        for (int i = 0; i < fields.length; i++)   {//循环设置请求参数
            fieldName = fields[i].getName();
            fieldNameUpper = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            getMethod = cls.getMethod("get" + fieldNameUpper);//通过反射获取get方法
            value = (String) getMethod.invoke(obj);//通过反射调用get方法
            if(value != null) {//请求参数值不为空，才设置
                params.add(new BasicNameValuePair(fieldName, value));
            }
        }
        HttpPost httppost = new HttpPost(httpUrl);//设置HttpPost
        httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8)); // 设置参数的编码UTF-8
        HttpClient httpclient = new DefaultHttpClient();//创建HttpClient
        HttpEntity entity = httpclient.execute(httppost).getEntity();//Http请求网关
        if(entity != null&& entity.getContentLength() >0) {//返回值不为空，且长度大于0
            result= EntityUtils.toString(entity);//将返回值转换成字符串
        }//处理返回结果
        httpclient.getConnectionManager().shutdown();//关闭连接

        return result;//返回返回值
    }

}

