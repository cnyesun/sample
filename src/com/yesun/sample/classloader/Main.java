package com.yesun.sample.classloader;

public class Main {

	public static void main(String[] args) {

		Parent parent = new Parent();
		parent.print();
		Child child = new Child();
		child.print();

	}

}
