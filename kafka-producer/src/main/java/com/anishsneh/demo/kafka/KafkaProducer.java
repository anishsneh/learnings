package com.anishsneh.demo.kafka;

import java.util.Properties;
import java.util.UUID;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducer {

	public static void main(String[] args) throws InterruptedException {

		Properties props = new Properties();

		props.put("metadata.broker.list", "server01:9092,server02:9092");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("request.required.acks", "1");

		ProducerConfig config = new ProducerConfig(props);

		Producer<String, String> producer = new Producer<String, String>(config);

		String topic = "kafka-demo-topic";

		for (int i = 1; i <= 1000; i++) {
			String msg = "UUID: " + UUID.randomUUID().toString() + "; AT: " + System.currentTimeMillis();
			System.out.println(msg);
			KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, String.valueOf(i), msg);
			producer.send(data);
			Thread.sleep(2000L);
		}
		producer.close();
	}
}
