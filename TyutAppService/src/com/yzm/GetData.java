package com.yzm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GetData {
	 
	
public static int[][]  getWh(){
		int wh[][]=new int[122][2];
		int i=48;
	for(;i<122;i++){
		String classpath=com.yzm.GetData.class.getResource("").getPath();
		//System.out.println(classpath+"不存在");
		String fpath1[]=classpath.split("WEB-INF/classes/",2);
		//System.out.println(fpath1[0]+"不存在");
		//String fpath[]=fpath1[0].split("/", 2);	
		File file=new File(fpath1[0]+"TYUTjavamodel/"+i+".bmp");
			if(!file.exists()){
			//System.out.println(fpath[0]+"不存在");
				wh[i][0]=0;
				wh[i][1]=0;
			}else{
				//System.out.println("娑撳秴鐡ㄩ崷锟�;
				BufferedImage bufferedImage = null;
				try {
					bufferedImage = ImageIO.read(file);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				wh[i][0]=bufferedImage.getWidth();
				wh[i][1]=bufferedImage.getHeight();
			
			}
		
	}
	return wh;
	
}
}
