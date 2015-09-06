package com.anishsneh.spring.integration;

import org.apache.log4j.Logger;

/**
 * The Class PrintServiceActivator.
 * 
 * @author Anish Sneh
 */
public class PrintServiceActivator {
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(PrintServiceActivator.class);
	
	/**
	 * Process content.
	 *
	 * @param fileContent the file content
	 * @return the string
	 */
	public String processContent(final String fileContent){
		logger.debug(fileContent);
		return fileContent;
	}
}
