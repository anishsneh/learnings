package com.anishsneh.demo.spring.microservice.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anishsneh.demo.spring.microservice.configuration.SystemConfiguration;
import com.anishsneh.demo.spring.microservice.util.UserUtil;
import com.anishsneh.demo.spring.microservice.vo.Users;

/**
 * The Class FooBarResource.
 * 
 * @author Anish Sneh
 */
@Path("/")
@Component
public class FooBarResource {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(FooBarResource.class);

    /** The system configuration. */
    @Autowired
    private SystemConfiguration systemConfiguration;

    /**
     * Message.
     *
     * @return the string
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/message")
    public String message() {
    	logger.info("Getting foobar message");
        return "FOO_BAR=" + systemConfiguration.userKey;
    }

    /**
     * Users.
     *
     * @param uriInfo the uri info
     * @return the users
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users")
    public Users getUsers(@Context final UriInfo uriInfo) {
    	logger.info("Getting foobar users");
        return UserUtil.getUsers();
    }
}
