package com.anishsneh.demo.quick.core.patterns.creational.factory;

public class FactoryMain {
	public static void main(final String[] args) {
		
		// create a small dog
		Dog dog = DogFactory.getDog("small");
		dog.speak();

		// create a big dog
		dog = DogFactory.getDog("big");
		dog.speak();

		// create a working dog
		dog = DogFactory.getDog("working");
		dog.speak();
	}
}
