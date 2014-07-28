package com.yesun.sample.ehcache;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Main {

	public static void main(String[] args) {

		CacheManager manager = CacheManager.create();
		Cache cache = manager.getCache("Test");
		cache.put(new Element("key", "value"));

		Element elem = cache.get("key");
		String value = (String) elem.getObjectValue();
		System.out.println(value);
		
		List<String> list = new ArrayList<String>();
		list.add("yesun");
		list.add("dcm");
		
		cache.put(new Element("list", list));

		elem = cache.get("list");
		List<String> list2 =  (List<String>) elem.getObjectValue();
		System.out.println(list2.size());
	}

}
