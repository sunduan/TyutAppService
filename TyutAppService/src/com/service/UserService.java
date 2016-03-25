package com.service;

import TYUTservice.data.MessageLogin;
import TYUTservice.data.MessageTyut;

import com.dao.LoginDao;

import vo.User;

public class UserService {
	public MessageLogin userLogin(User user){
		LoginDao dao=new LoginDao();
		return dao.userlogin(user);
	}
	//public String userYxkc()

}
