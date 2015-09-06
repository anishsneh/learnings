package com.anishsneh.spring.integration;

import org.springframework.integration.Message;
import org.springframework.integration.file.DefaultFileNameGenerator;

/**
 * The Class CustomFileNameGenerator.
 * 
 * @author Anish Sneh
 */
public class CustomFileNameGenerator extends DefaultFileNameGenerator {
	 
	    /* (non-Javadoc)
    	 * @see org.springframework.integration.file.DefaultFileNameGenerator#generateFileName(org.springframework.integration.Message)
    	 */
    	@Override
	    public String generateFileName(Message<?> message) {
	        final String originalFileName = super.generateFileName(message);
	        final String newFileName = "updated_" + originalFileName;
	        return newFileName;
	    }
}
