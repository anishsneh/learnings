package com.anishsneh.demo.quick.core.patterns.behavioral.observer;

public class ObserverMain {

	public static void main(final String[] args) {
		System.out.println("Enter Text >");

		// create an event source - reads from stdin
		final EventSource eventSource = new EventSource();

		// create an observer
		final ResponseHandler responseHandler = new ResponseHandler();

		// subscribe the observer to the event source
		eventSource.addObserver(responseHandler);

		// starts the event thread
		Thread thread = new Thread(eventSource);
		thread.start();
	}
}
