package org.jumao.spider.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jumao.spider.beans.UserTraceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class HttpUtil {

    public static String          HttpMethodGet  = "GET";
    public static String          HttpMethodPost = "POST";
    static HttpClient             httpClient     = new DefaultHttpClient();

    protected static final Logger logger         = LoggerFactory.getLogger(HttpUtil.class);

    @SuppressWarnings("resource")
    public static JSONObject post(String url, JSONObject params) {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            String value = params.getString(key);
            try {
                value = URLEncoder.encode(value, "utf8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            sb.append(key + "=" + value).append("&");
        }
        url = url + "?" + sb;
        HttpPost method = new HttpPost(url);
        JSONObject jobj = new JSONObject();
        try {
            // 建立一个NameValuePair数组，用于存储欲传送的参数
            method.addHeader("Content-type", "application/json; charset=utf-8");
            method.setHeader("Accept", "application/json");
            // method.setEntity(new StringEntity(sb.toString(),
            // Charset.forName("UTF-8")));

            HttpResponse response = httpClient.execute(method);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                jobj.put("success", true);
            } else {
                jobj.put("success", false);
            }

            // Read the response body
            String body = EntityUtils.toString(response.getEntity());
            jobj.put("data", body);
        } catch (IOException ex) {
            logger.warn("url = " + url, ex);
            jobj.put("success", false);
        }
        return jobj;
    }

    @SuppressWarnings("resource")
    public static JSONObject doPost(String url, JSONObject params) {

        HttpPost method = new HttpPost(url);
        JSONObject jobj = new JSONObject();
        try {
            // 建立一个NameValuePair数组，用于存储欲传送的参数
            method.addHeader("Content-type", "application/json; charset=utf-8");
            method.setHeader("Accept", "application/json");
            method.setEntity(new StringEntity(params.toString(), Charset.forName("UTF-8")));

            HttpResponse response = httpClient.execute(method);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                jobj.put("success", true);
            } else {
                jobj.put("success", false);
            }

            // Read the response body
            String body = EntityUtils.toString(response.getEntity());
            jobj.put("data", body);
        } catch (IOException ex) {
            logger.warn("url = " + url, ex);
            jobj.put("success", false);
        }
        return jobj;
    }

    @SuppressWarnings("resource")
    public static JSONObject postForm(String url, JSONObject params) {
        // StringBuilder sb = new StringBuilder();
        // for(String key : params.keySet()){
        // sb.append(key+"="+params.getString(key)).append("&");
        // }
        // url = url+"?"+sb;
        HttpPost method = new HttpPost(url);
        JSONObject jobj = new JSONObject();
        try {
            // 建立一个NameValuePair数组，用于存储欲传送的参数
            method.addHeader("Content-type", "application/json; charset=utf-8");
            method.setHeader("Accept", "application/json");

            List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
            for (String key : params.keySet()) {
                pairList.add(new BasicNameValuePair(key, params.getString(key)));
            }
            method.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));

            HttpResponse response = httpClient.execute(method);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                jobj.put("success", true);
            } else {
                jobj.put("success", false);
            }

            // Read the response body
            String body = EntityUtils.toString(response.getEntity());
            jobj.put("data", body);
        } catch (IOException e) {
            e.printStackTrace();
            jobj.put("success", false);
        }
        return jobj;
    }

    public static void main(String[] args) {
        // JSONObject param = new JSONObject();
        // param.put("args", "warningtest");
        // param.put("mobile", "150***66");
        // param.put("tplCode", "jumoreshuju-warning");
        // post("http://www.jlt.com/app/smsTemplate/doSendSms", param);

        JSONObject param = new JSONObject();
        // param.put("bizCode", "1024");
        // param.put("loginTime", System.currentTimeMillis() + "");
        // param.put("userAgent", "test");
        List<UserTraceInfo> list = new ArrayList<UserTraceInfo>();
        UserTraceInfo info = new UserTraceInfo();
        info.setBizCode("1014");
        info.setLoginTime(System.currentTimeMillis() + "");
        //info.setUserAgent("test");
        list.add(info);
        UserTraceInfo info2 = new UserTraceInfo();
        info2.setBizCode("1016");
        info2.setLoginTime(System.currentTimeMillis() + "");
        //info2.setUserAgent("test2");
        list.add(info2);
        param.put("list", list);
        doPost("http://192.168.23.237:8080/biservice/v1/usertrace", param);
    }
}
