package com.anishsneh.demo.hbase.phoenix.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class DbUtil.
 * 
 * For the demo cluster setup given in http://www.anishsneh.com/2014/09/hbase-phoenix.html user following JDBC URL:
 * 
 * 		JDBC_CONNECTION_URL = "jdbc:phoenix:server01:2281"
 * 
 * For local standalone HBase use:
 * 
 * 		JDBC_CONNECTION_URL = "jdbc:phoenix:localhost:2181"
 */
public class DbUtil {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(DbUtil.class);
	
	/** The Constant JDBC_CONNECTION_URL. */
	public static final String JDBC_CONNECTION_URL = "jdbc:phoenix:localhost:2181";
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public static final Connection getConnection(){		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(JDBC_CONNECTION_URL);
		} 
		catch (final SQLException ex) {
			logger.error("Failed to get HBase connection from url: {}", JDBC_CONNECTION_URL, ex);
		}
		return conn;
	}
	
}
