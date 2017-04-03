package com.anishsneh.demo.quick.core;

import java.io.IOException;

class AirPlane {
	public AirPlane() throws IOException, RuntimeException {
		System.out.println("AirPlane");
	}
}

class AirJet extends AirPlane {
	//NEED TO SUPPLY EXPLCIT CONSTRUCTOR "public AirJet() throws IOException, RuntimeException" 
	//DEFAULT WILL NOT WORK AS SUPER CONSTRUCTOR THROWS EXCEPTIONS
	public AirJet() throws IOException, RuntimeException {
		System.out.println("AirJet");
	}
} 

/**
 * The Class ConstructorExceptions.
 */
public class ConstructorExceptions {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(final String args[]) throws IOException {
		new AirPlane();
	}
}