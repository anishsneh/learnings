package com.anishsneh.demo.ogm.infinispan.ejb;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;

import com.anishsneh.demo.ogm.infinispan.api.EventManager;
import com.anishsneh.demo.ogm.infinispan.api.dto.Event;
import com.anishsneh.demo.ogm.infinispan.ejb.entity.EventEntity;
import com.anishsneh.demo.ogm.infinispan.ejb.util.BeanUtil;

/**
 * The Class EventManagerBean.
 * 
 * Versions:
 * 
 * wildfly-8.2.0.Final
 * hibernate-ogm-modules-wildfly8-4.1.3.Final.zip
 * hibernate-search-modules-5.1.0.Final-wildfly-8-dist.zip
 * infinispan-as-embedded-modules-7.1.1.Final.zip
 * 
 * @author Anish Sneh
 * 
 */
@SuppressWarnings("all")
@Stateless
public class EventManagerBean implements EventManager{
	
	/** The logger. */
	@Inject
	private Logger logger;
	
	/** The entity manager. */
	@PersistenceContext(unitName = "EventPersistentUnit")
    private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.anishsneh.demo.ogm.infinispan.api.EventManager#addEvent(com.anishsneh.demo.ogm.infinispan.api.dto.Event)
	 */
	@Override
	public Event addEvent(final Event event) {				
		logger.info("Adding event: {}", event.toString());
		event.setEventUuid(UUID.randomUUID().toString());
		event.setTimestampMillis(System.currentTimeMillis());		
		final EventEntity eventEntity = BeanUtil.getEventEntityFromEvent(event);		
		entityManager.persist(eventEntity);
		return BeanUtil.getEventFromEventEntity(eventEntity);
	}

	/* (non-Javadoc)
	 * @see com.anishsneh.demo.ogm.infinispan.api.EventManager#removeEvents(java.lang.String)
	 */
	@Override
	public int removeEvents(final String severity) {
		logger.info("Removing all events by severity:{}", severity);		
		final Query query = entityManager.createQuery("FROM EventEntity e where e.severity = :severity");
		query.setParameter("severity", severity);
		int deletionCount = 0;
        for(final Object obj : query.getResultList()){
        	final EventEntity eventEntity = (EventEntity)obj;
        	logger.info("Processing deletion for event id: {}", eventEntity.getEventUuid());
        	entityManager.remove(eventEntity);
        	deletionCount++;
        }
		return deletionCount;
	}
	
	/* (non-Javadoc)
	 * @see com.anishsneh.demo.ogm.infinispan.api.EventManager#getEventsBySeverity(java.lang.String)
	 */
	@Override
	public List<Event> getEventsBySeverity(final String severity) {
		logger.info("Geting event by severity:{}", severity);
		logger.info("Entity manager: {}", entityManager.toString());
		final Query query = entityManager.createQuery("FROM EventEntity e where e.severity = :severity");
	    query.setParameter("severity", severity);
	    final List<Event> events = BeanUtil.getEventListFromEventEntityList(query.getResultList());
	    logger.info("Returning total events:{} by severity:{}", events.size(), severity);
	    return events;		
	}

	/* (non-Javadoc)
	 * @see com.anishsneh.demo.ogm.infinispan.api.EventManager#getEventsByTime(long)
	 */
	@Override
	public List<Event> getEventsByTime(final long pastMins) {
		final long pastMillis = pastMins*60*1000;
		logger.info("Getting all event from past millis: {}", pastMillis);
		final long fromTimestampMillis = System.currentTimeMillis() - pastMillis;
		logger.info("Entity manager: {}", entityManager.toString());
		final Query query = entityManager.createQuery("FROM EventEntity e where e.timestampMillis > :fromTimestampMillis");
	    query.setParameter("fromTimestampMillis", fromTimestampMillis);
	    final List<Event> events = BeanUtil.getEventListFromEventEntityList(query.getResultList());
	    logger.info("Returning total events:{} by past mins:{}", events.size(), pastMins);
	    return events;
	}
}
