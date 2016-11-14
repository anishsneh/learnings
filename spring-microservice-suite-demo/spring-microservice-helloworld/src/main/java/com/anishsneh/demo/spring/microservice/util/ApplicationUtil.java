package com.anishsneh.demo.spring.microservice.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

/**
 * The Class ApplicationUtil.
 * 
 * @author Anish Sneh
 */
public class ApplicationUtil {

	/**
	 * Inits the jersey servlet (using Jersey1 hence need to init servlet)
	 *
	 * @param servletContext the servlet context
	 */
	public  static void initJerseyServlet(final ServletContext servletContext) {
		final ServletRegistration.Dynamic appServlet = servletContext.addServlet("jersey-servlet", new SpringServlet());
		Map<String, String> filterParameters = new HashMap<>();
        filterParameters.put("com.sun.jersey.config.property.packages", "com.anishsneh.demo.spring.microservice.service");
        appServlet.setInitParameters(filterParameters);
        appServlet.setInitParameter(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
		appServlet.setLoadOnStartup(2);
		appServlet.addMapping("/*");
	}
	
}
