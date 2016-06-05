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
	private String actionType;
	private String pageNumber;
	private String kcm;
	private String jsm;
	private String xsjc;
	private String skjc;
	private String xaqh;
	private String jxlh;
	private String jash;
	
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getKcm() {
		return kcm;
	}
	public void setKcm(String kcm) {
		this.kcm = kcm;
	}
	public String getJsm() {
		return jsm;
	}
	public void setJsm(String jsm) {
		this.jsm = jsm;
	}
	public String getXsjc() {
		return xsjc;
	}
	public void setXsjc(String xsjc) {
		this.xsjc = xsjc;
	}
	public String getSkjc() {
		return skjc;
	}
	public void setSkjc(String skjc) {
		this.skjc = skjc;
	}
	public String getXaqh() {
		return xaqh;
	}
	public void setXaqh(String xaqh) {
		this.xaqh = xaqh;
	}
	public String getJxlh() {
		return jxlh;
	}
	public void setJxlh(String jxlh) {
		this.jxlh = jxlh;
	}
	public String getJash() {
		return jash;
	}
	public void setJash(String jash) {
		this.jash = jash;
	}
	private KCCXservice kccXservice;
	public void getKcxqList()throws JSONException, IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		out = response.getWriter();
		//½âÂë
		kcm=new String(kcm.getBytes("ISO-8859-1"),"utf-8");
		jsm=new String(jsm.getBytes("ISO-8859-1"),"utf-8");
		xsjc=new String(xsjc.getBytes("ISO-8859-1"),"utf-8");
		System.out.println("action²ã"+kcm);
		MessageKccx kccx = kccXservice.getBxqkc(cookie,actionType, pageNumber, kcm, jsm, xsjc, skjc, xaqh, jxlh, jash);
		JSONObject mainjson = new JSONObject();
		if (kccx.getStatus() == 3) {
			List<KccxMsg> list = kccx.getKccxMsg();
			JSONArray key = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				KccxMsg kccxMsg = list.get(i);
				JSONObject json = new JSONObject();
				json.put("kcm", kccxMsg.getMon());
				json.put("xf", kccxMsg.getTue());
				json.put("js", kccxMsg.getWed());
				json.put("jc", kccxMsg.getThu());
				json.put("xq", kccxMsg.getFri());
				json.put("zc", kccxMsg.getSat());
				json.put("con", kccxMsg.getSun());
				key.put(json);
			}
			mainjson.put("id", kccx.getId());
			mainjson.put("status", kccx.getStatus());
			mainjson.put("kcxqMsgs", key);
		}else {
			mainjson.put("id", kccx.getId());
			mainjson.put("status", kccx.getStatus());
		}
		out.println(mainjson);
		out.flush();
		out.close();
		
	}
	
}
