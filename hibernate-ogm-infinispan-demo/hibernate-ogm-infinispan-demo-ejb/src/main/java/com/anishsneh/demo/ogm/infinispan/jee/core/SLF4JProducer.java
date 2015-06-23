package com.anishsneh.demo.ogm.infinispan.jee.core;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class SLF4JProducer for CDI injection.
 * 
 * @author Anish Sneh
 * 
 */
@ApplicationScoped
public class SLF4JProducer {

	/**
	 * Producer.
	 *
	 * @param ip the ip
	 * @return the logger
	 */
	@Produces
	public Logger producer(final InjectionPoint ip) {
		return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
	}
}