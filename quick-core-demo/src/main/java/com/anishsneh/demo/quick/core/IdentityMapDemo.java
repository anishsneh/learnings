package com.anishsneh.demo.quick.core;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/*
 * System.identityHashCode(x) is used, instead of x.hashCode() for locating bucket
 * 
 * IdentityHashMap use equality operator "=="  to compare keys and values in Java which makes it faster compare to HashMap and suitable where 
 * you need reference equality check and instead of logical equality.
 * 
 * It is faster than HashMap (comparing only for reference based keys)
 * 
 * IdentityHashMap violates Map interface general contract (which mandates the use of the equals method when comparing objects) and 
 * should only be used when reference equality make sense
 * 
 */
public class IdentityMapDemo {

	public static void main(final String[] args) {
		final Map identityMap = new IdentityHashMap();
		final Map hashMap = new HashMap();
		identityMap.put("a", 1);
		identityMap.put(new String("a"), 2);
		identityMap.put("a", 3);

		hashMap.put("a", 1);
		hashMap.put(new String("a"), 2);
		hashMap.put("a", 3);

		System.out.println("Identity Map KeySet Size: " + identityMap.keySet().size());
		System.out.println("Hash Map KeySet Size: " + hashMap.keySet().size());
	}
}
