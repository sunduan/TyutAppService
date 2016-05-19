package com.action;

import java.util.HashMap;
import java.util.Map;

import com.network.Jsoupcookie;



public class Mate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*// TODO Auto-generated method stub
		String start="共";
		String end="项";
		String te="gradeLnAllAction.do?type=ln&oper=lnfaqkxx&fajhh=2961";
		Pattern pattern = Pattern.compile("gradeLnAllAction\\.do\\?type\\=ln\\&oper\\=lnfaqkxx\\&fajhh\\=([\\d]*)");

		Matcher matcher = pattern.matcher(te);
		if (matcher.find()){
			System.out.println(matcher.group());
		}else{

			System.out.println("not found");

		}*/
		Map<String, String> map = new HashMap<String, String>();
		map.put("org.apache.struts.taglib.html.TOKEN","2484496cd3d6201dcc8b67fc42d124cb");
		map.put("kch", "");
		map.put("kcm", "");
		map.put("jsm", "");
		map.put("xsjc", "");
		map.put("skjc", "");
		map.put("xaqh", "");
		map.put("jxlh", "");
		map.put("jash", "");
		map.put("pageSize", "20");
		map.put("showColumn", "kkxsjc#¿ª¿ÎÏµ");
		map.put("showColumn", "kcm#¿Î³ÌÃû");
		map.put("showColumn", "xf#Ñ§·Ö");
		map.put("showColumn", "skjs#½ÌÊ¦");
		map.put("showColumn", "");
		map.put("showColumn", "");
		map.put("showColumn", "");
		
		map.put("pageNumber", "0");
		map.put("actionType", "1");
		
		
		Jsoupcookie jsoupcookie=new Jsoupcookie("http://202.207.247.44:8065/courseSearchAction.do", "bchaWyuIxRxfw4sW8Cbtv", map);
		System.out.println(jsoupcookie.postDoc().text());
	}

}
