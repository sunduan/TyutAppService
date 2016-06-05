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

public class GrxxAction {
	private String cookie;
	private GRXXservice grxXservice;

	public void getStudentInfo() throws IOException, JSONException{
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		out = response.getWriter();
		
		StudentInfo info=grxXservice.getStudentInfo(cookie);
		JSONObject mainjson = new JSONObject();
		if(info.getStatus()==3){
			JSONObject key= new JSONObject();
			key.put("name", info.getName());
			key.put("userper", info.getUserper());
			key.put("tel",info.getTel());
			key.put("address",info.getAddress());
			key.put("email", info.getEmail());
			
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
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public GRXXservice getGrxXservice() {
		return grxXservice;
	}
	public void setGrxXservice(GRXXservice grxXservice) {
		this.grxXservice = grxXservice;
	}
}
