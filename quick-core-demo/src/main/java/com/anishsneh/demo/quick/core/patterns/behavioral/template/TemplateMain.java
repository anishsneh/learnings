package com.anishsneh.demo.quick.core.patterns.behavioral.template;

public class TemplateMain {

	public static void main(final String[] args) {
		Game game = new Cricket();
		game.play();
		System.out.println();
		game = new Football();
		game.play();
	}
}

abstract class Game {

	abstract void initialize();

	abstract void startPlay();

	abstract void endPlay();

	// template method
	public final void play() {
		initialize();
		startPlay();
		endPlay();
	}
}

class Cricket extends Game {

	@Override
	void endPlay() {
		System.out.println("Cricket Game Finished!");
	}

	@Override
	void initialize() {
		System.out.println("Cricket Game Initialized! Start playing.");
	}

	@Override
	void startPlay() {
		System.out.println("Cricket Game Started. Enjoy the game!");
	}
}

class Football extends Game {

	@Override
	void endPlay() {
		System.out.println("Football Game Finished!");
	}

	@Override
	void initialize() {
		System.out.println("Football Game Initialized! Start playing.");
	}

	@Override
	void startPlay() {
		System.out.println("Football Game Started. Enjoy the game!");
	}
}
