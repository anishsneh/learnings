package com.anishsneh.demo.quick.core.patterns.structural.flyweight;

public class FlyweightAdder implements Flyweight {

	String operation;

	public FlyweightAdder() {
		operation = "adding";
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doMath(final int a, final int b) {
		System.out.println(operation + " " + a + " and " + b + ": " + (a + b));
	}

}
