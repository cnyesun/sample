package com.yesun.sample.io;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;

import org.junit.Test;

public class IOSample {
	
	
	
	@Test
	public void testDataInputStream() throws FileNotFoundException{
//		
//		FileInputStream fis = new FileInputStream("c:\\a.txt");
//		DataInputStream dis = new DataInputStream(fis);
		
		String tmp = new String("DCM"); 
		execute(tmp);
		System.out.println(tmp);
		
	}
	
	
	public void execute(String tmp) {
		tmp = new String("YESUN");
	}
	

}
