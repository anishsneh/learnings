package com.anishsneh.demo.spring.microservice.streaming;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;

import com.anishsneh.demo.spring.microservice.streaming.vo.Event;

/**
 * The Class HelloKafkaProducerApplication.
 * 
 * @author Anish Sneh
 * 
 * NOTE: Kafka broker MUST BE up & running before hand at localhost:9092
 */
@EnableEurekaClient
@SpringBootApplication
@EnableBinding(Source.class)
public class HelloKafkaProducerApplication {
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(HelloKafkaProducerApplication.class);
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		
		SpringApplication.run(HelloKafkaProducerApplication.class, args);
	}
	
	/**
	 * Timer message source.
	 *
	 * @return the message source
	 */
	@Bean
	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "2000", maxMessagesPerPoll = "1"))
	public MessageSource<Event> timerMessageSource() {
		logger.info("SENDING EVENT");
		return () -> MessageBuilder.withPayload(new Event(UUID.randomUUID().toString(),"CRITICAL", ZonedDateTime.now().toInstant().toEpochMilli())).build();
	}
}