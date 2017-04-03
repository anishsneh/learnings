package com.anishsneh.demo.quick.core.patterns.creational.abstractfactory;

public class MammalFactory extends SpeciesFactory {

	@Override
	public Animal getAnimal(final String type) {
		if ("dog".equals(type)) {
			return new Dog();
		} else {
			return new Cat();
		}
	}

}
