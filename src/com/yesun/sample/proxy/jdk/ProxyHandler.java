package com.yesun.sample.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler  {

	private Object target;
	public ProxyHandler(Object target){
		this.target = target;		
	}
		
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("proxy : invoke " + method.getName());
		Object returnValue = method.invoke(target, args);	
		System.out.println("proxy : invoke " + method.getName());		
		return null;
	}
}
