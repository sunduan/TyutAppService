/*package com.dao;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.network.Jsoupcookie;

import vo.StudentInfo;
import vo.StudentStatus;

public class XjxxDao {
	public StudentStatus getStudentStatus(String cookie){
		
		
		StudentStatus info=null;
		Jsoupcookie jsoupcookie=new Jsoupcookie("http://202.207.247.44:8065/userInfo.jsp",cookie);
		//doc
		Document document=jsoupcookie.getDoc();
		
		if (document==null||document.text().equals("")) {
			info.setStatus(2);

		} else {
		
		Elements tds=document.getElementsByAttributeValue("class", "fieldName");
		String td=document.getElementsByAttributeValue("valign", "top").select("tbody").select("tr").get(0).child(5).text();
		info=new StudentStatus(idstudent, name, idcard, sex, mz, jg, csrq, zz, rxrq, xs, zy, nj, bj, sfxj, gjxj, xq, yd, status)
		
		}
		return info;
	}
}
*/