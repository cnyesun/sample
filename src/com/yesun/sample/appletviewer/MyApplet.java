package com.yesun.sample.appletviewer;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class MyApplet extends Applet {

	public void init() {
		setBackground(Color.white);
	}

	public void paint(Graphics g) {
		
		g.setColor(Color.red);
		g.drawString("姓名：曹郡益", 50, 50);
		g.setColor(Color.green);
		g.drawString("性别:boy", 50, 100);
		g.setColor(Color.blue);
		g.drawString("家庭住址：CQUPT", 50, 150);
	}

}
