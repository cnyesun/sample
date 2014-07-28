package com.yesun.sample.security;
import java.io.File;
import java.io.IOException;


public class FileUtils {
	
	public static void mkdir(String path){
		File fs = new File(path);
		try {
			fs.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
