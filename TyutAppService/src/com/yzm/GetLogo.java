package com.yzm;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetLogo {
	private String cookieurl;
	private String cookie;
	public GetLogo(String cookieurl,String cookie) {
		this.cookieurl=cookieurl;
		this.cookie=cookie;
	}

	public String getLogo(){
		String yzm="";
		
		HttpClient httpClient = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(cookieurl);
		
		
		httpGet.setHeader("Cookie","JSESSIONID="+cookie);
		
		try {
	        /* 鍙戝嚭HTTP request */  
	        //HttpResponse httpResponse2 = new DefaultHttpClient().execute(httpRequest3);
	    	System.out.println("sdf,dddd");
			HttpResponse httpResponse = httpClient.execute(httpGet);
	        /* 鑻ョ姸鎬佺爜涓�00 ok */  
	        if (httpResponse.getStatusLine().getStatusCode() == 200) {  
	    	
	    	
	        	/*StringBuffer sb = new StringBuffer();  
	            HttpEntity entity = httpResponse.getEntity();  
	            InputStream is = entity.getContent();  
	            BufferedReader br = new BufferedReader(new InputStreamReader(is, "GB2312"));  
	            //鏄鍙栬鏀圭紪鐮佺殑婧愶紝婧愮殑鏍煎紡鏄疓B2312鐨勶紝瀹夋簮鏍煎紡璇昏繘鏉ワ紝鐒跺悗鍐嶅婧愮爜杞崲鎴愭兂瑕佺殑缂栫爜灏辫  
	            String data = "";  
	            while ((data = br.readLine()) != null) {  
	                sb.append(data);  
	            }  
	            result = sb.toString();  //姝ゆ椂result涓氨鏄垜浠垚缁╃殑HTML鐨勬簮浠ｇ爜浜�
	            
	            
	            System.out.println(result);*/
	        	//StringBuffer sb = new StringBuffer();  
	            HttpEntity entity = httpResponse.getEntity();  
	            InputStream inputStream = entity.getContent();
	        	
	            BufferedImage buf=ImageIO.read(inputStream);
	            Test t=new Test();
	            
	            yzm=t.fg(t.twoJpg(buf));
	            System.out.println("sdf,dddd2222");
	            
	            
	            //byte[] data = readInputStream(inputStream);  
	            //new涓�釜鏂囦欢瀵硅薄鐢ㄦ潵淇濆瓨鍥剧墖锛岄粯璁や繚瀛樺綋鍓嶅伐绋嬫牴鐩綍  
	            //File imageFile = new File("F:/img/"+ttt+".jpg");  
	            //鍒涘缓杈撳嚭娴� 
	            //FileOutputStream outStream = new FileOutputStream(imageFile);  
	            //鍐欏叆鏁版嵁
	           /* for(int i=0;i<data.length;i++){
	            	System.out.println(data[i]);
	            }*/
	            /*outStream.write(data); 
	            outStream.flush();
	            //鍏抽棴杈撳嚭娴� 
	            outStream.close(); */ 
	        	
	        	/* List<Cookie>  cookies = ((AbstractHttpClient) httpClient).getCookieStore().getCookies();
				  System.out.println(cookies.get(0).getValue());
				  ckyzm[0]=cookies.get(0).getValue();*/
				 return yzm;
	        	//Log.i("鎴愬姛",cookies.get(0).getValue());
	        	
	        } else {  
	        	System.out.println("杩斿洖"+httpResponse.getStatusLine().getStatusCode());
	        }  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }
		return yzm;
	}
	
	
	public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //鍒涘缓涓�釜Buffer瀛楃涓� 
        byte[] buffer = new byte[1024];  
        //姣忔璇诲彇鐨勫瓧绗︿覆闀垮害锛屽鏋滀负-1锛屼唬琛ㄥ叏閮ㄨ鍙栧畬姣� 
        int len = 0;  
        //浣跨敤涓�釜杈撳叆娴佷粠buffer閲屾妸鏁版嵁璇诲彇鍑烘潵  
        while( (len=inStream.read(buffer)) != -1 ){  
            //鐢ㄨ緭鍑烘祦寰�uffer閲屽啓鍏ユ暟鎹紝涓棿鍙傛暟浠ｈ〃浠庡摢涓綅缃紑濮嬭锛宭en浠ｈ〃璇诲彇鐨勯暱搴� 
            outStream.write(buffer, 0, len); 
            System.out.print("a,");
        }  
        //鍏抽棴杈撳叆娴� 
        inStream.close();  
        //鎶妎utStream閲岀殑鏁版嵁鍐欏叆鍐呭瓨  
        return outStream.toByteArray();  
    }  
}
