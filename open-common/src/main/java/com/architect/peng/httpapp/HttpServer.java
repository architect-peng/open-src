package com.architect.peng.httpapp;

/**
 * @author shangpeng
 * @version V1.0
 * @Date 2020/8/21 16:44
 */

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.squareup.okhttp.*;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpServer {

    public static HttpClient createHttpClient(int maxTotal, int maxPerRoute, int socketTimeout, int connectTimeout,
                                              int connectionRequestTimeout) {
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(maxTotal);
        cm.setDefaultMaxPerRoute(maxPerRoute);
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(defaultRequestConfig).build();
        return httpClient;
    }

    /**
     * 发送post请求
     *
     * @param url      请求地址
     * @param params   请求参数
     * @param encoding 编码
     */
    public static String sendPost(HttpClient httpClient, String url, Map<String, String> params, String cookie,
                                  Charset encoding) {
        String resp = "";
        HttpPost httpPost = new HttpPost(url);
        if (params != null && params.size() > 0) {
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            Iterator<Map.Entry<String, String>> itr = params.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<String, String> entry = itr.next();
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(formParams, encoding);
            httpPost.setEntity(postEntity);
        }

        httpPost.setHeader("Cookie", cookie);
        CloseableHttpResponse response = null;
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpPost);
            resp = EntityUtils.toString(response.getEntity(), encoding);

            // String setCookie = response.getFirstHeader("Set-Cookie").getValue();
            // System.out.println("setCookie=" + setCookie);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    // log
                    e.printStackTrace();
                }
            }
        }
        return resp;
    }


    public static void main(String[] args) {

        HttpClient httpClient = HttpServer.createHttpClient(10, 10, 4000, 4000, 4000);
        //请求cookie,为了与验证码绑定（注意：分号要是英文格式）
        String cookie = "cust_type=2;JSESSIONID=1042CD46231F265585CE3D8D105741CD;_gscu_1827457641=573266013u4vos16";
//验证码
        String code = "1674";

        String url = "http://www.hzgjj.gov.cn:8080/WebAccounts/userLogin.do";
        Map<String, String> params = new HashMap<>();
        params.put("cust_no", "用户名");
        params.put("password", "密码");
        params.put("validate_code", code);
        params.put("user_type", "3");
        params.put("cust_type", "2");

        String result = HttpServer.sendPost(httpClient, url, params, cookie, null);
    }
}
