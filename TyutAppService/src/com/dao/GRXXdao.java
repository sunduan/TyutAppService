package com.dao;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.network.Jsoupcookie;

import vo.StudentInfo;
import vo.StudentStatus;

public class GRXXdao {
	public StudentInfo getStudentInfo(String cookie){
		StudentInfo info=null;
		Jsoupcookie jsoupcookie=new Jsoupcookie("http://202.207.247.44:8065/userInfo.jsp",cookie);
		//doc
		Document document=jsoupcookie.getDoc();
		
		if (document==null||document.text().equals("")) {
			info.setStatus(2);

		} else {
		
		Elements tds=document.getElementsByAttributeValue("class", "fieldName");
		String td=document.getElementsByAttributeValue("valign", "top").select("tbody").select("tr").get(0).child(5).text();
		info=new StudentInfo(tds.get(0).nextElementSibling().text(),td,document.getElementsByAttributeValue("name", "dh").get(0).attr("value"),document.getElementsByAttributeValue("name", "txdz").get(0).attr("value"),document.getElementsByAttributeValue("name", "email").get(0).attr("value"),3);
		
		}
		return info;
	}
public StudentStatus getStudentStatus(String cookie){
		
		
		StudentStatus info=null;
		Jsoupcookie jsoupcookie=new Jsoupcookie("http://202.207.247.44:8065/xjInfoAction.do?oper=xjxx",cookie);
		//doc
		Document doc=jsoupcookie.getDoc();
		
		if (doc==null||doc.text().equals("")) {
			info.setStatus(2);

		} else {
		System.out.println(doc.text());
		Element table=doc.getElementById("tblView");
		Elements trs=table.select("tr");
		
		
		info=new StudentStatus(trs.get(0).select("td").get(1).text(), trs.get(0).select("td").get(3).text(), 
				trs.get(2).select("td").get(3).text(), trs.get(3).select("td").get(1).text(),
				trs.get(5).select("td").get(3).text(), trs.get(6).select("td").get(1).text(),
				trs.get(6).select("td").get(3).text(), trs.get(7).select("td").get(1).text(),
				trs.get(12).select("td").get(1).text(), trs.get(12).select("td").get(3).text(),
				trs.get(13).select("td").get(1).text(), trs.get(14).select("td").get(1).text(), 
				trs.get(14).select("td").get(3).text(), trs.get(15).select("td").get(1).text(), trs.get(15).select("td").get(3).text(),
				trs.get(16).select("td").get(1).text(), trs.get(16).select("td").get(3).text(), 3);
		}
		return info;
	}
}
