package com.anishsneh.demo.cassandra.unit;

import java.io.IOException;
import java.util.Iterator;

import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.assertj.core.api.Assertions;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.util.ConnectionUtil;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * The Class CustomYamlConfigTest.
 * 
 * @author Anish Sneh
 * 
 */
public class CustomYamlConfigTest {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CustomYamlConfigTest.class);

	/**
	 * Setup.
	 *
	 * @throws TTransportException the t transport exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 * @throws ConfigurationException the configuration exception
	 */
	@Before
	public void setup() throws TTransportException, IOException, InterruptedException, ConfigurationException {
		ConnectionUtil.waitForMillis(ConnectionUtil.DEFAULT_COOLING_OFF_DURATION);
		EmbeddedCassandraServerHelper.startEmbeddedCassandra(ConnectionUtil.CASSANDRA_CONFIG_YAML);
		final me.prettyprint.hector.api.Cluster cluster = me.prettyprint.hector.api.factory.HFactory.getOrCreateCluster(ConnectionUtil.DEFAULT_CLUSTER_NAME, new me.prettyprint.cassandra.service.CassandraHostConfigurator(ConnectionUtil.RPC_HOST + ":" + ConnectionUtil.RPC_PORT));
		Assertions.assertThat(cluster.getConnectionManager().getActivePools().size()).isEqualTo(1);
		final me.prettyprint.hector.api.ddl.KeyspaceDefinition keyspaceDefinition = cluster.describeKeyspace(ConnectionUtil.SYSTEM_KEY_SPACE_NAME);
		Assertions.assertThat(keyspaceDefinition).isNotNull();
		Assertions.assertThat(keyspaceDefinition.getReplicationFactor()).isEqualTo(1);
	}
	
	/**
	 * Cleanup.
	 */
	@After
	public void cleanup(){
		ConnectionUtil.waitForMillis(ConnectionUtil.DEFAULT_COOLING_OFF_DURATION);
	}

	/**
	 * Test custome cassandra config.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCustomeCassandraConfig() throws Exception {
		final Cluster dataCluster = Cluster.builder().addContactPoints(ConnectionUtil.CONTACT_POINT).withPort(ConnectionUtil.NATIVE_TRANSPORT_PORT).build();
		Assertions.assertThat(dataCluster).isNotNull();
	}

	/**
	 * Test load and select data.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testLoadAndSelectData() throws Exception {
		final Cluster dataCluster = Cluster.builder().addContactPoints(ConnectionUtil.CONTACT_POINT).withPort(ConnectionUtil.NATIVE_TRANSPORT_PORT).build();
		createKeySpace(dataCluster, ConnectionUtil.USER_KEY_SPACE_NAME);
		loadData(dataCluster, ConnectionUtil.USER_KEY_SPACE_NAME);		
		final Session session = dataCluster.connect(ConnectionUtil.USER_KEY_SPACE_NAME);
		final ResultSet result = session.execute("SELECT * FROM " + ConnectionUtil.USER_COLUMN_FAMILY_NAME + " WHERE id='USR0000001'");
		final Iterator<Row> resultIterator = result.iterator();
		Assertions.assertThat(resultIterator.hasNext()).isTrue();
		final Row row = resultIterator.next();
		final String login = row.getString("login");
		final String fullName = row.getString("full_name");
		final String countryCode = row.getString("country_code");		
		Assertions.assertThat(login).isEqualTo("anishsneh");
		Assertions.assertThat(fullName).isEqualTo("Anish Sneh");
		Assertions.assertThat(countryCode).isEqualTo("IN");
	}
	
	/**
	 * Creates the key space.
	 *
	 * @param dataCluster the data cluster
	 * @param keySpaceName the key space name
	 */
	private void createKeySpace(final Cluster dataCluster, final String keySpaceName) {
		logger.info("clusterName: {}", dataCluster.getClusterName());
		final Session session = dataCluster.connect();
		Assertions.assertThat(session).isNotNull();
		session.execute("CREATE KEYSPACE IF NOT EXISTS " + keySpaceName + " WITH replication " +  "= {'class':'SimpleStrategy', 'replication_factor':1};");
		session.close();
	}
	
	/**
	 * Load data.
	 *
	 * @param dataCluster the data cluster
	 * @param keySpaceName the key space name
	 */
	private void loadData(final Cluster dataCluster, final String keySpaceName){
		final Session session = dataCluster.connect(keySpaceName);
		Assertions.assertThat(session).isNotNull();
		session.execute("USE " + keySpaceName + ";");
		session.execute("CREATE TABLE IF NOT EXISTS " + ConnectionUtil.USER_COLUMN_FAMILY_NAME + " (id varchar, login varchar, full_name varchar, country_code varchar, PRIMARY KEY(id));");
		session.execute("INSERT INTO " + ConnectionUtil.USER_COLUMN_FAMILY_NAME + " (id, login, full_name, country_code) values('USR0000001', 'anishsneh', 'Anish Sneh', 'IN');");
		session.execute("INSERT INTO " + ConnectionUtil.USER_COLUMN_FAMILY_NAME + " (id, login, full_name, country_code) values('USR0000002', 'rakeshk', 'Rakesh K', 'UK');");
		session.execute("INSERT INTO " + ConnectionUtil.USER_COLUMN_FAMILY_NAME + " (id, login, full_name, country_code) values('USR0000003', 'ballys', 'Bally S', 'US');");
		session.execute("INSERT INTO " + ConnectionUtil.USER_COLUMN_FAMILY_NAME + " (id, login, full_name, country_code) values('USR0000004', 'yogeshd', 'Yogesh D', 'US');");
		session.close();
	}
}
