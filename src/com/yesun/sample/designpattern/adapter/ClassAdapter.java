package com.yesun.sample.designpattern.adapter;

public class ClassAdapter extends Ps2PortMachine implements UsbPort {

	@Override
	public void workUsb() {

		//调用super的workPs2()方法，达到适配的效果
		workPs2();
		
	}

}
