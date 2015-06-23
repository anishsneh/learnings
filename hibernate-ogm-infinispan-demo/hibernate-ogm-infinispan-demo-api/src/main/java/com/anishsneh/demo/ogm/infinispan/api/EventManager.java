package com.anishsneh.demo.ogm.infinispan.api;

import java.util.List;

import javax.ejb.Local;

import com.anishsneh.demo.ogm.infinispan.api.dto.Event;

/**
 * The Interface EventManager.
 * 
 * @author Anish Sneh
 * 
 */
@Local
public interface EventManager {

	/**
	 * Adds the event.
	 *
	 * @param event the event
	 * @return event, if successful
	 */
	public Event addEvent(final Event event);	
	
	
	/**
	 * Removes the events.
	 *
	 * @param severity the severity
	 * @return the int
	 */
	public int removeEvents(final String severity);	
	
	/**
	 * Gets the events by severity.
	 *
	 * @param severity the severity
	 * @return the events by severity
	 */
	public List<Event> getEventsBySeverity(final String severity);		
	
	/**
	 * Gets the events by time.
	 *
	 * @param pastMillis the past millis
	 * @return the events by time
	 */
	public List<Event> getEventsByTime(final long pastMillis);
}
