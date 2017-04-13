package com.anishsneh.demo.spring.microservice.testdriven.util;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.anishsneh.demo.spring.microservice.testdriven.vo.Event;
import com.anishsneh.demo.spring.microservice.testdriven.vo.Events;

/**
 * The Class EventUtil.
 * 
 * @author Anish Sneh
 */
public class EventUtil {

    /**
     * Gets the events.
     *
     * @return the events
     */
    public static Events getEvents() {
    	
    	final Events events = new Events();
    	final List<Event> eventsList = Collections.synchronizedList(new ArrayList<Event>());
    	eventsList.add(new Event(UUID.randomUUID().toString(),"EVENT_TYPE_001", ZonedDateTime.now().toInstant().toEpochMilli()));
    	eventsList.add(new Event(UUID.randomUUID().toString(),"EVENT_TYPE_002", ZonedDateTime.now().toInstant().toEpochMilli()));
    	eventsList.add(new Event(UUID.randomUUID().toString(),"EVENT_TYPE_003", ZonedDateTime.now().toInstant().toEpochMilli()));
    	eventsList.add(new Event(UUID.randomUUID().toString(),"EVENT_TYPE_004", ZonedDateTime.now().toInstant().toEpochMilli()));
    	eventsList.add(new Event(UUID.randomUUID().toString(),"EVENT_TYPE_005", ZonedDateTime.now().toInstant().toEpochMilli()));
    	events.setEvents(eventsList);
        return events;
    }
}
