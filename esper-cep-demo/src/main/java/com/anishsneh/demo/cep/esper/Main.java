package com.anishsneh.demo.cep.esper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anishsneh.demo.cep.esper.helper.AlarmEventManager;

/**
 * The Class Main. 
 * 
 * Excute using:
 * 		$mvn exec:java
 * 
 * @author Anish Sneh
 */
@SuppressWarnings("all")
public class Main {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	/** The Constant APPLICATION_CONTEXT. */
	private static final String APPLICATION_CONTEXT = "classpath:/META-INF/spring/application-context.xml";

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		logger.info("Initializing CEP system");		
		ApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT);
		logger.debug("Context intialized: " + context);
		AlarmEventManager alarmEventManager= context.getBean("alarmEventManager", AlarmEventManager.class);
		alarmEventManager.generateSampleEvents(Integer.parseInt(args[0]));		
	}
}
