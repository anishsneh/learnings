package com.anishsneh.demo.dwr.ajax.reverse;

import java.util.UUID;

import org.directwebremoting.ServerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class EventGenerator.
 * 
 * @author Anish Sneh
 * 
 */
public class EventGenerator implements Runnable{
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(EventGenerator.class);
	
	/** The wctx. */
	final ServerContext wctx;	
	
	/**
	 * Instantiates a new event generator.
	 *
	 * @param wctx the wctx
	 */
	public EventGenerator(final ServerContext wctx) {
		this.wctx = wctx;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while(true) {
			final String eventId = UUID.randomUUID().toString();
			logger.info("Generating event: " + eventId);
			ReverseService.updateAsync(this.wctx, new Message(eventId));
			try {
				Thread.sleep(3000L);
			} 
			catch (final InterruptedException ex) {
				logger.error("Failed", ex);
			}
		}
	}
	
}
