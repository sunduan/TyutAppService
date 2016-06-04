package com.service;

import TYUTservice.data.MessageKccx;

import com.dao.KCCXdao;

public class KCCXservice {
	private KCCXdao kccXdao;
/*public MessageKccx getListKccx(String cookie){
		
		return kccXdao.getListKccx(cookie);
	}*/
/*public MessageKccx getListKccx(String cookie,String kkyx,String kclb,String kch,String kcm,String page){
	
	return kccXdao.getListKccx(cookie, kkyx, kclb, kch, kcm,page);
}*/
public MessageKccx getListCourse(String cookie){
	return kccXdao.getListCourse(cookie);
}
public MessageKccx getBxqkc(String cookie,String actionType, String pageNumber, String kcm,
		String jsm, String xsjc, String skjc, String xaqh, String jxlh,
		String jash){
	return kccXdao.getBxqkc(cookie,actionType, pageNumber, kcm, jsm, xsjc, skjc, xaqh, jxlh, jash);
}
public KCCXdao getKccXdao() {
	return kccXdao;
}
public void setKccXdao(KCCXdao kccXdao) {
	this.kccXdao = kccXdao;
}
}
