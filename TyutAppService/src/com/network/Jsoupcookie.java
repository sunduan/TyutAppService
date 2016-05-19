package com.network;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Jsoupcookie {
	private String url;
	private String cookie;
	private Map<String, String> map;

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
				doc = Jsoup.connect(this.url).cookie("JSESSIONID", this.cookie)
						.timeout(100000).get();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("Time is:"
						+ (System.currentTimeMillis() - start) + "ms");
			}
		}
		return doc;
	}

	public Document postDoc() {
		Document doc = null;
		long start = System.currentTimeMillis();
		while (doc == null) {
			try {
				// POST
				doc = Jsoup.connect(this.url).cookie("JSESSIONID", this.cookie)
						.data(this.map).timeout(100000).post();
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
