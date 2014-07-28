package com.yesun.sample.designpattern.adapter;

public class Main {

	/**
	 * Description:
	 * @param args
	 * 1.0 YESUN Oct 10, 2013 5:04:34 PM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {

		//对象适配
		ObjectAdapter adapter = new ObjectAdapter(new Ps2PortMachine());
		adapter.workUsb();
		
		
		//类的适配
		ClassAdapter classAdapter = new ClassAdapter();
		classAdapter.workUsb();
	}

}
