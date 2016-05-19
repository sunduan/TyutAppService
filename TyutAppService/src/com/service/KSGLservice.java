package com.service;

import java.util.List;

import TYUTservice.data.MessageKsgl;

import com.dao.KSGLdao;

public class KSGLservice {
	//public MessageTyut 
	private KSGLdao ksgLdao;
	public KSGLdao getKsgLdao() {
		return ksgLdao;
	}
	public void setKsgLdao(KSGLdao ksgLdao) {
		this.ksgLdao = ksgLdao;
	}
	public List<MessageKsgl> getListKsxx(String cookie){
		
		ksgLdao.getListKsxx(cookie);
		return null;
	
	}
	
}
