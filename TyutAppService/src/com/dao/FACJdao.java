package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import TYUTservice.data.MessageFacj;
import TYUTservice.data.msgdata.FacjMsg;

import com.network.Jsoupcookie;

public class FACJdao {

	public MessageFacj getListFacj(String cookie, String address) {
		int ad = Integer.parseInt(address);
		System.out.println(ad);
		switch (ad) {
		//去除软工方案
		/*case 1:
			return rgfa(cookie);*/
		case 2:
			return kcsx(cookie, 0);
		case 3:
			return kcsx(cookie, 1);
		case 4:
			return kcsx(cookie, 2);
		case 5:
			return kcsx(cookie, 3);
		case 6:
			return kcsx(cookie, 4);
		case 7:
			return kcsx(cookie, 5);
		case 8:
			return kcsx(cookie, 6);
		case 9:
			return kcsx(cookie, 7);
		case 10:
			return jg(cookie, 1);
		case 11:
			return jg(cookie, 0);
		case 12:
			return bxqcj(cookie);
		case 13:
			return zzsj(cookie);
		default:
			return null;
		}
	}

	public MessageFacj rgfa(String cookie) {

		List<FacjMsg> list = new ArrayList<FacjMsg>();
		MessageFacj facj = new MessageFacj();
		// 获取doc
		Jsoupcookie jsoupcookie = new Jsoupcookie(
				"http://202.207.247.44:8065/gradeLnAllAction.do?type=ln&oper=fainfo&fajhh=2514#qb_2514",
				cookie);
		Document doc = jsoupcookie.getDoc();
		
		if(doc==null||doc.text()==""){
			facj.setId(3);
			facj.setStatus(2);
		}else{
			
		
		Elements trs = doc.getElementsByAttributeValue("onmouseout",
				"this.className='even';");
		int l = trs.size();
		for (int i = 0; i < l; i++) {
			Element tr = trs.get(i);
			Elements tds = tr.select("td");
			Elements ps = tr.select("p");

			FacjMsg facjmsg = new FacjMsg(tds.get(0).text(), tds.get(1).text(),
					tds.get(2).text(), tds.get(3).text(), tds.get(4).text(),
					tds.get(5).text(), ps.get(0).text(), ps.get(1).text());
			list.add(facjmsg);
		}
		if (list != null) {
			facj.setId(3);
			facj.setStatus(3);
			facj.setFacjMsgs(list);
		}
		}
		return facj;

	}

	public MessageFacj kcsx(String cookie, int ad) {

		List<FacjMsg> list = new ArrayList<FacjMsg>();
		MessageFacj facj = new MessageFacj();
		// 获取doc
		Jsoupcookie jsoupcookie = new Jsoupcookie(
				"http://202.207.247.44:8065/gradeLnAllAction.do?type=ln&oper=sxinfo&lnsxdm=003#qb_003",
				cookie);
		Document doc = jsoupcookie.getDoc();


		if(doc==null||doc.text()==""){
			facj.setId(3);
			facj.setStatus(2);
		}else{
			
		Elements tables = doc.getElementsByAttributeValue("class", "titleTop2");
		Elements trs = tables.get(ad).getElementsByAttributeValue("onmouseout",
				"this.className='even';");
		int l = trs.size();
		for (int i = 0; i < l; i++) {
			Element tr = trs.get(i);
			Elements tds = tr.select("td");
			Elements ps = tr.select("p");

			FacjMsg facjmsg = new FacjMsg(tds.get(0).text(), tds.get(1).text(),
					tds.get(2).text(), tds.get(3).text(), tds.get(4).text(),
					tds.get(5).text(), ps.get(0).text(), ps.get(1).text());
			list.add(facjmsg);
		}
		if (list != null) {
			facj.setId(3);
			facj.setStatus(3);
			facj.setFacjMsgs(list);
		}
		}
		return facj;

	}

