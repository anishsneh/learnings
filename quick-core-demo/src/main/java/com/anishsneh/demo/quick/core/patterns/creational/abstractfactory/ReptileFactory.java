package com.anishsneh.demo.quick.core.patterns.creational.abstractfactory;

public class ReptileFactory extends SpeciesFactory {

	@Override
	public Animal getAnimal(final String type) {
		if ("snake".equals(type)) {
			return new Snake();
		} else {
			return new Tyrannosaurus();
		}
	}

}
