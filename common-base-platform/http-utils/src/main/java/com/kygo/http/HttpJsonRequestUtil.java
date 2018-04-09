package com.kygo.http;

import com.kygo.http.exception.HttpConnectHostException;
import com.kygo.http.exception.HttpRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * http 请求 （参数通过json的方式发送,返回的json字符串）
 * @author Keith Wang (王 杰)
 * @email wangjie01vcredit.com
 * @date 2017年11月16日
 * @version 1.0
 */
public class HttpJsonRequestUtil {

    private static Logger logger = LoggerFactory.getLogger("HttpRequestUtil");
    
    private static final String HTTPS = "https";
    private static final String UTF8 = "UTF-8";
    
    private static final int DEFAULT_SUBSTRING_MAX_LENGTH = 2000;
    
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    public static String doPost(final String url, final byte[] content, final Map<String, String> headers) throws HttpRequestException, HttpConnectHostException{
    	CloseableHttpClient client = null;
        try {
            if (startsWith(url, HTTPS)) {
                client = createSSLInsecureClient();
            } else {
                client = HttpClients.createDefault();
            }

            HttpPost post = new HttpPost(url);

            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }

            HttpEntity paramEntity = new ByteArrayEntity(content);
            post.setEntity(paramEntity);
            CloseableHttpResponse response = client.execute(post);
            try {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    return EntityUtils.toString(response.getEntity(), UTF8);
                }else{
                	logger.debug("call http doPost url = " + url);
                	logger.debug("return statusCode = {}", response.getStatusLine().getStatusCode());
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
        	logger.error("HttpRequestUtil.doPost: {}, url = {}", e.getMessage() , url);
            if (e instanceof HttpHostConnectException || e.getCause() instanceof ConnectException) {
                throw new HttpConnectHostException(url, e.getMessage());
            }
            throw new HttpRequestException(url, e.getMessage());
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {
                }
            }
        }
        return "";
    }
    
    /**
     * 发送post请求
     * @param url (请求的URL)
     * @param param (请求的参数)
     * @param isParamNeedBase64 (参数是否需要base64编码)
     * @return
     * @throws HttpConnectHostException 
     */
    public static String doPost(final String url, final Object param, boolean isParamNeedBase64, final Map<String, String> headers) throws HttpConnectHostException, HttpRequestException{
    	if(param == null){
    		return doPost(url, "", headers);
    	}
    	String json;
		try {
			json = objectMapper.writeValueAsString(param);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
			logger.error("call doPost {},{}, hint JsonProcessingException :{}", new Object[]{url, param.toString(), e1.toString()});
			throw new HttpRequestException(url, e1.getMessage());
		}
    	if(isParamNeedBase64){
    		String base64Content;
			try {
				base64Content = Base64.encodeBase64String(json.getBytes("utf-8"));
				return doPost(url, base64Content, headers);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error("call doPost , hint UnsupportedEncodingException : ", new Object[]{url, param.toString(), e.toString()});
				throw new HttpRequestException(url, e.getMessage());
			}
    	}
    	return doPost(url, json, headers);
    }

    public static String doPost(final String url, final Object param, boolean isParamNeedBase64) throws HttpConnectHostException, HttpRequestException{
        return doPost(url, param, isParamNeedBase64, null);
    }

    public static String doPost(final String url, final Map<String, String> param) throws HttpConnectHostException, HttpRequestException{
    	return doPost(url, param,null);
    }
    
    public static String doPost(final String url, final Object param) throws HttpConnectHostException, HttpRequestException{
    	return doPost(url, param,null);
    }

    public static String doPost(final String url, final Map<String, String> param, final Map<String, String> headers) throws HttpConnectHostException, HttpRequestException{
        return doPost(url, param, false, headers);
    }

    public static String doPost(final String url, final Object param, final Map<String, String> headers) throws HttpConnectHostException, HttpRequestException{
        return doPost(url, param, false, headers);
    }

    public static String doPost(String url, String json) throws HttpConnectHostException , HttpRequestException{
        return doPost(url, json, null);
    }

    public static String doPost(String url, String json, final Map<String, String> headers) throws HttpConnectHostException , HttpRequestException{
        CloseableHttpClient client = null;
        try {
            if (startsWith(url, HTTPS)) {
                client = createSSLInsecureClient();
            } else {
                client = HttpClients.createDefault();
            }

            HttpPost post = new HttpPost(url);

            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }

            if(json != null && !"".equals(json)){
            	StringEntity s = new StringEntity(json, ContentType.APPLICATION_JSON);
                //s.setContentEncoding(UTF8);
                //s.setContentType("application/json");
                post.setEntity(s);
            }
            CloseableHttpResponse response = client.execute(post);
            try {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                	String result = EntityUtils.toString(response.getEntity(), UTF8);
                	String paramterOfLimitLength =substring(json);
                	logger.debug("call http doPost url = " + url);
                	logger.debug("request paramter = " + paramterOfLimitLength);
                	logger.debug("return = " + result);
                    return result;
                }else{
                	logger.debug("call http doPost url = " + url);
                	int statusCode = response.getStatusLine().getStatusCode();
                	logger.debug("return statusCode = {}", statusCode);
                	throw new HttpRequestException(url, "请求异常返回：statusCode = "+statusCode);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
        	logger.error("call http doPost url = " + url);
        	logger.error("request paramter = " + json);
        	logger.error("hit error = " + e.getMessage());
        	if (e instanceof HttpHostConnectException || e.getCause() instanceof ConnectException) {
                throw new HttpConnectHostException(url ,e.getMessage());
            }
            throw new HttpRequestException(url, e.getMessage());
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public static String doGet(String url) throws HttpConnectHostException {
        return doGet(url, null);
    }

	public static String doGet(String url, Map<String, String> headers) throws HttpConnectHostException, HttpRequestException {
        CloseableHttpClient client = null;
        try {
            if (startsWith(url, HTTPS)) {
                client = createSSLInsecureClient();
            } else {
                client = HttpClients.createDefault();
            }
            HttpGet httpget = new HttpGet(url);

            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpget.addHeader(entry.getKey(), entry.getValue());
                }
            }

            CloseableHttpResponse response = client.execute(httpget);
            try {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                	String result = EntityUtils.toString(response.getEntity(), UTF8);
                	logger.debug("call http doGet url =" + url);
                	logger.debug("response return = " + result);

                    return result;
                }else{
                	int statusCode = response.getStatusLine().getStatusCode();
                	logger.debug("return statusCode = {}", statusCode);
                	throw new HttpRequestException(url, "请求异常返回：statusCode = "+statusCode);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
        	logger.debug("call http doGet url = "+ url);
        	logger.debug("response hit error return = " + e.getMessage());

            if (e instanceof HttpHostConnectException || e.getCause() instanceof ConnectException) {
                throw new HttpConnectHostException(url, e.getMessage());
            }
            throw new HttpRequestException(url, e.getMessage());
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {
                    // this exception can be ignored
                }
            }
        }
    }


    /**
     * 自定义静态私有类
     */
    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }
    
    public static boolean startsWith(final String str, final String prefix) {
    	if(str == null || prefix == null){
    		return false;
    	}
        return str.startsWith(prefix);
    }
    
    /**
     * 创建httpClient
     *
     * @return
     */
    private static CloseableHttpClient createSSLInsecureClient() {
        SSLContext sslcontext = createSSLContext();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new HostnameVerifier() {

            @Override
            public boolean verify(String paramString, SSLSession paramSSLSession) {
                return true;
            }
        });

        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        return httpclient;
    }

    /**
     * 获取初始化sslContext
     *
     * @return
     */
    private static SSLContext createSSLContext() {
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            logger.error("HttpRequestUtil.createSSLContext: " + e.getMessage());
        } catch (KeyManagementException e) {
            logger.error("HttpRequestUtil.createSSLContext: " + e.getMessage());
        }
        return sslcontext;
    }
    
    public static String substring(String json, int maxLenght)
    {
    	if(json == null){
			return "";
		}
		if(json.length() > maxLenght){
			return json.substring(0, maxLenght)+"...";
		}
		return json;
    }
    
    public static String substring(String json) {
		return substring(json, DEFAULT_SUBSTRING_MAX_LENGTH);
	}
    
    public static void main(String[] args){
    	doGet("http://10.103.7.99:8080/api-app/test");
    }
}
