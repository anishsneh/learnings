package com.anishsneh.spring.integration;

import org.springframework.integration.Message;
import org.springframework.integration.file.DefaultFileNameGenerator;

public class CustomFileNameGenerator extends DefaultFileNameGenerator {
	 
	    @Override
	    public String generateFileName(Message<?> message) {
	        String originalFileName = super.generateFileName(message);
	        String newFileName = "updated_" + originalFileName;
	        return newFileName;
	    }
}
