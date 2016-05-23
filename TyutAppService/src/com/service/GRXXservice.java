package com.service;

import com.dao.GRXXdao;

import vo.StudentInfo;

public class GRXXservice {
	private GRXXdao grxXdao;
	public GRXXdao getGrxXdao() {
		return grxXdao;
	}
	public void setGrxXdao(GRXXdao grxXdao) {
		this.grxXdao = grxXdao;
	}
	public StudentInfo getStudentInfo(String cookie){
		return grxXdao.getStudentInfo(cookie);
	}
}
