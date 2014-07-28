package com.yesun.sample.security;

import java.io.File;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * @author yesun
 * 默认java本地程序是没有启动SecurityManager，可以手动设置SecurityManager
 * 默认网络java程序如applet，是有启动了安全管理的
 * 手动设置SecurityManager，也可以自己重写SecurityManager，以便实现另类的安全管理
 * 
 * 
 * 
 *
 */
public class SecuritySample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.setSecurityManager(new SecurityManager());

		SecuritySample.makeFile();
	}
	
	static String path = "F:/";
	
	public static void makeFile(){
		//以下代码会出现权限错误
//		File fs = new File("c:\\a.txt");
//		try {
//			fs.createNewFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		AccessController.doPrivileged(new PrivilegedAction(){

			@Override
			public Object run() {

				File fs = new File(path + "a.txt");
				try {
					fs.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return null;
			}
			
		});
		
	}

}
