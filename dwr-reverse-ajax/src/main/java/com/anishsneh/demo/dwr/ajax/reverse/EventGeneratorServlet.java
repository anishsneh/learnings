package com.anishsneh.demo.dwr.ajax.reverse;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class EventGeneratorServlet.
 * 
 * @author Anish Sneh
 */
public class EventGeneratorServlet extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8155471730183825885L;
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(EventGeneratorServlet.class);
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {		
		final ServletConfig conf= getServletConfig();
		logger.info("Initializing event generator with servlet conf:{}", conf);
		final ServerContext wctx = ServerContextFactory.get(conf.getServletContext());		
		logger.info("Starting event generator with server context:{}", wctx);
		final Runnable eventGenerator = new EventGenerator(wctx);
		new Thread(eventGenerator).start();
	}
}
