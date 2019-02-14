package com.org.ixinhai.until;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;




public class SMS {
	//private static String url="10.168.20.183:8080/Demo/requestTest2.do";
	public static String SetSMS(String jobName) throws Exception {
		String jsonString = "";
		HttpClient client = new DefaultHttpClient();// 实例化http
		HttpPost request = new HttpPost();
//		request.setURI(new URI("http://wcphp172.xinhaimobile.cn/xh_sms/sms/sms_qcloud.php"));// 设置url地址
		request.setURI(new URI("http://wcjava71.xinhaimobile.cn/api/company/work_add_wx.jsp?method=find_user_by_company_id"));// 设置url地址
		//		request.setHeader("Accept", "application/json");
//		request.setHeader("Content-Type", "application/json");
		String charSet = "UTF-8";
		// 设置参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("company_id",jobName ));
		nvps.add(new BasicNameValuePair("openid","0" ));
	//	nvps.add(new BasicNameValuePair("name", name));
	//	nvps.add(new BasicNameValuePair("content", content));
	//	nvps.add(new BasicNameValuePair("templId", templId));
	//	nvps.add(new BasicNameValuePair("operation", operation));
		
		// 将参数写入request请求体 , 设置编码格式
		request.setEntity(new UrlEncodedFormEntity(nvps, charSet));
		HttpResponse response = client.execute(request);// 获得转发
		StatusLine status = response.getStatusLine();
		int state = status.getStatusCode();
		System.err.println("状态码" + state);

		if (state == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			jsonString = EntityUtils.toString(entity);
		//	System.err.println("返回结果" + jsonString);
			return jsonString;
		//	return "succed";

		} else {

			System.err.println("请求失败：-------" + status);
			return "false";
		}
		//return null;
		//	return "ok";
	}
	

	
	
public static void main(String[] args) throws Exception {
	String jsonString = "";
	jsonString=SMS.SetSMS("820056");
	System.err.println(jsonString);
	//System.out.println(jsonString);
}
	
	
}