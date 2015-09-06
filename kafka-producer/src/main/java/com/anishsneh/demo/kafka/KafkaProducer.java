package com.anishsneh.demo.kafka;

import java.util.Properties;
import java.util.UUID;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * The Class KafkaProducer.
 * 
 * @author Anish Sneh
 */
public class KafkaProducer {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws InterruptedException {

		final Properties props = new Properties();

		props.put("metadata.broker.list", "server01:9092,server02:9092");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("request.required.acks", "1");

		final ProducerConfig config = new ProducerConfig(props);

		final Producer<String, String> producer = new Producer<String, String>(config);

		final String topic = "kafka-demo-topic";

		for (int i = 1; i <= 1000; i++) {
			final String msg = "UUID: " + UUID.randomUUID().toString() + "; AT: " + System.currentTimeMillis();
			System.out.println(msg);
			final KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, String.valueOf(i), msg);
			producer.send(data);
			Thread.sleep(2000L);
		}
		producer.close();
	}
}
