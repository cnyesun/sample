package com.yesun.sample.jni;

import java.util.Hashtable;

public class JNISample {
	
	static{
		System.loadLibrary("JNISample");
	}
	
	
	public native Hashtable getNameList();

	
	public static void main(String[] args) {
		JNISample sample = new JNISample();
		Hashtable table = sample.getNameList();
		System.out.println(table.size() + "");
		
	}

}
