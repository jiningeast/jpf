package com.joiest.jpf.common.util;


import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import okhttp3.*;
import okhttp3.internal.http.HttpMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.HttpClient;

import org.apache.http.params.CoreConnectionPNames;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import sun.net.www.http.HttpClient;
import org.apache.http.client.methods.HttpPost;


import javax.net.ssl.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.bouncycastle.asn1.misc.NetscapeCertType.sslClient;

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
     * Http POST 字符串
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

    /*private static Response convert(HttpResponse response) throws IOException {

        Response res = new Response();

        if (null != response) {
            res.setStatusCode(response.getStatusLine().getStatusCode());
            for (Header header : response.getAllHeaders()) {
                res.setHeader(header.getName(), MessageDigestUtil.iso88591ToUtf8(header.getValue()));
            }

            res.setContentType(res.getHeader("Content-Type"));
            res.setRequestId(res.getHeader("X-Ca-Request-Id"));
            res.setErrorMessage(res.getHeader("X-Ca-Error-Message"));
            res.setBody(readStreamAsStr(response.getEntity().getContent()));

        } else {
            //服务器无回应
            res.setStatusCode(500);
            res.setErrorMessage("No Response");
        }

        return res;
    }


    */
    /**
     * 将流转换为字符串
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String readStreamAsStr(InputStream is) throws IOException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        WritableByteChannel dest = Channels.newChannel(bos);
        ReadableByteChannel src = Channels.newChannel(is);
        ByteBuffer bb = ByteBuffer.allocate(4096);

        while (src.read(bb) != -1) {
            bb.flip();
            dest.write(bb);
            bb.clear();
        }
        src.close();
        dest.close();
        return new String(bos.toByteArray(), "UTF-8");
    }
    /**
     * 初始化地址
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
}

