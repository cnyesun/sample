package com.yesun.sample.jmx;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class JmxSample {

	public static void main(String[] args) throws MalformedObjectNameException, NullPointerException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
		
		MBeanServer server = MBeanServerFactory.createMBeanServer();

        ObjectName helloName = new ObjectName("yesun:name=HelloWorld");
        server.registerMBean(new Hello(), helloName);

        ObjectName adapterName = new ObjectName("JmxSample:name=htmladapter,port=8082");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();//在jmxtools.jar包中
        server.registerMBean(adapter, adapterName);
        adapter.start();
        System.out.println("start.....");


	}

}
