package com.anishsneh.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ConnectionUtil.
 * 
 * @author Anish Sneh
 * 
 */
public class ConnectionUtil {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ConnectionUtil.class);
	
	/** The Constant NATIVE_TRANSPORT_PORT. */
	public static final Integer NATIVE_TRANSPORT_PORT = 9142;
	
	/** The Constant CASSANDRA_CONFIG_YAML. */
	public static final String CASSANDRA_CONFIG_YAML = "cassandra.yaml";
	
	/** The Constant CONTACT_POINT. */
	public static final String CONTACT_POINT = "127.0.0.1";
	
	/** The Constant RPC_HOST. */
	public static final String RPC_HOST = "localhost";
	
	/** The Constant RPC_PORT. */
	public static final Integer RPC_PORT = 9175;
	
	/** The Constant SYSTEM_KEY_SPACE_NAME. */
	public static final String SYSTEM_KEY_SPACE_NAME = "system";
	
	/** The Constant DEFAULT_CLUSTER_NAME. */
	public static final String DEFAULT_CLUSTER_NAME = "demo_cluster";
	
	/** The Constant USER_KEY_SPACE_NAME. */
	public static final String USER_KEY_SPACE_NAME = "demo_keyspace";
	
	/** The Constant USER_COLUMN_FAMILY_NAME. */
	public static final String USER_COLUMN_FAMILY_NAME = "demo_table";
	
	/** The Constant DEFAULT_COOLING_OFF_DURATION. */
	public static final long DEFAULT_COOLING_OFF_DURATION = 10000L;
	
	/**
	 * Wait for millis.
	 *
	 * @param millis the millis
	 */
	public static void waitForMillis(final long millis){
		try {
			logger.info("Going to sleep for:{}ms", millis);
			Thread.sleep(millis);
			logger.info("Woke up after:{}ms", millis);
		} 
		catch (final InterruptedException e) {
			logger.error("Failed to sleep", e);
		}
		
	}
	
}
