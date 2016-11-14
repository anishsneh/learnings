package com.anishsneh.demo.spring.microservice.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * The Class RegistryApplication.
 * 
 * @author Anish Sneh
 */
@EnableEurekaServer
@SpringBootApplication
public class RegistryApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		SpringApplication.run(RegistryApplication.class, args);
	}
}
