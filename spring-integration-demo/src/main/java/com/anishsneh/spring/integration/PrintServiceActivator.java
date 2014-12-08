package com.anishsneh.spring.integration;

import org.apache.log4j.Logger;

public class PrintServiceActivator {
	
	private static final Logger logger = Logger.getLogger(PrintServiceActivator.class);
	
	public String processContent(String fileContent){
		logger.debug(fileContent);
		return fileContent;
	}

}
