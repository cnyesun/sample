package com.yesun.sample.designpattern.builder;

public class Director {
	
	Builder builder;
	
	public Director(Builder builder) {
		this.builder = builder;
	}
	
	
	public ICar build(){
		
		//构建过程，步骤，都可以在这里修改，其实还可以再向外提（在Client类中定义构建过程）
		this.builder.buildBody();		
		this.builder.buildHeader();		
		this.builder.buildFooter();
		return this.builder.build();
	}

}
