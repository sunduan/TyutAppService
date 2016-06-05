package com.action;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
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

import com.dao.KCCXdao;
import com.network.Jsoupcookie;


public class Mate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * // TODO Auto-generated method stub 正则匹配 String start="共"; String
		 * end="项"; String
		 * te="gradeLnAllAction.do?type=ln&oper=lnfaqkxx&fajhh=2961"; Pattern
		 * pattern = Pattern.compile(
		 * "gradeLnAllAction\\.do\\?type\\=ln\\&oper\\=lnfaqkxx\\&fajhh\\=([\\d]*)"
		 * );
		 * 
		 * Matcher matcher = pattern.matcher(te); if (matcher.find()){
		 * System.out.println(matcher.group()); }else{
		 * 
		 * System.out.println("not found");
		 * 
		 * }
		 */
		KCCXdao mate=new KCCXdao();
		mate.getBxqkc("bcgYhKYoG9d0xdH1prEuv","1", "0", "形势与政策", "", "", "", "", "", "");

	}

	/*public void ydxx() {
		Jsoupcookie jsoupcookie = new Jsoupcookie(
				"http://202.207.247.49/xjInfoAction.do?oper=xjxx",
				"aeg4yhe6jVvsYgCCREAuv");
		Document document = jsoupcookie.getDoc();

		Elements tables = document.getElementsByAttributeValue("id", "tblView");
		Elements tds = tables.get(0).select("td");
		System.out.println("学号" + tds.get(1).text());
	}*/

	public MessageKccx getBxqkc(String actionType, String pageNumber, String kcm,
			String jsm, String xsjc, String skjc, String xaqh, String jxlh,
			String jash) {
		List<KccxMsg> list = new ArrayList<KccxMsg>();
		MessageKccx kccx = new MessageKccx();

		// 課程查詢
		List<String> key = new ArrayList<String>();
		List<String> value = new ArrayList<String>();

		value.add("showColumn");
		value.add("showColumn");
		value.add("showColumn");
		value.add("showColumn");
		value.add("showColumn");
		value.add("showColumn");
		value.add("showColumn");
		value.add("showColumn");
		value.add("showColumn");
		
		
		key.add("kcm#¿Î³ÌÃû");
		key.add("xf#Ñ§·Ö");
		key.add("skjs#½ÌÊ¦");
		key.add("zcsm#ÖÜ´Î");
		key.add("skxq#ÐÇÆÚ");
		key.add("skjc#½Ú´Î");
		key.add("xqm#Ð£Çø");
		key.add("jxlm#½ÌÑ§Â¥");
		key.add("jasm#½ÌÊÒ");

		Map<String, String> map = new HashMap<String, String>();

		map.put("org.apache.struts.taglib.html.TOKEN",
				"9b21211bd13059e2dadfe6357e7a6ecf");
		map.put("kch", "");
		// 课程名 汉字
		map.put("kcm", kcm);
		// 教师名 汉字
		map.put("jsm", jsm);
		// 系所 汉字
		map.put("xsjc", xsjc);
		// 上课节次 01-14
		map.put("skjc", skjc);
		// 校区 int
		map.put("xaqh", xaqh);
		// 教学楼 编号
		map.put("jxlh", jxlh);
		// 教室 编号
		map.put("jash", jash);
		map.put("pageSize", "20");
		/*
		 * map.put("showColumn", "kcm#¿Î³ÌÃû");
		 * 
		 * map.put("showColumn", "xf#Ñ§·Ö"); map.put("showColumn", "skjs#½ÌÊ¦");
		 * map.put("showColumn", "skjc#½Ú´Î"); map.put("showColumn",
		 * "xqm#Ð£Çø"); map.put("showColumn", "jxlm#½ÌÑ§Â¥");
		 * map.put("showColumn", "jasm#½ÌÊÒ");
		 */

		map.put("pageNumber", pageNumber);
		map.put("actionType", actionType);
		// System.out.println(map.get("showColumn"));

		Jsoupcookie jsoupcookie = new Jsoupcookie(
				"http://202.207.247.44:8065/courseSearchAction.do",
				"bicexXKuOOmVZ6xdp0yuv", value, key, map);
		// System.out.println(.text());
		Document doc = jsoupcookie.postDoc(5000);
		doc.charset(Charset.forName("GBK"));
		//Element element=null;
		if (doc.text().equals("")) {
			kccx.setId(3);
			kccx.setStatus(2);

		} else {
			/*try {
				element=(Element) doc.html(new String(doc.html().getBytes("utf-8"),"gbk"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
			String start = "共";
			String end = "页";
			String te = doc.text();
			Pattern pattern = Pattern.compile("共([\\d]*)页");

			Matcher matcher = pattern.matcher(te);
			if (matcher.find()) {
				System.out.println(matcher.group());
				System.out.println(doc.text());
			} else {

				System.out.println("not found" + doc.text());
			}
			Elements trs = doc.getElementsByAttributeValue("onmouseout",
					"this.className='even';");
			int l = trs.size();
			for (int i = 0; i < l; i++) {
				KccxMsg kccxMsg = new KccxMsg();
				Elements tds = trs.get(i).select("td");
				kccxMsg = new KccxMsg(tds.get(0).text(), tds.get(1).text(), tds
						.get(2).text(), "周" + tds.get(4).text() + " "
						+ tds.get(5).text() + "节", tds.get(6).text()
						+ tds.get(7).text() + tds.get(8).text(), tds.get(3)
						.text(), matcher.group().replace(start, "")
						.replace(end, ""));
			}
			if (list != null) {
				kccx.setId(3);
				kccx.setStatus(3);
				kccx.setKccxMsg(list);
			}
		}
		return kccx;
	}

	public void getGrxx() {
		// 個人信息
		Jsoupcookie jsoupcookie = new Jsoupcookie(
				"http://202.207.247.49/userInfo.jsp", "gebecSMxeQea2-gMHdotv");
		// doc
		Document document = jsoupcookie.getDoc();

		Elements tds = document.getElementsByAttributeValue("class",
				"fieldName");
		String td = document.getElementsByAttributeValue("valign", "top")
				.select("tbody").select("tr").get(0).child(5).text();
		System.out.println(tds.get(0).nextElementSibling().text()
				+ ","
				+ td
				+ ","
				+ document.getElementsByAttributeValue("name", "dh").get(0)
						.attr("value")
				+ ","
				+ document.getElementsByAttributeValue("name", "txdz").get(0)
						.attr("value")
				+ ","
				+ document.getElementsByAttributeValue("name", "email").get(0)
						.attr("value"));

	}
}
