package com.yesun.sample.designpattern.builder;


public class CarBuilder implements Builder {
	Car car;
	public CarBuilder() {
		car = new Car();
	}
	
	@Override
	public void buildBody() {
		System.out.println("car body");
	}

	@Override
	public void buildFooter() {
		System.out.println("car footer");
	}

	@Override
	public void buildHeader() {
		System.out.println("car header");
	}

	@Override
	public ICar build() {
		return car;
	}

}
