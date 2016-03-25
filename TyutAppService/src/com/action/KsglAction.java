package com.action;

import com.service.KSGLservice;


public class KsglAction {
private String cookie;
private KSGLservice ksgLservice;

public void getListKsxx()
{
	
	ksgLservice.getListKsxx(cookie);
	
}

public String getCookie() {
	return cookie;
}

public void setCookie(String cookie) {
	this.cookie = cookie;
}

public KSGLservice getKsgLservice() {
	return ksgLservice;
}

public void setKsgLservice(KSGLservice ksgLservice) {
	this.ksgLservice = ksgLservice;
}
}
