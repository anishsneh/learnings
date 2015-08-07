package com.anishsneh.demo.jline2.helper;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import jline.console.ConsoleReader;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.StringsCompleter;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.jline2.parser.CommandParser;
import com.anishsneh.demo.jline2.util.CommandContext;
import com.anishsneh.demo.jline2.util.CommandUtil;

/**
 * The Class PromptHelper.
 * 
 * @author Anish Sneh
 */
public class PromptHelper {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(PromptHelper.class);

	/**
	 * Instantiates a new prompt helper.
	 */
	public PromptHelper() {
		init();
	}

	/**
	 * Inits the.
	 */
	public void init() {
		logger.info("Initializing prompt helper");
	}

	/**
	 * Process command.
	 *
	 * @param reader the reader
	 * @param input the input
	 */
	public void processCommand(final ConsoleReader reader, final String input) {

		Objects.requireNonNull(input);
		final CommandParser commandParser = new CommandParser();
		final CmdLineParser cmdLineParser = new CmdLineParser(commandParser);
		cmdLineParser.getProperties().withUsageWidth(80);
		try {
			final String normalizedcommand = CommandUtil.normalizeCommand(input);
			logger.info("Parsing normalizedcommand:{}", normalizedcommand);
			final String[] commandTokens = CommandUtil.tokenizeCommand(normalizedcommand);
			logger.info("Parsing commandTokens:" + Arrays.asList(commandTokens));
			cmdLineParser.parseArgument(commandTokens);
			logger.info("No argument is given");
		} 
		catch (final CmdLineException e) {
			logger.error("Failed to parse", e);
			if(null != e.getMessage() && !e.getMessage().contains(CommandUtil.NORMALIZATION_COMMAND)){
				System.err.println(e.getMessage());
			}
			return;
		}

		if (null != commandParser.getCmdContext()) {
			logger.info("Command context is: " + commandParser.getCmdContext());
			CommandContext commandContext = null;
			try {
				commandContext = CommandContext.valueOf(commandParser.getCmdContext().toUpperCase());
				switch (commandContext) {
					case HELLOWORLD:
						logger.info("Processing other parameters for helloworld");
						if(commandParser.isCmdToAll()){
							System.out.println("Hello everyone");
						}
						else if(null != commandParser.getCmdToSelected()){
							System.out.println("Hello message: " + commandParser.getCmdToSelected());
							logger.info("Hello message: " + commandParser.getCmdToSelected());
							for(final String argument : commandParser.getArguments()){
								System.out.println("--- Hello: " + argument);
								logger.info("--- Hello: " + argument);
							}
						}
						break;
	
					case BYEWORLD:
						logger.info("Processing other parameters for byeworld");
						if(commandParser.isCmdToAll()){
							System.out.println("Bye everyone");
						}
						else if(null != commandParser.getCmdToSelected()){
							System.out.println("Bye message: " + commandParser.getCmdToSelected());
							logger.info("Bye message: " + commandParser.getCmdToSelected());
							for(final String argument : commandParser.getArguments()){
								System.out.println("--- Bye: " + argument);
								logger.info("--- Bye: " + argument);
							}
						}
						break;
						
					case HELP:
						logger.info("Processing other parameters for help");
						printHelp(cmdLineParser);
						break;
						
					case QUIT:
						logger.info("Processing other parameters for quit");
						System.out.println("Thanks for using HelloWorld greeter prompt.");
						System.exit(0);
						break;
						
					case CLEAR:
						reader.clearScreen();
						break;
	
					default:
						logger.error("Invalid command context");
						System.out.println("Invalid command context: " + commandContext);
				}
			} 
			catch (final Exception ex) {
				logger.warn("Invalid command context: " + ex.getMessage());
				System.out.println("Invalid command context");
				printHelp(cmdLineParser);
			}
		} 
		else {
			System.out.println("Command context is undefined");
			logger.info("Command context is null");
		}
	}

	/**
	 * Execute.
	 *
	 * @param out the out
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void execute(final PrintStream out) throws IOException {

		printWelcomeMessage();
		final ConsoleReader reader = new ConsoleReader();
		reader.setBellEnabled(false);
		final List<Completer> completors = new LinkedList<Completer>();

		completors.add(new StringsCompleter(CommandContext.names()));
		reader.addCompleter(new ArgumentCompleter(completors));

		String line;
		final PrintWriter printWriter = new PrintWriter(out);

		while ((line = readLine(reader, "")) != null) {
			processCommand(reader, line);
			printWriter.flush();
		}
	}

	/**
	 * Prints the welcome message.
	 */
	private void printWelcomeMessage() {
		System.out.println("Welcome to HelloWorld demo prompt. For assistance press TAB or type \"help\" then hit ENTER.");
	}

	/**
	 * Prints the help.
	 *
	 * @param cmdLineParser the cmd line parser
	 */
	private void printHelp(final CmdLineParser cmdLineParser) {
		System.err.printf("%-25s %-20s %n", "Command Context", "Description");
		System.err.printf("%-23s %-20s %n", "-------------", "-------------");
		System.err.printf("%-23s %-20s %n", " help", ": Show help");
		System.err.printf("%-23s %-20s %n", " clear", ": Clear screen");
		System.err.printf("%-23s %-20s %n", " helloworld", ": Greet hello to world");
		System.err.printf("%-23s %-20s %n", " byeworld", ": Say bye to world");
		System.err.printf("%-23s %-20s %n", " quit", ": Quit the application");
		System.err.printf("%-23s %-20s %n", "      ", "     ");
		System.err.printf("%-23s %-20s %n", "Available Options:", "     ");
		System.err.printf("%-23s %-20s %n", "      ", "     ");
		cmdLineParser.printUsage(System.err);
		System.err.printf("%-23s %-20s %n", "      ", "     ");
		System.err.printf("%-23s %-20s %n", "Example:", "     ");
		System.err.printf("%-23s %-20s %n", "      ", "     ");
		System.err.printf("%-43s %n", "helloworld -s goodmorning anish rakesh narendra");

	}

	/**
	 * Read line.
	 *
	 * @param reader the reader
	 * @param promtMessage the promt message
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private String readLine(final ConsoleReader reader, final String promtMessage) throws IOException {
		reader.setPrompt("\u001B[1mfoo\u001B[0m@bar\u001B[32m@baz\u001B[0m> ");
		final String line = reader.readLine(promtMessage + "\nanishsneh> ");
		return line.trim();
	}

}
