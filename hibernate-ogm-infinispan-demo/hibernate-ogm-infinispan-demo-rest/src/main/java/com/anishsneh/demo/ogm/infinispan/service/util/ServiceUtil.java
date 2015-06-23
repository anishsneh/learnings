package com.anishsneh.demo.ogm.infinispan.service.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * The Class ServiceUtil.
 * 
 * @author Anish Sneh
 * 
 */
public class ServiceUtil {

	/**
	 * Gets the response.
	 *
	 * @param status the status
	 * @param entity the entity
	 * @return the response
	 */
	public static Response getResponse(final Status status, final Object entity) {
		return Response.status(status).entity(entity).build();
	}
}
