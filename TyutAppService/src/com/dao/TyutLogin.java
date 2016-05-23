package com.dao;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import vo.User;

import com.base.BaseLogin;
import com.yzm.GetLogo;

public class TyutLogin extends BaseLogin {

	private String yzm;
	List<Cookie> cookies;
	
	public TyutLogin() {

	}
	/*
	 * public static void main(String arg[]) { boolean getBOO = false; TyutLogin
	 * tl =null; for(;!getBOO;){ User user = new User("2012005003",
	 * "142727199408286017"); tl=new TyutLogin() ;
	 * 
	 * getBOO= tl.login("", user); for(;!getBOO;getBOO=tl.login("", user)); tl=
	 * new TyutLogin();} Test3 t=new Test3(); t.js(tl.cookies); }
	 */
private String testurl="202.207.247.44";
	public String login(String university, User user) {
		System.out.println(user.getUsername());
		//http://202.207.247.49
		//"http://202.207.247.44:8065
		this.loginurl = "http://"+testurl+":8065/loginAction.do";
		this.cookiename = "JSESSIONID=";
		this.cookieurl = "http://"+testurl+":8065/validateCodeAction.do?random="
				+ Math.random();
		this.getCookie();
		// this.cookie="bdc4JtKs639VNdcNO3Nlv";
		System.out.println("验证码：" + yzm);
		this.params.add(new BasicNameValuePair("zjh", user.getUsername()));
		// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�取锟斤拷锟斤拷
		this.params.add(new BasicNameValuePair("mm", user.getPassword()));
		this.params.add(new BasicNameValuePair("v_yzm", yzm));
		String cs = this.login();
		if (cs.indexOf("学分制综合教务") != -1) {
			System.out.println("登录成功");
			return "3";
		} else if (cs.indexOf("您的密码不正确") != -1||cs.indexOf("你输入的证件号不存在")!=-1) {
			System.out.println("错误为：您的密码不正确");
			return "2";
		} else if (cs.indexOf("你输入的验证码") != -1) {
			System.out.println("错误为：验证码不正确");
			return "1";
		} else {
			//异常
			return "4";
		}

	}

	public void getCookie() {
		getLoginCookie();
		GetLogo g = new GetLogo(this.cookieurl, this.cookie);
		this.yzm = g.getLogo();
	}

	public void getLoginCookie() {

		String destUrl = "http://"+testurl+":8065";
		HttpClient httpClient = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(destUrl);

		try {
			/* 锟斤拷锟斤拷HTTP request */
			// HttpResponse httpResponse2 = new
			// DefaultHttpClient().execute(httpRequest3);
			System.out.println("sdf,ddd2...");
			HttpResponse httpResponse = httpClient.execute(httpGet);

			/* 锟斤拷状态锟斤拷为200 ok */
			if (httpResponse.getStatusLine().getStatusCode() == 200) {

				System.out.println("dfd");

				List<Cookie> cookies = ((AbstractHttpClient) httpClient)
						.getCookieStore().getCookies();
				this.cookies = cookies;
				System.out.println(cookies.get(0).getValue());
				cookie = cookies.get(0).getValue();
				// Log.i("锟缴癸拷",cookies.get(0).getValue());
			} else {
				// Log.i("锟斤拷锟斤拷锟斤拷","失锟斤拷");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
