package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import TYUTservice.data.FacjMsg;
import TYUTservice.data.MessageFacj;
import TYUTservice.data.MessageTyut;

public class FACJdao {
	public MessageFacj getListFacj(String cookie,String address) {
		List<FacjMsg> list = new ArrayList<FacjMsg>();
		MessageFacj facj= new MessageFacj();
		// ªÒ»°doc
		String url = "http://202.207.247.44:8065/gradeLnAllAction.do?type=ln&oper=fainfo&fajhh=2514#qb_2514";
		long start = System.currentTimeMillis();
		Document doc = null;
		try {
			// doc = Jsoup.connect(url).get();
			doc = Jsoup.connect(url).cookie("JSESSIONID", cookie).get();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Time is:"
					+ (System.currentTimeMillis() - start) + "ms");
		}
		Elements trs = doc.getElementsByAttributeValue("onmouseout",
				"this.className='even';");
		int l = trs.size();
		for (int i = 0; i < l; i++) {
			Element tr = trs.get(i);
			Elements tds = tr.select("td");
			Elements ps	=tr.select("p");

			
			FacjMsg facjmsg=new FacjMsg(tds.get(0).text(),tds.get(1).text(),
					tds.get(2).text(),tds.get(3).text(),tds.get(4).text(),
					tds.get(5).text(),ps.get(0).text(),ps.get(1).text());
			list.add(facjmsg);
		}
		if(list!=null){
			facj.setId(3);
			facj.setStatus(3);
			facj.setFacjMsgs(list);
		}
		return facj;

	}
}
