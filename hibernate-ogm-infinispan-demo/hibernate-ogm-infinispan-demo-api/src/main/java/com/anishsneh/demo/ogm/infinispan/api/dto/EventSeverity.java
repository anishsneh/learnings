package com.anishsneh.demo.ogm.infinispan.api.dto;

/**
 * The Enum EventSeverity.
 * 
 * @author Anish Sneh
 * 
 */
public enum EventSeverity {
	
	/** The major. */
	MAJOR("MAJOR"), 
	
	/** The minor. */
	MINOR("MINOR"), 
	
	/** The critical. */
	CRITICAL("CRITICAL");

	/** The severity. */
	private String severity;

	/**
	 * Instantiates a new event severity.
	 *
	 * @param s the s
	 */
	private EventSeverity(final String s) {
		severity = s;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return severity;
	}
}
