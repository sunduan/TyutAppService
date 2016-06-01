package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import TYUTservice.data.MessageKccx;
import TYUTservice.data.msgdata.Course;

import com.service.KCCXservice;

public class KccxAction {
	private String cookie;
	private KCCXservice kccXservice;

	public void getKccxList() throws JSONException, IOException {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		out = response.getWriter();

		MessageKccx kccx = kccXservice.getListCourse(cookie);
		JSONObject mainjson = new JSONObject();
		if (kccx.getStatus() == 3) {
			HashMap<String, HashMap<String, Course>> list = kccx.getKccxMsgs();
			JSONObject key = new JSONObject();
			//System.out.println("&nbsp; &nbsp;");
			
			for (int i = 0; i < 7; i++) {
				HashMap<String,Course> wek = list.get(i+"");
				JSONArray wekarray = new JSONArray();
				
				if(wek.size()==1){
					
				}else{
					int l=wek.size();
				for(int j=0;j<l-1;j++){
					JSONObject json=new JSONObject();
					if(wek.get(j+"").getName().equals(list.get("6").get(list.get("6").size()-2+"").getName())){
						
					}else{
						
					json.put("name", wek.get(j+"").getName());
					json.put("start",wek.get(j+"").getStart());
					json.put("step", wek.get(j+"").getStep());
					wekarray.put(json);}
				}
				if(!wekarray.isNull(0)){
				key.put(i+"",wekarray);
				}else{
					
				}
				}
			}
			mainjson.put("id", kccx.getId());
			mainjson.put("status", kccx.getStatus());
			mainjson.put("kccxMsgs", key);
			
		} else {
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

	public KCCXservice getKccXservice() {
		return kccXservice;
	}

	public void setKccXservice(KCCXservice kccXservice) {
		this.kccXservice = kccXservice;
	}
}
