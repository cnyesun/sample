package com.yesun.sample.objectpool;

import java.util.LinkedHashMap;
import java.util.Map;

public class CachePoolTest {
	public static void main(String[] args) {
		test3();
	}

	public static void test1() {
		Map<String, String> map = new LinkedHashMap<String, String>(1);
		map.put("a", "a");
		map.put("b", "b");
		map.put("c", "c");
		map.put("d", "d");
		System.out.println(map);
	}

	public static void test2() {
		ObjectCachePool<String, String> pool = new ObjectCachePool<String, String>(2);
		pool.put("a", "a");
		pool.put("b", "b");
		pool.put("c", "c");
		pool.put("d", "d");
		System.out.println(pool.listValue());//先进先出，a、b先进，所以先被remove掉

	}

	public static void test3() {
		ObjectCachePool<String, String> pool = new ObjectCachePool<String, String>(2, ObjectCachePool.LRU);
		pool.put("a", "a");
		pool.put("b", "b");
		pool.put("c", "c");
		System.out.println(pool.get("b"));
		pool.put("d", "d");
		System.out.println(pool.listValue());//最近最少使用策略，所以a和c被删除

	}
}
