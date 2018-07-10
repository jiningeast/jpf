package com.joiest.jpf.cloud.api.util;

import com.joiest.jpf.common.util.MessageDigestUtil;
import com.joiest.jpf.common.util.SignUtils;
import com.joiest.jpf.dto.ToolCateResponse;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
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
import java.security.cert.X509Certificate;
import java.util.*;

public class AliYunUtils {

    /*
     * 获取参数的json对象
     */
    public static JSONObject getParam(int type, String dataValue) {

        JSONObject obj = new JSONObject();
        try {
            obj.put("dataType", type);
            obj.put("dataValue", dataValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    /**
     * 流信息获取
     * **/
    public static ToolCateResponse convert(HttpResponse response)  throws IOException {

        ToolCateResponse res = new ToolCateResponse();
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
    /**
     * 将流转换为字符串
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
     * 身份证、姓名实名认证 Post String
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

}

