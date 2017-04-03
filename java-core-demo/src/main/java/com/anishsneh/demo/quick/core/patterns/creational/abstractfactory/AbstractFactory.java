package com.anishsneh.demo.quick.core.patterns.creational.abstractfactory;

public class AbstractFactory {

	public SpeciesFactory getSpeciesFactory(final String type) {
		if ("mammal".equals(type)) {
			return new MammalFactory();
		} else {
			return new ReptileFactory();
		}
	}

}
