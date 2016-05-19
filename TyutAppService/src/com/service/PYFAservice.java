package com.service;

import TYUTservice.data.MessagePyfa;

import com.dao.PYFAdao;

public class PYFAservice {
	private PYFAdao pyfAdao;

	public PYFAdao getPyfAdao() {
		return pyfAdao;
	}

	public void setPyfAdao(PYFAdao pyfAdao) {
		this.pyfAdao = pyfAdao;
	}

	public MessagePyfa getListPyfa(String cookie) {

		return pyfAdao.getListPYFdao(cookie);
	}
}
