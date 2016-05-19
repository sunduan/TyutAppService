package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import TYUTservice.data.MessageKccx;
import TYUTservice.data.msgdata.KccxMsg;

import com.network.Jsoupcookie;

public class KCCXdao {
	public MessageKccx getListKccx(String cookie) {

		List<KccxMsg> list = new ArrayList<KccxMsg>();
		MessageKccx kccx = new MessageKccx();
		// 获取doc
		Jsoupcookie jsoupcookie = new Jsoupcookie(
				"http://202.207.247.44:8065/xkAction.do?actionType=6", cookie);
		Document doc = jsoupcookie.getDoc();

		Elements trs = doc.getElementsByAttributeValue("bgcolor", "#FFFFFF");
		int l = trs.size();
		for (int i = 0; i < l; i++) {
			KccxMsg kccxMsg = new KccxMsg();
			Element tr = trs.get(i);
			Elements tds = tr.select("td");
			if (i == 0 || i == 5 || i == 10) {
				kccxMsg = new KccxMsg(tds.get(2).text(), tds.get(3).text(), tds
						.get(4).text(), tds.get(5).text(), tds.get(6).text(),
						tds.get(7).text(), tds.get(8).text());
				list.add(kccxMsg);
			} else if (i == 4 || i == 9) {

			} else {
				kccxMsg = new KccxMsg(tds.get(1).text(), tds.get(2).text(), tds
						.get(3).text(), tds.get(4).text(), tds.get(5).text(),
						tds.get(6).text(), tds.get(7).text());
				list.add(kccxMsg);
			}
		}
		if (list != null) {
			kccx.setId(3);
			kccx.setStatus(3);
			kccx.setKccxMsgs(list);
		}

		return kccx;
	}

	public MessageKccx getListKccx(String cookie, String kkyx, String kclb,
			String kch, String kcm, String page) {

		List<KccxMsg> list = new ArrayList<KccxMsg>();
		MessageKccx kccx = new MessageKccx();
		Map<String, String> map = new HashMap<String, String>();
		map.put("kkxsh", kkyx);
		map.put("kclbdm", kclb);
		map.put("kch", kch);
		map.put("kcm", kcm);
		map.put("pageSize", "10");
		map.put("page", page);
		map.put("currentPage", "1");
		// 获取doc
		Jsoupcookie jsoupcookie = new Jsoupcookie(
				"http://202.207.247.44:8065/kclbAction.do?oper=kclb", cookie,
				map);
		Document doc = jsoupcookie.postDoc();
		
		//System.out.println(doc.text());
		//匹配项数
		String start="共";
		String end="项";
		String te=doc.text();
		Pattern pattern = Pattern.compile("共([\\d]*)项");

		Matcher matcher = pattern.matcher(te);
		if (matcher.find()){
			System.out.println(matcher.group().replace(start,"").replace(end,""));
		}else{

			System.out.println("not found");
		}
		
		Elements trs = doc.getElementsByAttributeValue("onmouseout",
				"this.className='even';");
		int l = trs.size();
		for (int i = 0; i < l; i++) {
			KccxMsg kccxMsg = new KccxMsg();
			Elements tds = trs.get(i).select("td");
			kccxMsg = new KccxMsg(tds.get(1).text(), tds.get(2).text(), tds
					.get(3).text(), tds.get(4).text(), matcher.group().replace(start,"").replace(end,""),
					"", "");
			list.add(kccxMsg);
		}
		if (list != null) {
			kccx.setId(3);
			kccx.setStatus(3);
			kccx.setKccxMsgs(list);
		}
		return kccx;
	}
}
