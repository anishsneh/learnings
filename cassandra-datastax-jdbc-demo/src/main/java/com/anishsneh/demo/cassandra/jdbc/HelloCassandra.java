package com.anishsneh.demo.cassandra.jdbc;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * The Class HelloCassandra.
 * 
 * @author Anish Sneh
 */
public class HelloCassandra {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(HelloCassandra.class);

	/** The Constant CONTACT_POINT. */
	public static final String CONTACT_POINT = "127.0.0.1";
	
	/** The Constant PORT. */
	public static final int PORT = 9042;
	
	/** The Constant KEYSPACE_NAME. */
	public static final String KEYSPACE_NAME = "hello_keyspace";
	
	/** The Constant COLUMN_FAMILY_NAME. */
	public static final String COLUMN_FAMILY_NAME = "hello_users";
	
	/** The Constant REPLICATION_FACTOR. */
	public static final int REPLICATION_FACTOR = 1;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		logger.info("Started HelloCassandra application at: " + System.currentTimeMillis());
		final HelloCassandra helloCassandra = new HelloCassandra();
		helloCassandra.init(KEYSPACE_NAME);
		final Session session = helloCassandra.getSession(KEYSPACE_NAME);
		logger.info("Connected to Cassandra cluster with session: {}", session);
		helloCassandra.createColumnFamily(session, COLUMN_FAMILY_NAME);
		helloCassandra.populateColumnFamily(session, COLUMN_FAMILY_NAME);
		logger.info("==============================================================");
		helloCassandra.printColumnFamily(session, COLUMN_FAMILY_NAME);
		logger.info("==============================================================");
	}

	/**
	 * Creates the keyspace.
	 *
	 * @param session the session
	 * @param keyspace the keyspace
	 */
	private void createKeyspace(final Session session, final String keyspace) {
		session.execute("CREATE KEYSPACE IF NOT EXISTS " + KEYSPACE_NAME + " WITH replication={'class' : 'SimpleStrategy', 'replication_factor':" + REPLICATION_FACTOR + "}");
	}

	/**
	 * Creates the column family.
	 *
	 * @param session the session
	 * @param columnFamily the column family
	 */
	private void createColumnFamily(final Session session, final String columnFamily) {
		session.execute("CREATE TABLE IF NOT EXISTS " + columnFamily + "(id varchar, login varchar, full_name varchar, country_code varchar, PRIMARY KEY(id))");
	}

	/**
	 * Populate column family.
	 *
	 * @param session the session
	 * @param columnFamily the column family
	 */
	private void populateColumnFamily(final Session session, final String columnFamily) {
		session.execute("INSERT INTO " + columnFamily + "(id, login, full_name, country_code) values('USR0000001', 'anishsneh', 'Anish Sneh', 'IN')");
		session.execute("INSERT INTO " + columnFamily + "(id, login, full_name, country_code) values('USR0000002', 'rakeshk', 'Rakesh K', 'UK')");
		session.execute("INSERT INTO " + columnFamily + "(id, login, full_name, country_code) values('USR0000003', 'ballys', 'Bally S', 'US')");
		session.execute("INSERT INTO " + columnFamily + "(id, login, full_name, country_code) values('USR0000004', 'yogeshd', 'Yogesh D', 'US')");
	}

	/**
	 * Prints the column family.
	 *
	 * @param session the session
	 * @param columnFamily the column family
	 */
	private void printColumnFamily(final Session session, final String columnFamily) {
		final ResultSet result = session.execute("SELECT * FROM " + columnFamily);
		final Iterator<Row> resultIterator = result.iterator();
		while (resultIterator.hasNext()) {
			final Row row = resultIterator.next();
			logger.info("id={}; login={}; full_name={}; country_code={};", row.getString("id"), row.getString("login"), row.getString("full_name"), row.getString("country_code"));
		}
	}

	/**
	 * Gets the session.
	 *
	 * @param keyspace the keyspace
	 * @return the session
	 */
	private Session getSession(final String keyspace) {
		final Cluster cluster = Cluster.builder().addContactPoints(CONTACT_POINT).withPort(PORT).build();
		final Session session = keyspace == null ? cluster.connect() : cluster.connect(keyspace);
		return session;
	}

	/**
	 * Inits the system
	 *
	 * @param keyspace the keyspace
	 */
	private void init(final String keyspace) {
		createKeyspace(getSession(null), keyspace);
	}
}
