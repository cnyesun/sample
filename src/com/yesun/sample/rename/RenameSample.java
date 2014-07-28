package com.yesun.sample.rename;

import java.io.File;
import java.util.List;

public class RenameSample {
	
	
	private void rename(String path){
		
		File pathFile = new File(path);
		File[] fileArray = pathFile.listFiles();
		int index = 1;
		String filename;
		String extName = "";
		for(File file : fileArray){
			filename = file.getName();
			extName = filename.substring(filename.lastIndexOf("."));
			//02-堆积情感@圣城家园@空の轨迹
			if(filename.contains("-") && filename.contains("@")){
				filename = filename.substring(filename.indexOf("-") + 1, filename.indexOf("@"));
			} 
			file.renameTo(new File(path + filename + extName));
			System.out.println("Rename file:" + filename);
		}
		
	}
	
	
	public static void main(String[] args){

		System.out.println(System.getProperty("user.dir"));
//		RenameSample rename = new RenameSample();
//		rename.rename("F:\\music\\无损音乐\\");
		
	}

}
