package com.yesun.sample.designpattern.visitor;

public interface ISubject {
	
	public void accept(IVisitor visitor);
	
	public String getSubject();

}
