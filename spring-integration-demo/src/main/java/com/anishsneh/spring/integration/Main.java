package com.anishsneh.spring.integration;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The Class Main.
 * 
 * @author Anish Sneh
 */
public class Main {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(Main.class);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/application-context.xml");
		logger.debug("Context intialized: " + context);
	}

}
