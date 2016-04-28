package com.service;

import TYUTservice.data.MessageFacj;

import com.dao.FACJdao;

public class FACJservice {
	public FACJdao getFacJdao() {
		return facJdao;
	}
	public void setFacJdao(FACJdao facJdao) {
		this.facJdao = facJdao;
	}
	private FACJdao facJdao;
	public MessageFacj getListFacj(String cookie,String address){
		
		return facJdao.getListFacj(cookie, address);
	}
}
