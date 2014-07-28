package com.yesun.sample.jmx;

import java.io.IOException;
import java.lang.management.MemoryUsage;
import java.util.Iterator;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JmxManager {
	
	public static void main(String[] args) {
		
		try {
			JmxManager.initConnection();
//			JmxManager.getAllObjectName();
			JmxManager.getHeapStack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static JMXConnector connector;
	private static MBeanServerConnection mbsc;
	
	public static void getAllObjectName() throws InstanceNotFoundException, IntrospectionException, ReflectionException, IOException{
        Set MBeanset = mbsc.queryMBeans(null, null);
		Iterator MBeansetIterator = MBeanset.iterator();
		while (MBeansetIterator.hasNext()) {
			ObjectInstance objectInstance = (ObjectInstance) MBeansetIterator.next();
			ObjectName objectName = objectInstance.getObjectName();
			MBeanInfo objectInfo = mbsc.getMBeanInfo(objectName);
			System.out.print("ObjectName:" + objectName.getCanonicalName()+ ".");
			System.out.print("mehtodName:");
			for (int i = 0; i < objectInfo.getAttributes().length; i++) {
				System.out.println(objectInfo.getAttributes()[i].getName() + ",");
			}
		}
	}
	
	
	public static void getHeapStack() throws MalformedObjectNameException, NullPointerException, AttributeNotFoundException, InstanceNotFoundException, MBeanException, ReflectionException, IOException{
			ObjectName heapObjName = new ObjectName("java.lang:type=Memory");
			
			//堆内存
			MemoryUsage heapMemoryUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(heapObjName,"HeapMemoryUsage"));
			long commitMemory = heapMemoryUsage.getCommitted();// 堆当前分配
			long usedMemory = heapMemoryUsage.getUsed();
			System.out.print("堆内存总量:"+heapMemoryUsage.getMax()/1024+"KB,当前分配量:"+commitMemory/1024+"KB,当前使用率:"+usedMemory/1024+"KB,");
			System.out.println("堆内存使用率:" + (int) usedMemory * 100/ commitMemory + "%");// 堆使用率
			
	        //栈内存
	  		MemoryUsage nonheapMemoryUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(heapObjName,"NonHeapMemoryUsage"));
	  		long noncommitMemory = nonheapMemoryUsage.getCommitted();
			long nonusedMemory = heapMemoryUsage.getUsed();
			
			System.out.println("栈内存使用率:" + (int) nonusedMemory * 100/ noncommitMemory + "%");
	  		
			//PermGen内存
	  		ObjectName permObjName = new ObjectName("java.lang:type=MemoryPool,name=Perm Gen");
		
			MemoryUsage permGenUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(permObjName,"Usage"));
			long committed = permGenUsage.getCommitted();// 持久堆大小
			long used = heapMemoryUsage.getUsed();//  
			System.out.println("perm gen:" + (int) used * 100 / committed+ "%");// 持久堆使用率
	   
	}
	
	
    private static void initConnection() throws Exception {
		//用户名、密码
//      Map<String, String[]> map = new HashMap<String, String[]>();
//		map.put("jmx.remote.credentials", new String[] { "monitorRole","QED" });
		String jmxURL = "service:jmx:rmi:///jndi/rmi://192.168.4.240:7001/jmxrmi";
		
		JMXServiceURL serviceURL = new JMXServiceURL(jmxURL);
		connector = JMXConnectorFactory.connect(serviceURL);//, map
		mbsc = connector.getMBeanServerConnection();
	}


   
}
