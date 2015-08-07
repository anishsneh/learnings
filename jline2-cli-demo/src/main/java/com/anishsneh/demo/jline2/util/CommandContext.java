package com.anishsneh.demo.jline2.util;

/**
 * The Enum CommandContext.
 * 
 * @author Anish Sneh
 */
public enum CommandContext {

	/** The helloworld. */
	HELLOWORLD, 
	
	/** The byeworld. */
	BYEWORLD,
	
	/** The quit. */
	QUIT,
	
	/** The help. */
	HELP,
	
	/** The clear. */
	CLEAR;
	
	/**
	 * Names.
	 *
	 * @return the string[]
	 */
	public static String[] names() {
		final CommandContext[] commandContexts = values();
	    final String[] names = new String[commandContexts.length];
	    for (int i = 0; i < commandContexts.length; i++) {
	        names[i] = commandContexts[i].name().toLowerCase();
	    }
	    return names;
	}
}
