package com.anishsneh.demo.spring.microservice.testdriven.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.anishsneh.demo.spring.microservice.testdriven.util.EventUtil;
import com.anishsneh.demo.spring.microservice.testdriven.vo.Events;

/**
 * The Class HelloWorldResource.
 * 
 * @author Anish Sneh
 */
@Path("/")
@Component
public class HelloTestDrivenResource {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(HelloTestDrivenResource.class);
	
    /**
     * Users.
     *
     * @param uriInfo the uri info
     * @return the events
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/events")
    public Events getEvents(@Context final UriInfo uriInfo) {
    	logger.info("Getting events");
        return EventUtil.getEvents();
    }
}
