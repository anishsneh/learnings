package com.anishsneh.demo.infinispan;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The listener interface for receiving cache events.
 * The class that is interested in processing a cache
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addCacheListener<code> method. When
 * the cache event occurs, that object's appropriate
 * method is invoked.
 *
 * @see CacheEvent
 * 
 * @author Anish Sneh
 */
@Listener
public class CacheListener {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CacheListener.class);

	/**
	 * On create event.
	 *
	 * @param event the event
	 */
	@CacheEntryCreated	
	public void onCreateEvent(CacheEntryCreatedEvent<String, Person> event) {
		logger.info("Entry " + event.getKey() + " created in the cache");
	}
	
	/**
	 * On modify event.
	 *
	 * @param event the event
	 */
	@CacheEntryModified
	public void onModifyEvent(CacheEntryModifiedEvent<String, Person> event) {
		logger.info("Entry " + event.getKey() + " modified in the cache");
	}
	
	/**
	 * On remove event.
	 *
	 * @param event the event
	 */
	@CacheEntryRemoved
	public void onRemoveEvent(CacheEntryRemovedEvent<String, Person> event) {
		logger.info("Entry " + event.getKey() + " removed in the cache");
	}

}
