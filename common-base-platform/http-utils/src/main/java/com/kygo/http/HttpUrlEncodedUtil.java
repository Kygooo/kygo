package com.kygo.http;

import com.kygo.http.exception.HttpConnectHostException;
import com.kygo.http.exception.HttpRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.net.ConnectException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class HttpUrlEncodedUtil {

	private static final String HTTPS = "https";

	private static Logger logger = LoggerFactory.getLogger("HttpUrlEncodedUtil");
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	public static String doPostParam(final String url, Object object) throws HttpRequestException, HttpConnectHostException {
		@SuppressWarnings("unchecked")
		Map<String, String> paramMap = objectMapper.convertValue(object, Map.class);
		return doPostMap(url, paramMap);
	}

	/**
	 * post请求
	 *
	 * @param url
	 * @param paramMap
	 * @return
	 * @throws HttpRequestException
	 * @throws HttpConnectHostException 
	 */
	public static String doPostMap(final String url, final Map<String, String> paramMap) throws HttpRequestException, HttpConnectHostException {
		CloseableHttpClient client = null;
		try {
			if (startsWith(url, HTTPS)) {
				client = createSSLInsecureClient();
			} else {
				client = HttpClients.createDefault();
			}

			HttpPost post = new HttpPost(url);
			if (paramMap != null) {
				List<NameValuePair> formparams = new ArrayList<NameValuePair>();
				Set<Map.Entry<String, String>> entries = paramMap.entrySet();
				if (entries != null) {
					Iterator<Map.Entry<String, String>> iterator = entries.iterator();
					while (iterator.hasNext()) {
						Map.Entry<String, String> keyVlaue = iterator.next();
						BasicNameValuePair pair = new BasicNameValuePair(keyVlaue.getKey(), keyVlaue.getValue());
						formparams.add(pair);

					}
					HttpEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
					post.setEntity(entity);
				}
			}
			CloseableHttpResponse response = client.execute(post);
			try {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					return EntityUtils.toString(response.getEntity(), Consts.UTF_8);
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			if (e instanceof HttpHostConnectException || e.getCause() instanceof ConnectException) {
				throw new HttpConnectHostException(url, "不能连接到主机!");
			}
			logger.error("HttpUrlEncodedUtil.doPost: " + e.getMessage() + ", url = " + url);
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
	 * 发送get请求
	 *
	 * @param url
	 * @return
	 * @throws HttpRequestException
	 * @throws HttpConnectHostException 
	 */
	public static String doGet(String url) throws HttpRequestException, HttpConnectHostException {
		CloseableHttpClient client = null;
		try {
			if (startsWith(url, HTTPS)) {
				client = createSSLInsecureClient();
			} else {
				client = HttpClients.createDefault();
			}
			HttpGet httpget = new HttpGet(url);
			CloseableHttpResponse response = client.execute(httpget);
			try {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					return EntityUtils.toString(response.getEntity(), Consts.UTF_8);
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			if (e instanceof HttpHostConnectException || e.getCause() instanceof ConnectException) {
				throw new HttpConnectHostException(url, "不能连接到主机!");
			}
			logger.error("HttpUrlEncodedUtil.doGet: " + e.getMessage() + ", url = " + url);
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (Exception e) {
					// this exception can be ignored
				}
			}
		}
		return "";
	}
	
	/**
     * 通过url获取到存放于字节数组的资源
     *
     * @param url
     * @return
     */
    private static byte[] getByteArrayFromUrl(String url) {
        byte[] byteArr = null;
        CloseableHttpClient client = null;
        try {
            if (startsWith(url, HTTPS)) {
                client = createSSLInsecureClient();
            } else {
                client = HttpClients.createDefault();
            }
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = client.execute(httpget);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                byteArr = EntityUtils.toByteArray(response.getEntity());
            }
        } catch (Exception e) {
            logger.error("通过url获取到存放于字节数组的资源失败", e);
            return null;
        }
        return byteArr;
    }

    /**
     * 把图片url转成base64
     *
     * @param url
     * @return
     */
    public static String getBase64FromUrl(String url) {
        byte[] bytes = getByteArrayFromUrl(url);
        if (bytes == null) {
            return null;
        }
        String base64 = Base64.encodeBase64String(bytes);
        base64.replace("\r", "");
        base64.replace("\n", "");
        return base64;
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
			return new X509Certificate[] {};
		}
	}

	public static boolean startsWith(final String str, final String prefix) {
		if (str == null || prefix == null) {
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
			sslcontext.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new SecureRandom());
		} catch (NoSuchAlgorithmException e) {
			logger.error("HttpUrlEncodedUtil.createSSLContext: " + e.getMessage());
		} catch (KeyManagementException e) {
			logger.error("HttpUrlEncodedUtil.createSSLContext: " + e.getMessage());
		}
		return sslcontext;
	}

}