	public MessageFacj jg(String cookie, int ad) {

		List<FacjMsg> list = new ArrayList<FacjMsg>();
		MessageFacj facj = new MessageFacj();
		// 获取doc
		Jsoupcookie jsoupcookie = new Jsoupcookie(
				"http://202.207.247.44:8065/gradeLnAllAction.do?type=ln&oper=bjg",
				cookie);
		Document doc = jsoupcookie.getDoc();


		if(doc==null||doc.text()==""){
			facj.setId(3);
			facj.setStatus(2);
		}else{
			
		Elements tables = doc.getElementsByAttributeValue("class", "titleTop2");
		Elements trs = tables.get(ad).getElementsByAttributeValue("onmouseout",
				"this.className='even';");
		int l = trs.size();
		for (int i = 0; i < l; i++) {
			Element tr = trs.get(i);
			Elements tds = tr.select("td");
			Elements ps = tr.select("p");

			FacjMsg facjmsg = new FacjMsg(tds.get(0).text(), tds.get(1).text(),
					tds.get(2).text(), tds.get(3).text(), tds.get(4).text(),
					tds.get(5).text(), ps.get(0).text(), tds.get(7).text());
			list.add(facjmsg);
		}
		if (list != null) {
			facj.setId(3);
			facj.setStatus(3);
			facj.setFacjMsgs(list);
		}
		}
		return facj;

	}

	public MessageFacj bxqcj(String cookie) {

		List<FacjMsg> list = new ArrayList<FacjMsg>();
		MessageFacj facj = new MessageFacj();
		// 获取doc

		Jsoupcookie jsoupcookie = new Jsoupcookie(
				"http://202.207.247.44:8065/bxqcjcxAction.do", cookie);
		Document doc = jsoupcookie.getDoc();


		if(doc==null||doc.text()==""){
			facj.setId(3);
			facj.setStatus(2);
		}else{
			
		Elements tables = doc.getElementsByAttributeValue("class", "titleTop2");
		Elements trs = tables.get(0).getElementsByAttributeValue("onmouseout",
				"this.className='even';");
		int l = trs.size();
		for (int i = 0; i < l; i++) {
			Element tr = trs.get(i);
			Elements tds = tr.select("td");
			Elements ps = tr.select("p");
			String fs = "未录入";
			if (ps.size() > 0) {
				fs = ps.get(0).text();
			}
			FacjMsg facjmsg = new FacjMsg(tds.get(0).text(), tds.get(1).text(),
					tds.get(2).text(), tds.get(3).text(), tds.get(4).text(),
					tds.get(5).text(), fs, "");
			list.add(facjmsg);
		}
		if (list != null) {
			facj.setId(3);
			facj.setStatus(3);
			facj.setFacjMsgs(list);
		}
		}
		return facj;

	}

	public MessageFacj zzsj(String cookie) {

		List<FacjMsg> list = new ArrayList<FacjMsg>();
		MessageFacj facj = new MessageFacj();
		// 获取doc

		Jsoupcookie jsoupcookie = new Jsoupcookie(
				"http://202.207.247.44:8065/xszzsjcjbAction.do?oper=viewByStudent&pageNum=1",
				cookie);
		Document doc = jsoupcookie.getDoc();

		if(doc==null||doc.text()==""){
			facj.setId(3);
			facj.setStatus(2);
		}else{
			
		System.out.println(doc.text().indexOf("非成绩录入时间!"));
		if (doc.text().indexOf("非成绩录入时间!") != -1) {
			facj.setId(3);
			facj.setStatus(2);
		} else {
			Elements tables = doc.getElementsByAttributeValue("class",
					"titleTop2");
			Elements trs = tables.get(0).getElementsByAttributeValue(
					"onmouseout", "this.className='even';");
			int l = trs.size();
			for (int i = 0; i < l; i++) {
				Element tr = trs.get(i);
				Elements tds = tr.select("td");
				Elements ps = tr.select("p");

				FacjMsg facjmsg = new FacjMsg(tds.get(0).text(), tds.get(1)
						.text(), tds.get(2).text(), tds.get(3).text(), tds.get(
						4).text(), tds.get(5).text(), ps.get(0).text(), ps.get(
						1).text());
				list.add(facjmsg);
			}
			if (list != null) {
				facj.setId(3);
				facj.setStatus(3);
				facj.setFacjMsgs(list);
			}
		}
		}
		return facj;

	}
}
