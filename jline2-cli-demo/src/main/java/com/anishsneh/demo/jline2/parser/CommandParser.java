package com.anishsneh.demo.jline2.parser;

import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

/**
 * The Class CommandParser.
 * 
 * @author Anish Sneh
 */
public class CommandParser {
		
	/** The cmd context. */
	@Option(name = "-CONTEXT", usage = "Command context", required = true)
	private String cmdContext;
	
	/** The cmd to all. */
	@Option(name = "-a", aliases = {"--to-all"}, usage = "Say hello to whole world", depends = {"-CONTEXT"}, forbids = {"-s"} )
	private boolean cmdToAll;
	
	/** The cmd to selected. */
	@Option(name = "-s", aliases = {"--to-selected"}, usage = "Say hello to selected people", depends = {"-CONTEXT"}, forbids = {"-a"})
	private String cmdToSelected;
	
	/** The arguments. */
	@Argument
	private List<String> arguments = new ArrayList<String>();

	/**
	 * Gets the cmd context.
	 *
	 * @return the cmd context
	 */
	public String getCmdContext() {
		return cmdContext;
	}

	/**
	 * Checks if is cmd to all.
	 *
	 * @return true, if is cmd to all
	 */
	public boolean isCmdToAll() {
		return cmdToAll;
	}

	/**
	 * Gets the cmd to selected.
	 *
	 * @return the cmd to selected
	 */
	public String getCmdToSelected() {
		return cmdToSelected;
	}
	
	/**
	 * Gets the arguments.
	 *
	 * @return the arguments
	 */
	public List<String> getArguments() {
		return arguments;
	}	
}
