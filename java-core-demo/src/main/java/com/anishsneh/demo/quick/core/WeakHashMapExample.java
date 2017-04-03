package com.anishsneh.demo.quick.core;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 
 * WeakHashMap is an implementation of the Map interface that stores only weak references to its keys.
 * 
 * When there are one or more reference to an object it will not be garbage collected in Java. 
 * But this rule depends on what type of reference it is. 
 * If an object has only weak reference (means it is not strongly referenced in the execution anymore) associated with other objects, 
 * then it is a valid candidate for garbage collection.
 *
 */
public class WeakHashMapExample {

	public static void main(final String[] args) {
		
		//WEAK HASHMAP
		System.out.println("WEAK HASHMAP");
		Key k1 = new Key("Hello");
	    Key k2 = new Key("World");
	    Key k3 = new Key("Java");
	    Key k4 = new Key("Programming");
	    Map<Key, String> wm = new WeakHashMap<Key, String>();
	    wm.put(k1, "Hello");
	    wm.put(k2, "World");
	    wm.put(k3, "Java");
	    wm.put(k4, "Programming");
	    k1=null;
	    k2=null;
	    k3=null;
	    k4=null;
	    System.gc();
	    System.out.println("Weak Hash Map :"+wm.toString());
	    
	    //NORMAL HASHMAP
	    System.out.println("NORMAL HASHMAP");
	    Key hk1 = new Key("Hello");
	    Key hk2 = new Key("World");
	    Key hk3 = new Key("Java");
	    Key hk4 = new Key("Programming");
	    Map<Key, String> hm = new HashMap<Key, String>();
	    hm.put(hk1, "Hello");
	    hm.put(hk2, "World");
	    hm.put(hk3, "Java");
	    hm.put(hk4, "Programming");
	    hk1=null;
	    hk2=null;
	    hk3=null;
	    hk4=null;
	    System.gc();
	    System.out.println("Hash Map :"+hm.toString());
	}
}

class Key {
	private String key;
	public Key(String key) {
		this.key = key;
	}
	@Override
	public boolean equals(Object obj) {
	    return this.key.equals((String)obj);
	}
	@Override
	public int hashCode() {
	    return key.hashCode();
	}
	@Override
	public String toString() {
	    return key;
	}
}