package com.yesun.sample.objectpool;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ObjectCachePool<K, V> {
	public static final int FIFO = 1;
	public static final int LRU = 2;
	private static final int DEFAUTLT_SIZE = 10;
	private Map<K, V> cacheObject;

	public ObjectCachePool() {
		this(DEFAUTLT_SIZE);
	}

	public ObjectCachePool(final int size) {
		this(size, FIFO);
	}

	public ObjectCachePool(final int size, final int policy) {
		switch (policy) {
		case FIFO://先进先出策略
			cacheObject = new LinkedHashMap<K, V>(size) {
				@Override
				protected boolean removeEldestEntry(Entry<K, V> eldest) {
					return size() > size;
				}
			};
			break;
		case LRU://最近最少使用策略
			cacheObject = new LinkedHashMap<K, V>(size, 0.75f, true) {
				@Override
				protected boolean removeEldestEntry(Entry<K, V> eldest) {
					return size() > size;
				}

			};
			break;
		}
	}

	public void put(K key, V value) {
		cacheObject.put(key, value);
	}

	public V get(K key) {
		return cacheObject.get(key);
	}

	public void remove(K key) {
		cacheObject.remove(key);
	}

	public void clear() {
		cacheObject.clear();
	}

	public Collection listValue() {
		return cacheObject.values();
	}
	
	
	
	
	
}
