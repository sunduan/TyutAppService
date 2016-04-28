package com.service;

import TYUTservice.data.MessageLogin;
import TYUTservice.data.MessageTyut;

import com.dao.LoginDao;

import vo.User;

public class UserService {
	private LoginDao dao;
	public MessageLogin userLogin(User user){
		return dao.userlogin(user);
	}
	//public String userYxkc()
	public LoginDao getDao() {
		return dao;
	}
	public void setDao(LoginDao dao) {
		this.dao = dao;
	}

}
