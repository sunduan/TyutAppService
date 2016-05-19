package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test3 {
	List<Cookie> cookies; // �����ȡ��cookie
	HttpClient client;
	HttpResponse httpResponse;

	public Test3() {

	}

	public static void main(String arg[]) {

		Test3 t = new Test3();
		t.dl();
	}

	public void dl() {

		String uriAPI = "http://202.207.247.44:8065/loginAction.do";

		HttpPost httpRequest = new HttpPost(uriAPI);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("zjh", "2012005003")); // �������������?ȡ����
		params.add(new BasicNameValuePair("mm", "142727199408286017"));

		try {
			// ����HTTP request
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			// ȡ��HTTP response
			httpResponse = client.execute(httpRequest); // ִ��
			// ��״̬��Ϊ200 ok
			if (httpResponse.getStatusLine().getStatusCode() == 200) { // ����ֵ����
				// ��ȡ���ص�cookie
				cookies = ((AbstractHttpClient) client).getCookieStore()
						.getCookies();
				js(cookies);
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void js(List<Cookie> cookies) {
		// http://202.207.247.44:8065/
		// ��ѧ�ڳɼ�
		// String url = "http://202.207.247.44:8065/bxqcjcxAction.do";
		// ��ѧ�ڿα�
		String url = "http://202.207.247.44:8065/xkAction.do?actionType=6";
		long start = System.currentTimeMillis();
		Document doc = null;
		try {
			// doc = Jsoup.connect(url).get();
			doc = Jsoup.connect(url)
					.cookie("JSESSIONID", cookies.get(0).getValue()).get();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Time is:"
					+ (System.currentTimeMillis() - start) + "ms");
		}
		// ��ѧ�ڳɼ�
		/*
		 * Elements elem = doc.select("table"); Element
		 * e=doc.getElementById("user"); Elements tds = e.select("td"); for(int
		 * j = 0;j<tds.size();j++){ String text = tds.get(j).text();
		 * System.out.println(text);
		 */
		// ��ѧ�ڿα�
		Elements elem = doc.getElementsByClass("displayTag");
		//for (int k = 0; k < elem.size(); k++) {
			Element e = elem.get(1).getElementById("user");
			Elements trs = e.select("tr");
			for (int i = 0; i < trs.size(); i++) {

				Elements tds = trs.get(i).select("td");
				for (int j = 0; j < tds.size(); j++) {
					String text = tds.get(j).text();
					System.out.println(text + "���Ϊ��" + i + j);
				}
			}
		//}
	}
}