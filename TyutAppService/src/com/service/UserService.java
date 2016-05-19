package com.service;

import vo.User;
import TYUTservice.data.MessageLogin;

import com.dao.LoginDao;

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
