package com.yesun.sample.jvm;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("jvm test...");
		
		int i = 0;
		while(true) {

			FileInputStream is = null;
			try {
				is = new FileInputStream("c:\\a.jar");
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				IOUtils.copy(is, bos);
				
				byte[] buffer = bos.toByteArray();
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
					if(is != null)
						is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
		}
	}

}
