package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import TYUTservice.data.MessagePyfa;
import TYUTservice.data.msgdata.PyfaMsg;

import com.service.PYFAservice;

public class PyfaAction {
	private String cookie;
	private PYFAservice pyfAservice;

	public void getPyfaList() throws JSONException, IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		out = response.getWriter();

		MessagePyfa messagePyfa = pyfAservice.getListPyfa(cookie);
		JSONObject mainjson = new JSONObject();
		if (messagePyfa.getStatus() == 3) {
			List<PyfaMsg> list = messagePyfa.getPyfaMsgs();
			JSONArray key = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				PyfaMsg pyfaMsg = list.get(i);
				JSONObject json = new JSONObject();
				json.put("Mon", pyfaMsg.getMon());
				json.put("Tue", pyfaMsg.getTue());
				json.put("Wed", pyfaMsg.getWed());
				json.put("Thu", pyfaMsg.getThu());
				json.put("Fri", pyfaMsg.getFri());
				json.put("Sat", pyfaMsg.getSat());
				json.put("Sun", pyfaMsg.getSun());

				key.put(json);
			}
			mainjson.put("id", messagePyfa.getId());
			mainjson.put("status", messagePyfa.getStatus());
			mainjson.put("pyfaMsgs", key);

		} else {
			mainjson.put("id", messagePyfa.getId());
			mainjson.put("status", messagePyfa.getStatus());
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

	public PYFAservice getPyfAservice() {
		return pyfAservice;
	}

	public void setPyfAservice(PYFAservice pyfAservice) {
		this.pyfAservice = pyfAservice;
	}

}
