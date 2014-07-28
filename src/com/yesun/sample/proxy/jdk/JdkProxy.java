package com.yesun.sample.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy extends Proxy implements IBook {

	protected JdkProxy(InvocationHandler h) {
		super(h);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void addBook() {

		Method method;
		try {
			method = IBook.class.getMethod("add");
			h.invoke(this, method, null);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
