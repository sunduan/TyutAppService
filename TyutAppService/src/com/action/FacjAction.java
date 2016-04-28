package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import TYUTservice.data.FacjMsg;
import TYUTservice.data.MessageFacj;

import com.service.FACJservice;

public class FacjAction {
	private String cookie;
	private String address;
	private FACJservice facJservice;

	public void getFacjList() throws JSONException, IOException {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		out = response.getWriter();
		
		
		MessageFacj facj = facJservice.getListFacj(cookie, address);
		JSONObject mainjson = new JSONObject();
		if(facj.getStatus()==3){
			List<FacjMsg> facjMsgs=facj.getFacjMsgs();
			JSONArray key= new JSONArray();
			for (int i = 0; i < facjMsgs.size(); i++) {
				FacjMsg facjMsg=facjMsgs.get(i);
				JSONObject json = new JSONObject();
				json.put("kch", facjMsg.getKch());
				json.put("kxh", facjMsg.getKxh());
				json.put("kcm", facjMsg.getKcm());
				json.put("ywkcm", facjMsg.getYwkcm());
				json.put("xf", facjMsg.getXf());
				json.put("kcsx", facjMsg.getKcsx());
				json.put("cj", facjMsg.getCj());
				json.put("wtgyy", facjMsg.getWtgyy());
				
				key.put(json);
			}
			mainjson.put("id",facj.getId());
			mainjson.put("status",facj.getStatus());
			mainjson.put("facjMsgs",key);
			
		}else{
			mainjson.put("id","3");
			mainjson.put("status","1");
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public FACJservice getFacJservice() {
		return facJservice;
	}

	public void setFacJservice(FACJservice facJservice) {
		this.facJservice = facJservice;
	}
}
