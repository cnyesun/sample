package com.yesun.sample.designpattern.visitor;

public class MySubject implements ISubject {

	@Override
	public String getSubject() {

		return "code task";
		
	}

	@Override
	public void accept(IVisitor visitor) {

		visitor.visit(this);
		
	}

}
