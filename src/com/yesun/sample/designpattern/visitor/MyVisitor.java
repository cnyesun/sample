package com.yesun.sample.designpattern.visitor;

public class MyVisitor implements IVisitor {

	@Override
	public void visit(ISubject subject) {
		System.out.println("visit " + subject.getSubject());
	}

}
