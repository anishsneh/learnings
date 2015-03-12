package com.anishsneh.demo.bpmn.camunda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PostFailureServiceTask.
 * 
 * @author Anish Sneh
 * 
 */
public class PostFailureServiceTask {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(PostFailureServiceTask.class);
	
	/**
	 * Execute.
	 */
	public void execute(){
		logger.info("Executing post failure");
	}
}
