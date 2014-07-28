package com.yesun.sample.rmi;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;

public class RmiHelloServer {
	
	public static void main(String[] args){
		
		if(System.getSecurityManager()==null)
		{
//		   System.setSecurityManager(new RMISecurityManager());
		}
		try{
			//创建远程对象
			RmiRemoteHello hello = new RmiRemoteHello();
			//启动注册表
			LocateRegistry.createRegistry(8888);
			//将名称绑定到对象
			Naming.bind("//192.168.3.240:8888/hello", hello);
			
			System.out.println("RMI服务器正在运行。。。。。。");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


		
	}

}
