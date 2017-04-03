package com.anishsneh.demo.quick.core.patterns.behavioral.chain;

public class ChainResponsibilityMain {

	public static void main(final String[] args) {
		PlanetHandler chain = setUpChain();

		chain.handleRequest(PlanetEnum.VENUS);
		chain.handleRequest(PlanetEnum.MERCURY);
		chain.handleRequest(PlanetEnum.EARTH);
		chain.handleRequest(PlanetEnum.JUPITER);
	}

	public static PlanetHandler setUpChain() {
		PlanetHandler mercuryHandler = new MercuryHandler();
		PlanetHandler venusHandler = new VenusHandler();
		PlanetHandler earthHandler = new EarthHandler();

		mercuryHandler.setSuccessor(venusHandler);
		venusHandler.setSuccessor(earthHandler);

		return mercuryHandler;
	}

}

abstract class PlanetHandler {

	PlanetHandler successor;

	public void setSuccessor(PlanetHandler successor) {
		this.successor = successor;
	}

	public abstract void handleRequest(PlanetEnum request);

}

enum PlanetEnum {
	MERCURY, VENUS, EARTH, MARS, JUPITER, SATURN, URANUS, NEPTUNE;
}

class MercuryHandler extends PlanetHandler {

	public void handleRequest(PlanetEnum request) {
		if (request == PlanetEnum.MERCURY) {
			System.out.println("MercuryHandler handles " + request);
			System.out.println("Mercury is hot.\n");
		} else {
			System.out.println("MercuryHandler doesn't handle " + request);
			if (successor != null) {
				successor.handleRequest(request);
			}
		}
	}

}

class VenusHandler extends PlanetHandler {

	public void handleRequest(PlanetEnum request) {
		if (request == PlanetEnum.VENUS) {
			System.out.println("VenusHandler handles " + request);
			System.out.println("Venus is poisonous.\n");
		} else {
			System.out.println("VenusHandler doesn't handle " + request);
			if (successor != null) {
				successor.handleRequest(request);
			}
		}
	}

}

class EarthHandler extends PlanetHandler {

	public void handleRequest(PlanetEnum request) {
		if (request == PlanetEnum.EARTH) {
			System.out.println("EarthHandler handles " + request);
			System.out.println("Earth is comfortable.\n");
		} else {
			System.out.println("EarthHandler doesn't handle " + request);
			if (successor != null) {
				successor.handleRequest(request);
			}
		}
	}

}
