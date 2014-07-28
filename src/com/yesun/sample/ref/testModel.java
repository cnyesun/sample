package com.yesun.sample.ref;

public class testModel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Model model = new Model();
		model.setUsername("dcm");
		model.setPassword("000");
		
		rename(model);
		
		System.out.println(model.getUsername());

	}
	
	public static void rename(Model model){//java中默认就是引用，c#中需要用out或ref
		model.setUsername("yesun");
	}

}
