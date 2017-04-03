package com.anishsneh.demo.quick.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationParentChild {

	public static void main(final String[] args) throws Exception {
		MySerializable ms = new MySerializable();

		// writing object to byte[]
		System.out.println("writing ms");
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(ms);
		oos.close();
		byte[] objectInBinaryForm = baos.toByteArray();

		// reading object from byte[]
		System.out.println("reading ms2");
		final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(objectInBinaryForm));
		final MySerializable ms2 = (MySerializable) ois.readObject();
		System.out.println("ms == ms2 = " + (ms == ms2));
		System.out.println("ms = " + ms);
		System.out.println("ms2 = " + ms2);
	}

}

class NotSerializable {
	
	public NotSerializable() {
		System.out.println("NotSerializable constructor called");
	}
	
	/*public NotSerializable(int useless) {
		System.out.println("NotSerializable constructor called");
	}*/
}

class MySerializable extends NotSerializable implements Serializable {

	private static final long serialVersionUID = 1L;

	public MySerializable() {
		//super(1);
		System.out.println("MySerializable constructor called");
	}
}