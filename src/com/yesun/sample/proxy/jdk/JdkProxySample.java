package com.yesun.sample.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author yesun
 * JDK 的动态代理只能对实现了接口的目标类进行代理，而不实现接口的类就不能使用 JDK 的动态代理 
 *	CGLIB 是针对类来实现代理，当没有实现接口的类需要代理时就需要通过 CGLIB 来实现代理了，他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现增强，但是因为采用的是继承，所以不能对 finall 类进行继承。 
 *
 */
public class JdkProxySample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		IBook book = new BookServiceImpl();

		
		ClassLoader loader = JdkProxySample.class.getClassLoader();
		Class[] interfaces = {IBook.class};
		InvocationHandler handler = new ProxyHandler(book);
		book = (IBook)Proxy.newProxyInstance(loader, interfaces, handler);
		book.addBook();
		System.out.println("ok");
		

	}

}
