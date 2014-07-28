package com.yesun.sample.designpattern.builder;

public interface Builder {
	
	void buildHeader();
	void buildBody();
	void buildFooter();
	
	ICar build();

}
