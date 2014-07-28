package com.yesun.sample.designpattern.builder;

public class Client {

	/**
	 * Description:
	 * @param args
	 * 1.0 YESUN Oct 10, 2013 4:24:42 PM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {

		CarBuilder builder = new CarBuilder();
		Director director = new Director(builder);
		ICar car = director.build();
		System.out.println(car.toString());
		
		

	}

}
