package com.yesun.sample.rmi;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;

public class RmiHelloClient {
	
	public static void main(String[] args){
		
		if(System.getSecurityManager()==null)
		{
//		   System.setSecurityManager(new RMISecurityManager());
		}
		try{
			IRmiRemote c1=(IRmiRemote)Naming.lookup("rmi://192.168.3.240:8888/hello");
			System.out.println(c1.CallRemote("Everyone"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


		
	}

}
