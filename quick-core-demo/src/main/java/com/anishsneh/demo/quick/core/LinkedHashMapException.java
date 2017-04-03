package com.anishsneh.demo.quick.core;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class LinkedHashMapException {

	public static void main(final String[] args) {

		try {
			// INSERTION ORDER
			final HashMap<String, String> lhm1 = new LinkedHashMap<String, String>(16, 0.75f, false);
			lhm1.put("aaa", "AAA");
			lhm1.put("bbb", "BBB");
			lhm1.put("ccc", "CCC");
			final Set<String> keySet1 = lhm1.keySet();
			for (final String key1 : keySet1) {
				System.out.println("INSERTION ORDER: " + lhm1.get(key1));
			}
		}
		catch (final Exception e) {
			e.printStackTrace();
		}

		try {
			// ACCESS ORDER
			final HashMap<String, String> lhm2 = new LinkedHashMap<String, String>(16, 0.75f, true);
			lhm2.put("ddd", "DDD");
			lhm2.put("eee", "EEE");
			lhm2.put("fff", "FFF");
			final Set<String> keySet2 = lhm2.keySet();
			for (final String key2 : keySet2) {
				System.out.println("ACCESS ORDER: " + lhm2.get(key2));
			}
		} 
		catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
