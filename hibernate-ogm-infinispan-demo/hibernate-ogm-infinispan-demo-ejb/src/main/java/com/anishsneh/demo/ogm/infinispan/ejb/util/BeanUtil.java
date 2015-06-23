package com.anishsneh.demo.ogm.infinispan.ejb.util;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import com.anishsneh.demo.ogm.infinispan.api.dto.Event;
import com.anishsneh.demo.ogm.infinispan.ejb.entity.EventEntity;

/**
 * The Class BeanUtil.
 * 
 * @author Anish Sneh
 * 
 */
public class BeanUtil {
	
	/**
	 * Gets the event entity from event.
	 *
	 * @param event the event
	 * @return the event entity from event
	 */
	public static EventEntity getEventEntityFromEvent(final Event event){
		final Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
		return mapper.map(event, EventEntity.class);
	}
	
	/**
	 * Gets the event from event entity.
	 *
	 * @param eventEntity the event entity
	 * @return the event from event entity
	 */
	public static Event getEventFromEventEntity(final EventEntity eventEntity){
		final Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
		return mapper.map(eventEntity, Event.class);
	}
	
	/**
	 * Gets the event list from event entity list.
	 *
	 * @param eventEntityList the event entity list
	 * @return the event list from event entity list
	 */
	public static List<Event> getEventListFromEventEntityList(final List<EventEntity> eventEntityList){
		final List<Event> eventList = new ArrayList<Event>();
		for(final EventEntity eventEntity : eventEntityList){
			eventList.add(getEventFromEventEntity(eventEntity));
		}
		return eventList;
	}
}
