package com.anishsneh.demo.quick.core.concurrent;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Dictionary {

	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private final Lock read = readWriteLock.readLock();

	private final Lock write = readWriteLock.writeLock();

	private HashMap<String, String> dictionary = new HashMap<String, String>();

	public void set(String key, String value) {
		write.lock();
		try {
			dictionary.put(key, value);
		} finally {
			write.unlock();
		}
	}

	public String get(String key) {
		read.lock();
		try {
			return dictionary.get(key);
		} finally {
			read.unlock();
		}
	}

	public String[] getKeys() {
		read.lock();
		try {
			String keys[] = new String[dictionary.size()];
			return dictionary.keySet().toArray(keys);
		} finally {
			read.unlock();
		}
	}

	public static void main(final String[] args) {
		Dictionary dictionary = new Dictionary();
		dictionary.set("java", "object oriented");
		dictionary.set("linux", "rulez");
		Writer writer = new Writer(dictionary, "Mr. Writer");
		Reader reader1 = new Reader(dictionary, "Mrs Reader 1");
		Reader reader2 = new Reader(dictionary, "Mrs Reader 2");
		Reader reader3 = new Reader(dictionary, "Mrs Reader 3");
		Reader reader4 = new Reader(dictionary, "Mrs Reader 4");
		Reader reader5 = new Reader(dictionary, "Mrs Reader 5");
		writer.start();
		reader1.start();
		reader2.start();
		reader3.start();
		reader4.start();
		reader5.start();
	}
}

class Reader extends Thread {

	private Dictionary dictionary = null;

	public Reader(Dictionary d, String threadName) {
		this.dictionary = d;
		this.setName(threadName);
	}

	private boolean runForestRun = true;

	@Override
	public void run() {
		while (runForestRun) {
			String[] keys = dictionary.getKeys();
			for (String key : keys) {
				// reading from dictionary with READ LOCK
				String value = dictionary.get(key);

				// make what ever you want with the value.
				System.out.println(key + " : " + value);
			}

			// update every seconds
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopReader() {
		this.runForestRun = false;
		this.interrupt();
	}
}

class Writer extends Thread {
	private boolean runForestRun = true;
	private Dictionary dictionary = null;

	public Writer(Dictionary d, String threadName) {
		this.dictionary = d;
		this.setName(threadName);
	}

	@Override
	public void run() {
		while (this.runForestRun) {
			String[] keys = dictionary.getKeys();
			for (String key : keys) {
				String newValue = getNewValueFromDatastore(key);
				// updating dictionary with WRITE LOCK
				dictionary.set(key, newValue);
			}

			// update every seconds
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopWriter() {
		this.runForestRun = false;
		this.interrupt();
	}

	public String getNewValueFromDatastore(String key) {
		// This part is not implemented. Out of scope of this artile
		return "newValue";
	}
}
