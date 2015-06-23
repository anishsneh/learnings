package com.anishsneh.demo.ogm.infinispan.ejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * The Class EventEntity.
 * 
 * @author Anish Sneh
 * 
 */
@Entity
@Indexed
public class EventEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The event uuid. */
	@Id
	@Column(name = "event_id", updatable = false, nullable = false)
	private String eventUuid;
	
	/** The severity. */
	@Field(analyze = Analyze.NO)
	@Column(name = "severity", nullable = false)
	private String severity;
	
	/** The counter name. */
	@Field(analyze = Analyze.NO)
	@Column(name = "counter_name")
	private String counterName;
	
	/** The counter value. */
	@Field(analyze = Analyze.NO)
	@Column(name = "counter_value")
	private long counterValue;
	
	/** The timestamp millis. */
	@Field(analyze = Analyze.NO)
	@Column(name = "timestamp_millis", nullable = false)
	private long timestampMillis;
	
	/**
	 * Instantiates a new event entity.
	 */
	public EventEntity(){		
	}
	
	/**
	 * Instantiates a new event entity.
	 *
	 * @param eventUuid the event uuid
	 * @param severity the severity
	 * @param counterName the counter name
	 * @param counterValue the counter value
	 */
	public EventEntity(final String eventUuid, final String severity, final String counterName, final long counterValue) {
		super();
		this.eventUuid = eventUuid;
		this.severity = severity;
		this.counterName = counterName;
		this.counterValue = counterValue;
		this.timestampMillis = System.currentTimeMillis();
	}
	
	/**
	 * Gets the event uuid.
	 *
	 * @return the event uuid
	 */
	public String getEventUuid() {
		return eventUuid;
	}

	/**
	 * Sets the event uuid.
	 *
	 * @param eventUuid the new event uuid
	 */
	public void setEventUuid(final String eventUuid) {
		this.eventUuid = eventUuid;
	}

	/**
	 * Gets the severity.
	 *
	 * @return the severity
	 */
	public String getSeverity() {
		return severity;
	}

	/**
	 * Sets the severity.
	 *
	 * @param severity the new severity
	 */
	public void setSeverity(final String severity) {
		this.severity = severity;
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
	public long getCounterValue() {
		return counterValue;
	}

	/**
	 * Sets the counter value.
	 *
	 * @param counterValue the new counter value
	 */
	public void setCounterValue(final long counterValue) {
		this.counterValue = counterValue;
	}

	/**
	 * Gets the timestamp millis.
	 *
	 * @return the timestamp millis
	 */
	public long getTimestampMillis() {
		return timestampMillis;
	}

	/**
	 * Sets the timestamp millis.
	 *
	 * @param timestampMillis the new timestamp millis
	 */
	public void setTimestampMillis(final long timestampMillis) {
		this.timestampMillis = timestampMillis;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Event [eventUuid=" + eventUuid + ", severity=" + severity + ", counterName=" + counterName + ", counterValue=" + counterValue + ", timestampMillis=" + timestampMillis + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((counterName == null) ? 0 : counterName.hashCode());
		result = prime * result + (int) (counterValue ^ (counterValue >>> 32));
		result = prime * result + ((eventUuid == null) ? 0 : eventUuid.hashCode());
		result = prime * result + ((severity == null) ? 0 : severity.hashCode());
		result = prime * result + (int) (timestampMillis ^ (timestampMillis >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		final EventEntity other = (EventEntity) obj;
		if (counterName == null) {
			if (other.counterName != null){
				return false;
			}
		} 
		else if (!counterName.equals(other.counterName)){
			return false;
		}
		if (counterValue != other.counterValue){
			return false;
		}
		if (eventUuid == null) {
			if (other.eventUuid != null){
				return false;
			}
		} 
		else if (!eventUuid.equals(other.eventUuid)){
			return false;
		}
		if (severity == null) {
			if (other.severity != null){
				return false;
			}
		} 
		else if (!severity.equals(other.severity)){
			return false;
		}
		if (timestampMillis != other.timestampMillis){
			return false;
		}
		return true;
	}
}
