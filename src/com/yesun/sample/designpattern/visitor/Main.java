package com.yesun.sample.designpattern.visitor;

public class Main {
	
	public static void main(String[] args) {
		
		IVisitor visitor = new MyVisitor();
		ISubject subject = new MySubject();
		subject.accept(visitor);
		
	}

}
