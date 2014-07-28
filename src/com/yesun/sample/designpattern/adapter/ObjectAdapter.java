package com.yesun.sample.designpattern.adapter;

/**
 * sample ObjectAdapter.java
 * Description: 对象适配器，内部持有一个待适配的对象的引用
 * 1.0 YESUN Oct 10, 2013 5:02:49 PM Create.
 * ChangeLog:
 */
public class ObjectAdapter  implements UsbPort {
	
	Ps2Port ps2port;
	
	public ObjectAdapter(Ps2Port ps2port) {
		this.ps2port = ps2port;
	}

	@Override
	public void workUsb() {
		//包装了一下，其实还是调用了ps2接口
		ps2port.workPs2();
	}
	

}
