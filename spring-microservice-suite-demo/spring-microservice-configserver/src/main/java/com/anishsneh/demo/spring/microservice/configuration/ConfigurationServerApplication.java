package com.anishsneh.demo.spring.microservice.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The Class ConfigurationServerApplication.
 * 
 * @author Anish Sneh
 */
@EnableEurekaClient
@EnableConfigServer
@SpringBootApplication
public class ConfigurationServerApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		SpringApplication.run(ConfigurationServerApplication.class, args);
	}
}
