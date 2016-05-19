package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import TYUTservice.data.MessageKccx;
import TYUTservice.data.msgdata.KccxMsg;

import com.service.KCCXservice;

public class KcxqAction {
	public KCCXservice getKccXservice() {
		return kccXservice;
	}
	public void setKccXservice(KCCXservice kccXservice) {
		this.kccXservice = kccXservice;
	}
	private String cookie;	
	private String kkyx;
	private String kclb;
	private String kch;
	private String kcm;
	private String page;
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	private KCCXservice kccXservice;
	public void getKcxqList()throws JSONException, IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		out = response.getWriter();
		
		MessageKccx kccx = kccXservice.getListKccx(cookie, kkyx, kclb, kch, kcm,page);
		JSONObject mainjson = new JSONObject();
		if (kccx.getStatus() == 3) {
			List<KccxMsg> list = kccx.getKccxMsgs();
			JSONArray key = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				KccxMsg kccxMsg = list.get(i);
				JSONObject json = new JSONObject();
				json.put("Mon", kccxMsg.getMon());
				json.put("Tue", kccxMsg.getTue());
				json.put("Wed", kccxMsg.getWed());
				json.put("Thu", kccxMsg.getThu());
				json.put("Fri", kccxMsg.getFri());
				key.put(json);
			}
			mainjson.put("id", kccx.getId());
			mainjson.put("status", kccx.getStatus());
			mainjson.put("kccxMsgs", key);
		}else {
			mainjson.put("id", kccx.getId());
			mainjson.put("status", kccx.getStatus());
		}
		out.println(mainjson);
		out.flush();
		out.close();
		
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public String getKkyx() {
		return kkyx;
	}
	public void setKkyx(String kkyx) {
		this.kkyx = kkyx;
	}
	public String getKclb() {
		return kclb;
	}
	public void setKclb(String kclb) {
		this.kclb = kclb;
	}
	public String getKch() {
		return kch;
	}
	public void setKch(String kch) {
		this.kch = kch;
	}
	public String getKcm() {
		return kcm;
	}
	public void setKcm(String kcm) {
		this.kcm = kcm;
	}
}
