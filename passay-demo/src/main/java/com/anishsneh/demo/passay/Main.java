package com.anishsneh.demo.passay;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.passay.processor.PasswordProcessor;

/**
 * The Class Main.
 */
public class Main {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		logger.info("Initializing passay demo");
		final PasswordProcessor passwordProcessor = new PasswordProcessor();
		final List<String> passwordList = new ArrayList<String>();
		passwordList.add("HelloWorld");
		passwordList.add("redapple1@");
		passwordList.add("redapppple1@");
		passwordList.add("RedA p ple1@");
		passwordList.add("1qaz2Wsx@");		
		passwordList.add("AnishSneh1@");
		passwordList.add("RedApple1@");

		for(final String pwd : passwordList){
			logger.info("########################### INPUT: " + pwd + " ###########################");
			passwordProcessor.processPassword(pwd);
		}
	}
}
