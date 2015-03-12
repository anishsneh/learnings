package com.anishsneh.demo.bpmn.camunda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PostSuccessServiceTask.
 * 
 * @author Anish Sneh
 * 
 */
public class PostSuccessServiceTask {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(PostSuccessServiceTask.class);

	/**
	 * Execute.
	 */
	public void execute(){
		logger.info("Executing post success");
	}
}
