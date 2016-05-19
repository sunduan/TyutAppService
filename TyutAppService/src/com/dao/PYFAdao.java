package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import TYUTservice.data.MessagePyfa;
import TYUTservice.data.msgdata.PyfaMsg;

import com.network.Jsoupcookie;

public class PYFAdao {
	public MessagePyfa getListPYFdao(String cookie) {

		List<PyfaMsg> list = new ArrayList<PyfaMsg>();
		MessagePyfa pyfa = new MessagePyfa();
		// 获取doc
		Jsoupcookie jsoupcookie = new Jsoupcookie(
				"http://202.207.247.44:8065/gradeLnAllAction.do?type=ln&oper=lnfaqk&flag=zx",
				cookie);
		Document doc = jsoupcookie.getDoc();
		
		//匹配项数
				/*String start="gradeLnAllAction.do?type=ln&oper=lnfaqkxx&fajhh=";
				String end="\"";*/
				String te=doc.getElementsByAttributeValue("width", "38%").html();
				System.out.println(te);
				Pattern pattern = Pattern.compile("fajhh\\=([\\d]*)");
				Matcher matcher = pattern.matcher(te);
				if (matcher.find()){
					System.out.println(matcher.group(0));
				}else{

					System.out.println("not found");
				}
				
		jsoupcookie = new Jsoupcookie(
				"http://202.207.247.44:8065/gradeLnAllAction.do?type=ln&oper=lnfaqkxx&"+matcher.group(),
				cookie);
		doc = jsoupcookie.getDoc();

		System.out.println(doc.text());
		Element table = doc.getElementById("tblView");
		Elements trs = table.select("tr");

		int l = trs.size();
		System.out.print(l);
		for (int i = 0; i < l; i = i + 7) {
			PyfaMsg pyfaMsg = new PyfaMsg();
			if (i == 0) {
				pyfaMsg = new PyfaMsg(trs.get(i).select("td").get(1).text(),
						trs.get(i + 1).select("td").get(1).text(), trs
								.get(i + 2).select("td").get(1).text(), trs
								.get(i + 3).select("td").get(1).text(), trs
								.get(i + 4).select("td").get(1).text(), trs
								.get(i + 5).select("td").get(1).text(), trs
								.get(i + 6).select("td").get(1).text());
			} else {
				String wc="您已达到本学期所要求最低学分";
				int ll=trs.get(i+1).select("td").size();
				System.out.println(ll);
				if(ll==0){
					
				}else{
					wc="您未达到本学期所要求最低学分";
				}
				pyfaMsg = new PyfaMsg(trs.get(i).select("td").select("div")
						.text(), wc, trs.get(i + 2).select("td").get(1).text(), trs
						.get(i + 3).select("td").get(1).text(), trs.get(i + 4)
						.select("td").get(1).text(), trs.get(i + 5)
						.select("td").get(1).text(), trs.get(i + 6)
						.select("td").get(1).text());
			}
			list.add(pyfaMsg);

		}
		if (list != null) {
			pyfa.setId(3);
			pyfa.setStatus(3);
			pyfa.setPyfaMsgs(list);
		}

		return pyfa;
	}
}
