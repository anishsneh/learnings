package com.anishsneh.demo.quick.core.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * 
 * It wont allow to remove elements using iterator; need to call coll.remove()
 * to remove elements
 * 
 * Copy of array will be made; and after editing original list will be replaced
 * and is volatile
 * 
 * Iterators returned by ArrayList's iterator and listiterator  methods are fail-fast. CopyOnWriteArrayList uses fail-safe iterator
 *
 */
public class CopyOnWriteArrayListExample {

	public final static void main(final String args[]) {
		System.out.println("######################### TEST noUpdatesReflected_ModificationDuringIteration() #########################");
		try {
			noUpdatesReflected_ModificationDuringIteration();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("######################### TEST normalArrayList_ModificationDuringIteration #########################");
		try {
			normalArrayList_ModificationDuringIteration();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("######################### TEST notSupported_IteratorRemove() #########################");
		try {
			notSupported_IteratorRemove();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public final static void noUpdatesReflected_ModificationDuringIteration() {
		System.out.println("==============================");
		System.out.println("noUpdatesReflected_ModificationDuringIteration");
		System.out.println("==============================");
		CopyOnWriteArrayList list = new CopyOnWriteArrayList();
		list.add("vivek");
		list.add("kumar");
		Iterator i = list.iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
			list.add("abhishek");
		}
		System.out.println("\n\nAfter modification:\n\n");

		Iterator i2 = list.iterator();
		while (i2.hasNext()) {
			System.out.println(i2.next());
		}
	}

	public final static void normalArrayList_ModificationDuringIteration() {
		System.out.println("==============================");
		System.out.println("normalArrayList_ModificationDuringIteration");
		System.out.println("==============================");
		ArrayList list = new ArrayList();
		list.add("vivek");
		list.add("kumar");
		Iterator i = list.iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
			list.add("kumar");
		}
		System.out.println("After modification:");
		Iterator i2 = list.iterator();
		while (i2.hasNext()) {
			System.out.println(i2.next());
		}
	}

	// CopyOnWriteArrayList iterator.remove(), NOT SUPPORTED
	public final static void notSupported_IteratorRemove() {
		System.out.println("==============================");
		System.out.println("notSupported_IteratorRemove");
		System.out.println("==============================");
		CopyOnWriteArrayList list = new CopyOnWriteArrayList();
		list.add("vivek");
		list.add("kumar");
		Iterator i = list.iterator();
		int j = 0;
		while (i.hasNext()) {
			System.out.println(i.next());
			list.add("abhishek");
			System.out.println("*************");
			i.remove();
		}
	}
}
