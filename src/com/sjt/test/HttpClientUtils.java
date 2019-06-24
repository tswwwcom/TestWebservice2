package com.sjt.test;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientUtils {
	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
	private HttpClientUtils(){
	}
	
	/**
	 * 执行HTTP POST请求
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param headers 请求头
	 * @return
	 */
	public static String doPost(String url, String params, Map<String, String> headers){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		
		RequestConfig requestConfig = 
				RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(15000).build();
		httpPost.setConfig(requestConfig);
		//设置请求头
		if(headers != null && !headers.isEmpty()){
			for(Entry<String, String> entry : headers.entrySet()){
				httpPost.setHeader(entry.getKey(), entry.getValue());
			}
		}
		
		CloseableHttpResponse response = null;
		try {
			StringEntity stringEntity = new StringEntity(params, "UTF-8");
			httpPost.setEntity(stringEntity);
		
			response = httpclient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if(200 == statusCode){
				HttpEntity entity = response.getEntity();
				if(entity != null){
					return EntityUtils.toString(entity, "UTF-8");
				}
			}
		} catch (Exception e) {
			logger.error("执行HTTP POST 请求失败！", e);
		} finally{
			try {
				if(response != null)
					response.close();
				
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
