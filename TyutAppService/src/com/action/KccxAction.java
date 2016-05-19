package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.service.KCCXservice;

import TYUTservice.data.MessageKccx;
import TYUTservice.data.msgdata.KccxMsg;

public class KccxAction {
	private String cookie;
	private KCCXservice kccXservice;

	public void getKccxList() throws JSONException, IOException {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		out = response.getWriter();

		MessageKccx kccx = kccXservice.getListKccx(cookie);
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
				json.put("Sat", kccxMsg.getSat());
				json.put("Sun", kccxMsg.getSun());

				key.put(json);
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
