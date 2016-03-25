package com.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import vo.User;
import TYUTservice.data.MessageLogin;
import TYUTservice.data.MessageTyut;

import com.service.UserService;

public class LoginAction {
	private String username;
	private String password;

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	private UserService service;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		PrintWriter out;
		out = response.getWriter();
		JSONObject json = new JSONObject();

		System.out.println(username + password);
		MessageLogin loginbo = (MessageLogin) service.userLogin(new User(username, password));
			json.put("id",loginbo.getId());
			json.put("status",loginbo.getStatus());
			json.put("cookie", loginbo.getCookie());
		
		
			//json.put("loginmess", loginbo.hashCode());
			// return Action.SUCCESS;
		
		out.println(json);
		out.flush();
		out.close();
	}
}
