package com.anishsneh.demo.cep.esper.event;

import com.thoughtworks.xstream.XStream;

/**
 * The Class AlarmEvent.
 * 
 * @author Anish Sneh
 */
public class AlarmEvent {

	/** The event id. */
	private String eventId;
	
	/** The counter name. */
	private String counterName;
	
	/** The counter value. */
	private double counterValue;
	
	/** The timestamp. */
	private long timestamp;
	
	/**
	 * Instantiates a new alarm event.
	 *
	 * @param counterName the counter name
	 * @param counterValue the counter value
	 */
	public AlarmEvent(final String counterName, final double counterValue) {
		this.counterName = counterName;
		this.counterValue = counterValue;
		timestamp = System.currentTimeMillis();
	}
	
	/**
	 * Gets the counter name.
	 *
	 * @return the counter name
	 */
	public String getCounterName() {
		return counterName;
	}
	
	/**
	 * Sets the counter name.
	 *
	 * @param counterName the new counter name
	 */
	public void setCounterName(final String counterName) {
		this.counterName = counterName;
	}
	
	/**
	 * Gets the counter value.
	 *
	 * @return the counter value
	 */
	public double getCounterValue() {
		return counterValue;
	}
	
	/**
	 * Sets the counter value.
	 *
	 * @param counterValue the new counter value
	 */
	public void setCounterValue(final double counterValue) {
		this.counterValue = counterValue;
	}
	
	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(final long timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * Gets the event id.
	 *
	 * @return the event id
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * Sets the event id.
	 *
	 * @param eventId the new event id
	 */
	public void setEventId(final String eventId) {
		this.eventId = eventId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return new XStream().toXML(this);		
	}
}
