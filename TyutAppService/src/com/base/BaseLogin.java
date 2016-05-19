package com.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class BaseLogin {
	public String loginurl;
	public String cookie;
	public String cookieurl;
	public List<NameValuePair> params = new ArrayList<NameValuePair>();
	public String cookiename;

	public BaseLogin() {

	}

	public void ddd() {
		cookie = "abd";
	}

	public String login() {

		// String uriAPI = URL + "/index.aspx";
		HttpClient client = new DefaultHttpClient();
		HttpResponse httpResponse;
		HttpPost httpRequest = new HttpPost(loginurl);
		String result = "";
		try {
			// 锟斤拷锟斤拷HTTP request
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			// 取锟斤拷HTTP response
			httpRequest.setHeader("Cookie", cookiename + cookie);
			httpResponse = client.execute(httpRequest); // 执锟斤拷
			// 锟斤拷状态锟斤拷为200 ok
			if (httpResponse.getStatusLine().getStatusCode() == 200) { // 锟斤拷锟斤拷值锟斤拷锟斤拷
				// 锟斤拷取锟斤拷锟截碉拷cookie
				StringBuffer sb = new StringBuffer();
				HttpEntity entity = httpResponse.getEntity();
				InputStream is = entity.getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, "gb2312"));
				// 锟角讹拷取要锟侥憋拷锟斤拷锟皆达拷锟皆达拷母锟绞斤拷锟紾B2312锟侥ｏ拷锟斤拷源锟斤拷式锟斤拷锟斤拷锟斤拷锟斤拷然锟斤拷锟劫讹拷源锟斤拷转锟斤拷锟斤拷锟斤拷要锟侥憋拷锟斤拷锟斤拷锟�
				String data = "";
				while ((data = br.readLine()) != null) {
					sb.append(data);
				}
				result = sb.toString();
				System.out.println(result);

				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getCookie() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(cookieurl);
		System.out.println("锟斤拷取CookIe锟叫★拷锟斤拷锟斤拷锟斤拷");
		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				List<Cookie> cookies = ((AbstractHttpClient) httpClient)
						.getCookieStore().getCookies();
				System.out.println("锟斤拷玫锟絚ookie为" + cookies.get(0).getValue());
				// ckyzm[0]=cookies.get(0).getValue();
				cookie = cookies.get(0).getValue();

			} else {
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
