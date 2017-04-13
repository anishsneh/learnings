package com.anishsneh.demo.spring.microservice.testdriven.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Events.
 * 
 * @author Anish Sneh
 */
public class Events {

	/** The events. */
	private List<Event> events;

	/**
	 * Gets the events.
	 *
	 * @return the events
	 */
	public List<Event> getEvents() {
		if (null == events) {
			events = new ArrayList<>();
		}
		return events;
	}

	/**
	 * Sets the events.
	 *
	 * @param events the new events
	 */
	public void setEvents(final List<Event> events) {
		this.events = events;
	}

}
