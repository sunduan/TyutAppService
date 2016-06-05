package com.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import vo.StudentInfo;
import vo.StudentStatus;

import com.service.GRXXservice;

public class XjxxAction {
	
	private String cookie;
	private GRXXservice grxXservice;
	public GRXXservice getGrxXservice() {
		return grxXservice;
	}
	public void setGrxXservice(GRXXservice grxXservice) {
		this.grxXservice = grxXservice;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
	public void getStudentStatus() throws IOException, JSONException{

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		out = response.getWriter();
		
		StudentStatus info=grxXservice.getStudentStatus(cookie);
		JSONObject mainjson = new JSONObject();
		if(info.getStatus()==3){
			JSONObject key= new JSONObject();
			key.put("idstudent", info.getIdcard());
			key.put("name", info.getName());
			key.put("idcard", info.getIdcard());
			key.put("sex",info.getSex());
			key.put("mz",info.getMz());
			key.put("jg", info.getJg());
			key.put("csrq", info.getCsrq());
			key.put("zz", info.getZz());
			key.put("rxrq", info.getRxrq());
			key.put("xs", info.getXs());
			key.put("zy", info.getZy());
			key.put("nj", info.getNj());
			key.put("bj", info.getBj());
			key.put("sfxj", info.getSfxj());
			key.put("gjxj", info.getGjxj());
			key.put("xq", info.getXq());
			key.put("yd", info.getYd());
			
			mainjson.put("id",0);
			mainjson.put("status",3);
			mainjson.put("grxx",key);
		}else{
			mainjson.put("id",0);
			mainjson.put("status",info.getStatus());		}
		
		out.println(mainjson);
		out.flush();
		out.close();
	}
	
	
	
	
	
}
