package com.service;

import com.dao.GRXXdao;

import vo.StudentInfo;
import vo.StudentStatus;

public class GRXXservice {
	private GRXXdao grxXdao;
	public GRXXdao getGrxXdao() {
		return grxXdao;
	}
	public void setGrxXdao(GRXXdao grxXdao) {
		this.grxXdao = grxXdao;
	}
	public StudentStatus getStudentStatus(String cookie){
		return grxXdao.getStudentStatus(cookie);
	}
	public StudentInfo getStudentInfo(String cookie){
		return grxXdao.getStudentInfo(cookie);
	}
}
