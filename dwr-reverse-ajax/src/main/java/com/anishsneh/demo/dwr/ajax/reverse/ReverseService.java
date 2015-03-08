package com.anishsneh.demo.dwr.ajax.reverse;

import java.util.Collection;

import org.directwebremoting.ServerContext;
import org.directwebremoting.proxy.dwr.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ReverseService.
 * 
 * @author Anish Sneh
 * 
 */
public class ReverseService {
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(ReverseService.class);

	/**
	 * Update async.
	 *
	 * @param wctx the wctx
	 * @param message the message
	 */
	@SuppressWarnings("all")
	public static void updateAsync(final ServerContext wctx, final Message message) {
		logger.info("Calling for server context:{}", wctx);		
		Collection sessions = wctx.getScriptSessionsByPage(ContextUtil.APPLICATION_CONTEXT_PATH + "/index.jsp");
		Util utilAll = new Util(sessions);		
		utilAll.addFunctionCall("updateAsync", message.toString());
	}
}
