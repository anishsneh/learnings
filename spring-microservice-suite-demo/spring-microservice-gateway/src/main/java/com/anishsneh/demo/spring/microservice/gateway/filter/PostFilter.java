package com.anishsneh.demo.spring.microservice.gateway.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * The Class PostFilter.
 * 
 * @author Anish Sneh
 */
@Component
public class PostFilter extends ZuulFilter {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(PostFilter.class);

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		return "post";
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() {
		final HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		final HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
		logger.info("REQUEST SCHEME/ADDR/PORT: " + request.getScheme() + " " + request.getLocalAddr() + ":" + request.getLocalPort());
		logger.info("REQUEST METHOD/URI/PROTOCOL: " + request.getMethod() + " " + request.getRequestURI() + " " + request.getProtocol());
		logger.info("RESPONSE STATUS: " + response.getStatus());
		return null;
	}
}
