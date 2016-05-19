package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import TYUTservice.data.MessageKsgl;
import TYUTservice.data.MessageTyut;

import com.network.Jsoupcookie;

public class KSGLdao {
	public List<MessageTyut> getListKsxx(String cookie) {
		List<MessageTyut> list = new ArrayList<MessageTyut>();
		// http://202.207.247.44:8065/
		// 本学期成绩
		// String url = "http://202.207.247.44:8065/bxqcjcxAction.do";
		// 本学期课表
		Jsoupcookie jsoupcookie = new Jsoupcookie(
				"http://202.207.247.44:8065/kwBmAction.do?oper=getKsList", cookie);
		Document doc = jsoupcookie.getDoc();
		Elements a = doc.select("a");
		int l=a.size();
		for (int i = 0; i < l; i++) {
			// 考試信息
			Element ta = a.get(i);
			// href值
			String sa = ta.attr("href");
			System.out.println("详细地址：" + sa);
			// 考试名称
			//String souce = sa.split("ksmc=")[1];
			String ksmc = "";

			// 考試時間
			Element tab = ta.parent().nextElementSibling();
			String ksks = tab.text();
			String ksjs = tab.nextElementSibling().text();
			System.out.println("开始时间为：" + ksks);
			System.out.println("结束时间为：" + ksjs);
			int bmzt = 0;

			if (doc.text().indexOf("英语四级") != -1) {
				ksmc = "CET4";
			} else if (doc.text().indexOf("英语六级") != -1) {
				ksmc = "CET6";
			}
			System.out.println("考试科目：" + ksmc);

			// System.out.println(doc.text());
			if (doc.getElementsByAttributeValue("src", "/img/icon/add.gif")
					.size() != 0) {
				// 未報
				bmzt = 1;
			} else {
				bmzt = 2;
			}
			System.out.println("報考狀態：" + bmzt);
			// 报名
			// kwBmAction.do?oper=bkbm&sfzdxs=否&ksbh=2015-2016-2-1-02&kch=CET6&kxh=0
			// 删除
			// kwBmAction.do?oper=bkdelete&kch=CET6&ksbh=2015-2016-2-1-02&kxh=0&sfzdxs=null
			list.add(new MessageKsgl(5,3, ksmc, ksks, ksjs, bmzt));

		}

		return list;
	}

	// 报名test通过
	/*public void ksbm(String cookie) {

		String url = "http://202.207.247.44:8065/kwBmAction.do?oper=bkbm&sfzdxs=否&ksbh=2015-2016-2-1-02&kch=CET6&kxh=0";
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
	}*/
	//删除test通过
	/*public void scks(String cookie){
		
		String url = "http://202.207.247.44:8065/kwBmAction.do?oper=bkdelete&kch=CET6&ksbh=2015-2016-2-1-02&kxh=0&sfzdxs=null";
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
	}*/
}
