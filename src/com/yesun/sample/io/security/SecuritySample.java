package com.yesun.sample.io.security;

import java.io.File;
import java.io.FilePermission;
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
	
	static String path = "F:/project/study/sample/bin/";
	
	public static void makeFile(){
		//以下代码会出现权限错误
//		File fs = new File(path + "ab.txt");
//		try {
//			fs.createNewFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		//可以避免权限检查
		AccessController.doPrivileged(new PrivilegedAction(){

			@Override
			public Object run() {

//				File fs = new File(path + "a.txt");
//				try {
//					fs.createNewFile();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				
//				mkdir();
				final FilePermission filePerm = new FilePermission("F:/project/study/sample/bin/a.txt", "read");
				System.getSecurityManager().checkPermission(filePerm);
				
				FileUtils.mkdir(path + "a.txt");
				return null;
			}
			
		});
		
	}
	
	
	public static void mkdir(){
		File fs = new File(path + "a.txt");
		try {
			fs.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
