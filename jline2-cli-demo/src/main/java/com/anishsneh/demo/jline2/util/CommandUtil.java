package com.anishsneh.demo.jline2.util;

/**
 * The Class CommandUtil.
 * 
 * @author Anish Sneh
 */
public class CommandUtil {
	
	/** The Constant NORMALIZATION_COMMAND. */
	public static final String NORMALIZATION_COMMAND = "CONTEXT";

	/**
	 * Normalize command.
	 *
	 * @param command the command
	 * @return the string
	 */
	public static String normalizeCommand(final String command){
		return "-" + NORMALIZATION_COMMAND + " " + command.trim();
	}
	
	/**
	 * Tokenize command.
	 *
	 * @param normalizedcommand the normalizedcommand
	 * @return the string[]
	 */
	public static String[] tokenizeCommand(final String normalizedcommand){
		return normalizedcommand.split("\\s+");
	}
}
