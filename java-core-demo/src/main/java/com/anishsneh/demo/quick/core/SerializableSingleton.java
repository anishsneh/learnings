package com.anishsneh.demo.quick.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableSingleton {

	private Singleton sone = null, stwo = null;

	public SerializableSingleton() {
		sone = Singleton.INSTANCE;
		stwo = Singleton.INSTANCE;
	}

	public static void main(final String[] args) {
		final SerializableSingleton ss = new SerializableSingleton();
		ss.testSerialize();
		ss.testUnique();
	}

	public void testSerialize() {
		System.out.println("testing singleton serialization...");
		writeSingleton();
		final Singleton s1 = readSingleton();
		final Singleton s2 = readSingleton();
		if (s1 == s2)
			System.out.println("WON 1...");
		else
			System.out.println("LOST 1...");
	}

	private void writeSingleton() {
		try {
			final FileOutputStream fos = new FileOutputStream("serializedSingleton");
			final ObjectOutputStream oos = new ObjectOutputStream(fos);
			final Singleton s = Singleton.INSTANCE;
			oos.writeObject(Singleton.INSTANCE);
			oos.flush();
		} 
		catch (final NotSerializableException se) {
			System.out.println("Not Serializable Exception: " + se.getMessage());
		} 
		catch (final IOException iox) {
			System.out.println("IO Exception: " + iox.getMessage());
		}
	}

	private Singleton readSingleton() {
		Singleton s = null;
		try {
			final FileInputStream fis = new FileInputStream("serializedSingleton");
			final ObjectInputStream ois = new ObjectInputStream(fis);
			s = (Singleton) ois.readObject();
		} 
		catch (final ClassNotFoundException cnf) {
			System.out.println("Class Not Found Exception: " + cnf.getMessage());
		} 
		catch (final NotSerializableException se) {
			System.out.println("Not Serializable Exception: " + se.getMessage());
		} 
		catch (final IOException iox) {
			System.out.println("IO Exception: " + iox.getMessage());
		}
		return s;
	}

	public void testUnique() {
		System.out.println("testing singleton uniqueness...");
		final Singleton another = new Singleton();
		System.out.println("checking singletons for equality");
		if (sone == stwo)
			System.out.println("WON 2...");
		else
			System.out.println("LOST 2...");
	}
}

class Singleton implements Serializable {
	public static Singleton INSTANCE = new Singleton();

	protected Singleton() {
		// Exists only to thwart instantiation.
	}

	// MUST PROVIDE IMPLEMENTAION FOR readResolve() TO GET SINGLETON OBJECT
	//readResolve is used for replacing the object read from the stream.
	//readResolve is called after readObject has returned
	//writeReplace is called before writeObject
	//readResolve() can be used to change the data that is serialized through readObject() method. 
	private Object readResolve() {
		return INSTANCE;
	}
}
