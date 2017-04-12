package com.anishsneh.demo.spring.microservice.streaming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * The Class HelloKafkaConsumerApplication.
 * 
 * @author Anish Sneh
 * 
 * NOTE: Kafka broker MUST BE up & running before hand at localhost:9092
 */
@EnableEurekaClient
@SpringBootApplication
@EnableBinding(Sink.class)
public class HelloKafkaConsumerApplication {
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(HelloKafkaConsumerApplication.class);
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		SpringApplication.run(HelloKafkaConsumerApplication.class, args);
	}
	
	/**
	 * Logger sink.
	 *
	 * @param event the event
	 */
	@StreamListener(Sink.INPUT)
	public void loggerSink(Message<?> event) {
		logger.info("RECEIVED: " + event.toString());
		//FOR MANUAL ACK USE FOLLOWING (must set autoCommitOffset=false in application configuration)
		/*final Acknowledgment acknowledgment = event.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
		if (acknowledgment != null) {
			logger.info("Acknowledged: " + event.toString());
			acknowledgment.acknowledge();
		}*/
	}
}