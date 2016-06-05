package com.network;

import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Jsoupcookie {
	private String url;
	private String cookie;
	private Map<String, String> map;
	private List<String> key;
	private List<String> value;

	public List<String> getKey() {
		return key;
	}

	public void setKey(List<String> key) {
		this.key = key;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}

	public Jsoupcookie(String url, String cookie, List<String> key,
			List<String> value, Map<String, String> map) {
		this.url = url;
		this.cookie = cookie;
		this.value = value;
		this.key = key;
		this.map = map;
	}

	public Jsoupcookie(String url, String cookie, Map<String, String> map) {
		this.url = url;
		this.cookie = cookie;
		this.map = map;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public Jsoupcookie(String url, String cookie) {
		this.url = url;
		this.cookie = cookie;
	}

	public Document getDoc() {
		Document doc = null;
		long start = System.currentTimeMillis();
		while (doc == null) {
			try {
				// GET
				Connection connection=Jsoup.connect(this.url).cookie("JSESSIONID", this.cookie)
						.timeout(5000);
				
				if(connection.execute().statusCode()==500){
					break;
				}else{
				doc = connection.get();
				}
			} catch (Exception e) {
				e.printStackTrace();
				if(e instanceof HttpStatusException){
					break;
				}
			} finally {
				System.out.println("Time is:"
						+ (System.currentTimeMillis() - start) + "ms");
			}
		}
		return doc;
	}

	public Document getDoc(int time) {
		Document doc = null;
		long start = System.currentTimeMillis();
		while (doc == null) {
			try {
				// GET
				Connection connection=Jsoup.connect(this.url).cookie("JSESSIONID", this.cookie)
						.timeout(time);
				
				if(connection.execute().statusCode()==500){
					break;
				}else{
				doc = connection.get();
				}
			} catch (Exception e) {
				e.printStackTrace();
				if(e instanceof HttpStatusException){
					break;
				}
			} finally {
				System.out.println("Time is:"
						+ (System.currentTimeMillis() - start) + "ms");
			}
		}
		return doc;
	}

	public Document postDoc(int time) {
		Document doc = null;
		long start = System.currentTimeMillis();
		while (doc == null) {
			try {
				// POST
				
				Connection loginConn = Jsoup.connect(this.url)
						.cookie("JSESSIONID", this.cookie).timeout(time)
						.data(map).postDataCharset("gbk");

				for (int i = 0; i < key.size(); i++) {
					loginConn.data(key.get(i), value.get(i));
				}
				
				
				if(loginConn.execute()==null||loginConn.execute().statusCode()==500){
					break;
				}else{
					doc = loginConn.post();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				if(e instanceof HttpStatusException){
					break;
				}
			} finally {
				System.out.println("Time is:"
						+ (System.currentTimeMillis() - start) + "ms");
			}

		}
		return doc;
	}

	public Document postLogin() {
		Document doc = null;
		long start = System.currentTimeMillis();
		if(this.cookie==null){
			this.cookie="1";
		}
		while(doc==null){
		try {
			// POST
			Connection loginConn = Jsoup.connect(this.url)
					.cookie("JSESSIONID", this.cookie).timeout(5000).data(map)
					.postDataCharset("gbk");
			doc = loginConn.post();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Time is:"
					+ (System.currentTimeMillis() - start) + "ms");

		}
		}
		return doc;
	}
}
