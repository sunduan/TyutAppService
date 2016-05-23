package com.dao;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.network.Jsoupcookie;

import vo.StudentInfo;

public class GRXXdao {
	public StudentInfo getStudentInfo(String cookie){
		StudentInfo info=null;
		Jsoupcookie jsoupcookie=new Jsoupcookie("http://202.207.247.44:8065/userInfo.jsp",cookie);
		//doc
		Document document=jsoupcookie.getDoc();
		
		Elements tds=document.getElementsByAttributeValue("class", "fieldName");
		String td=document.getElementsByAttributeValue("valign", "top").select("tbody").select("tr").get(0).child(5).text();
		info=new StudentInfo(tds.get(0).nextElementSibling().text(),td,document.getElementsByAttributeValue("name", "dh").get(0).attr("value"),document.getElementsByAttributeValue("name", "txdz").get(0).attr("value"),document.getElementsByAttributeValue("name", "email").get(0).attr("value"));
		
		
		return info;
	}
}
