package com.anishsneh.demo.spring.microservice.testdriven.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Event.
 * 
 * @author Anish Sneh
 */
@XmlRootElement
public class Event {

	/** The uuid. */
	private String uuid;
	
	/** The type. */
	private String type;
	
	/** The time millis. */
	private long timeMillis;
	
	/**
	 * Instantiates a new event.
	 */
	public Event(){	
	}
	
	/**
	 * Instantiates a new event.
	 *
	 * @param uuid the uuid
	 * @param type the type
	 * @param timeMillis the time millis
	 */
	public Event(final String uuid, final String type, final long timeMillis){
		this.uuid = uuid;
		this.type = type;
		this.timeMillis = timeMillis;
	}
	
	/**
	 * Gets the uuid.
	 *
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}
	
	/**
	 * Sets the uuid.
	 *
	 * @param uuid the new uuid
	 */
	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(final String type) {
		this.type = type;
	}
	
	/**
	 * Gets the time millis.
	 *
	 * @return the time millis
	 */
	public long getTimeMillis() {
		return timeMillis;
	}
	
	/**
	 * Sets the time millis.
	 *
	 * @param timeMillis the new time millis
	 */
	public void setTimeMillis(final long timeMillis) {
		this.timeMillis = timeMillis;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (timeMillis ^ (timeMillis >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Event other = (Event) obj;
		if (timeMillis != other.timeMillis)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Event [uuid=" + uuid + ", type=" + type + ", timeMillis=" + timeMillis + "]";
	}
}
	
