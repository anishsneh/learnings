package com.anishsneh.demo.ogm.infinispan.service;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;

import com.anishsneh.demo.ogm.infinispan.api.EventManager;
import com.anishsneh.demo.ogm.infinispan.api.dto.Event;
import com.anishsneh.demo.ogm.infinispan.api.dto.Events;
import com.anishsneh.demo.ogm.infinispan.api.util.EventUtil;
import com.anishsneh.demo.ogm.infinispan.service.util.ServiceUtil;

/**
 * The Class EventManagerResource.
 * 
 * http://127.0.0.1:8080/services/event-management
 * 
 * @author Anish Sneh
 * 
 */
@Path("/event-management")
public class EventResource {
	
	/** The logger. */
	@Inject
	private Logger logger;
	
	/** The event manager. */
	@EJB
	private EventManager eventManager;
		
	/**
	 * Gets the events.
	 *
	 * @param severity the severity
	 * @return the events
	 * 
	 * URL
	 * 
	 * http://127.0.0.1:8080/services/event-management/events-by-severity?severity=MAJOR
	 */
	@GET
	@Path("/events-by-severity")
	@Produces(MediaType.APPLICATION_XML)
	public Response getEvents(@DefaultValue("CRITICAL") @QueryParam("severity") final String severity) {
		if (!EventUtil.isValidSeverity(severity)) {
			logger.error("Provide a valid event severity: {}", severity);
			return ServiceUtil.getResponse(Status.BAD_REQUEST, "Invalid event severity: " + severity);			
		}
		logger.info("Returning events by severity: {}", severity);
		final Events events = new Events();
		events.setEventList(eventManager.getEventsBySeverity(severity));
		if(null == events || 0 == events.getEventList().size()){
			return ServiceUtil.getResponse(Status.NO_CONTENT, "");
		}
		return ServiceUtil.getResponse(Status.OK, events);
	}
	
	/**
	 * Gets the events.
	 *
	 * @param durationMins the duration mins
	 * @return the events
	 * 
	 * URL
	 * 
	 * http://127.0.0.1:8080/services/event-management/events-by-time?duration=10
	 */
	@GET
	@Path("/events-by-time")
	@Produces(MediaType.APPLICATION_XML)
	public Response getEvents(@DefaultValue("15") @QueryParam("duration") final int durationMins) {
		if (durationMins <= 0) {
			logger.error("Provide a valid duration in minutes: {}", durationMins);
			return ServiceUtil.getResponse(Status.BAD_REQUEST, "Invalid duration: " + durationMins);
		}
		logger.info("Returning events by past duration: {}", durationMins);
		final Events events = new Events();
		events.setEventList(eventManager.getEventsByTime(durationMins));
		if(null == events || 0 == events.getEventList().size()){
			return ServiceUtil.getResponse(Status.NO_CONTENT, "");
		}
		return ServiceUtil.getResponse(Status.OK, events);
	}
	
	/**
	 * Adds the event.
	 *
	 * @param event the event
	 * 
	 * URL
	 * 
	 * http://127.0.0.1:8080/services/event-management/events
	 * 
	 * PAYLOAD
	 * 
	 * <event>
	 * 		<severity>MAJOR</severity>
	 * 		<counterName>THROUGHPUT</counterName>
	 * 		<counterValue>20000</counterValue>
	 * </event>
	 * 
	 * @return the response
	 */
	@POST
	@Path("/events")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addEvent(final Event event) {
		if (!EventUtil.isValidEvent(event)) {
			logger.error("Provide valid event contents: " + event);
			return ServiceUtil.getResponse(Status.BAD_REQUEST, "Invalid event contents: " + event);
		}
		logger.info("Recieved event: {}", event.toString());
		eventManager.addEvent(event);
		return ServiceUtil.getResponse(Status.CREATED, event);
	}
	
	/**
	 * Removes the events.
	 *
	 * @param severity the severity
	 * @return the response
	 * 
	 * URL
	 * 
	 * http://127.0.0.1:8080/services/event-management/events-by-severity?severity=MAJOR
	 */
	@DELETE
	@Path("/events-by-severity")
	@Produces(MediaType.APPLICATION_XML)
	public Response removeEvents(@QueryParam("severity") final String severity) {
		if (!EventUtil.isValidSeverity(severity)) {
			logger.error("Provide a valid event severity: {}", severity);
			return ServiceUtil.getResponse(Status.BAD_REQUEST, "Invalid event severity: " + severity);			
		}
		logger.info("Deleting events by severity: {}", severity);
		int eventCount = eventManager.removeEvents(severity);
		return ServiceUtil.getResponse(Status.OK, "Removed total events: " + eventCount);
	}
}
