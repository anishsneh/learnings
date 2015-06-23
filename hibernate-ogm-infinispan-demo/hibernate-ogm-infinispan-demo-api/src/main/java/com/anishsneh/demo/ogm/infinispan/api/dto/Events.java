package com.anishsneh.demo.ogm.infinispan.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Events.
 * 
 * @author Anish Sneh
 * 
 */
@XmlRootElement(name = "events")
@XmlAccessorType(XmlAccessType.FIELD)
public class Events implements Serializable {
		
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The events. */
	@XmlElement(name = "event")
	private List<Event> eventList;

	/**
	 * Gets the event list.
	 *
	 * @return the event list
	 */
	public List<Event> getEventList() {
		if(null == eventList){
			eventList = new ArrayList<Event>();
		}
		return eventList;
	}

	/**
	 * Sets the event list.
	 *
	 * @param eventList the new event list
	 */
	public void setEventList(final List<Event> eventList) {
		this.eventList = eventList;
	}

}
