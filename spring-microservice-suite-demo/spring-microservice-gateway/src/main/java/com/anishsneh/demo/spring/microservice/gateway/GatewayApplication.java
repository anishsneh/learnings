package com.anishsneh.demo.spring.microservice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * The Class GatewayApplication.
 * 
 * @author Anish Sneh
 */
@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
