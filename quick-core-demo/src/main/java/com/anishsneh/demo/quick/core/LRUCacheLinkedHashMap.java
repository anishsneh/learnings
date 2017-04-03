package com.anishsneh.demo.quick.core;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLinkedHashMap {

	public static void main(final String[] args){
		final LRUCache<String, String> cache = LRUCache.newInstance(5);
		cache.put("A", "aaaaa");
		cache.put("B", "bbbbb");
		cache.put("C", "ccccc");
		cache.put("D", "ddddd");
		cache.put("E", "eeeee");
		
		System.out.println(cache);
		
		cache.put("F", "fffff");
		
		System.out.println(cache);
		
	}
	
}

class LRUCache<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = 1L;
	private int size;

    private LRUCache(final int size) {
        super(size, 0.75f, true);
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
        return size() > size;
    }

    public static <K, V> LRUCache<K, V> newInstance(final int size) {
        return new LRUCache<K, V>(size);
    }

}
