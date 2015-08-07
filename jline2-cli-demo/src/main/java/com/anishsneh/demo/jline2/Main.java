package com.anishsneh.demo.jline2;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.jline2.helper.PromptHelper;

/**
 * The Class Main.
 * 
 * @author Anish Sneh
 */
public class Main {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(final String[] args) throws IOException {
		logger.info("Initializing main");
		new PromptHelper().execute(System.out);		
	}
}
