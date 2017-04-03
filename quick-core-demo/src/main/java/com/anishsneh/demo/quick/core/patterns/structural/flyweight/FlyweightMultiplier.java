package com.anishsneh.demo.quick.core.patterns.structural.flyweight;

public class FlyweightMultiplier implements Flyweight {

	String operation;

	public FlyweightMultiplier() {
		operation = "multiplying";
		try {
			Thread.sleep(3000);
		} 
		catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doMath(final int a, final int b) {
		System.out.println(operation + " " + a + " and " + b + ": " + (a * b));
	}

}
