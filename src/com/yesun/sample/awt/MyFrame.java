package com.yesun.sample.awt;

import java.awt.Color;
import java.awt.Frame;

public class MyFrame extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5021253851547781212L;

	/**
	 * Description:
	 * @param args
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN Oct 31, 2013 12:11:12 PM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {
		MyFrame frame = new MyFrame("hello");
		frame.setSize(500, 500);
		frame.setBackground(Color.red);
		frame.setVisible(true);
	}
	
	public MyFrame(String name) {
		super(name);
	}

}
