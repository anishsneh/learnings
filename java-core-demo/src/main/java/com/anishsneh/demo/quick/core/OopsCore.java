package com.anishsneh.demo.quick.core;

public class OopsCore {
	
	public static void main(final String[] args) {
		final A a1 = new A();
		final A a2 = new B();
		final B b1 = new B();
		a1.sayHelloA1(); //static, hence just consider reference NOT contents
		a2.sayHelloA1(); //static, hence just consider reference NOT contents
		b1.sayHelloA1();
	}
}

class A{
	
	public static void sayHelloA1(){
		System.out.println("PARENT sayHelloA1()");
	}
	
	private void sayHelloA2(){
		System.out.println("PARENT sayHelloA2()");
	}
	
}

class B extends A{
	
	public static void sayHelloB1(){
		System.out.println("CHILD sayHelloB1()");	
	}
	
	private void sayHelloB2(){
		System.out.println("CHILD sayHelloB2()");	
	}
	
	public void sayHelloA2(){
		System.out.println("CHILD sayHelloA2()");	
	}
	
	public static void sayHelloA1(){
		System.out.println("CHILD sayHelloA1()");	
	}
}
