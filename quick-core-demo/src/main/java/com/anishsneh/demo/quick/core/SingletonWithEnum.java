package com.anishsneh.demo.quick.core;

/**
 * 
 * Advantage of implementing singleton using ENUM is JVM itself takes care of serialization
 * No need of explicitly taking care of readResolve() or writeReplace()
 *
 */
public class SingletonWithEnum {
	public static void main(final String[] agrs) {
		String msg = HelloWorld.INSTANCE.sayHello();
		System.out.println(msg);
	}
}

enum HelloWorld {

	INSTANCE;
	
	private String message = "Hello World: ";
	
	public String sayHello() {
		return INSTANCE.message + System.currentTimeMillis();
	}
}
