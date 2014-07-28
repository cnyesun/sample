package com.yesun.sample.parameters;
import org.junit.Test;



public class ParametersSample {
	
	
	/**
	 * 测试传递不定个数参数方法
	 * @param name
	 * @param messages
	 */
	public void create(String name, String... messages) {
		if (name.length() == 0)
			throw new IllegalArgumentException("name must have non-zero length");
		//String...被转化为String[]
		System.out.println(messages.length);
		
	}
	
	
	@Test
	public void testCreate(){
		
		create("yesun", "001", "002", "003", "004", "005");
		
		
	}
	
}
