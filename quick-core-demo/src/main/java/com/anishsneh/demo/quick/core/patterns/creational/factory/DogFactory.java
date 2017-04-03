package com.anishsneh.demo.quick.core.patterns.creational.factory;

public class DogFactory {
	public static Dog getDog(final String criteria) {
		if (criteria.equals("small"))
			return new Poodle();
		else if (criteria.equals("big"))
			return new Rottweiler();
		else if (criteria.equals("working"))
			return new SiberianHusky();

		return null;
	}
}