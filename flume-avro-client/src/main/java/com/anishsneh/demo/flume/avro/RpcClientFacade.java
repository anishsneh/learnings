package com.anishsneh.demo.flume.avro;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.event.EventBuilder;

/**
 * The Class RpcClientFacade.
 * 
 * @author Anish Sneh
 */
public class RpcClientFacade {

	/** The client. */
	private RpcClient client;
	
	/** The hostname. */
	private String hostname;
	
	/** The port. */
	private int port;

	/**
	 * Inits the.
	 *
	 * @param hostname the hostname
	 * @param port the port
	 */
	public void init(final String hostname, final int port) {
		// Setup the RPC connection
		this.hostname = hostname;
		this.port = port;
		this.client = RpcClientFactory.getDefaultInstance(hostname, port);
	}

	/**
	 * Send data to flume.
	 *
	 * @param data the data
	 */
	public void sendDataToFlume(final String data) {
		// Create a Flume Event object that encapsulates the sample data
		final Map<String, String> map = new HashMap<String, String>();
		map.put("header_1", "Demo header 1");
		map.put("header_2", "Demo header 2");
		final Event event = EventBuilder.withBody(data, Charset.forName("UTF-8"), map);

		// Send the event
		try {
			client.append(event);
		} catch (final EventDeliveryException e) {
			// clean up and recreate the client
			client.close();
			client = null;
			client = RpcClientFactory.getDefaultInstance(hostname, port);
		}
	}

	/**
	 * Clean up.
	 */
	public void cleanUp() {
		// Close the RPC connection
		client.close();
	}

}
