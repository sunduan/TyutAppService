package com.dao;

import TYUTservice.data.MessageLogin;
import TYUTservice.data.MessageTyut;
import vo.User;

public class LoginDao {
	
	public MessageLogin userlogin(User user) {
		String getBOO = "1";
		TyutLogin tyutLogin = null;
		for (; getBOO.equals("1");) {
			// User user = new User("2012005003", "142727199408286017");
			tyutLogin = new TyutLogin();
			System.out.println("getbooé_Ê¼");
			getBOO = tyutLogin.login("", user);
			System.out.println("getboo½YÊø"+getBOO);
			/*
			 * for(;!getBOO;getBOO=tl.login("", user)); tl= new TyutLogin();
			 */
		}
		//Test3 t = new Test3();
		//t.js(tyutLogin.cookies);
		MessageLogin messageTyut=new MessageLogin(1, Integer.parseInt(getBOO), tyutLogin.cookies.get(0).getValue());
		
		return messageTyut;
	}
}
