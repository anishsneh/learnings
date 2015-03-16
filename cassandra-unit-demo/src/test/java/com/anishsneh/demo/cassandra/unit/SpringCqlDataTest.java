package com.anishsneh.demo.cassandra.unit;

import java.util.Iterator;

import org.assertj.core.api.Assertions;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.anishsneh.demo.util.ConnectionUtil;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * The Class SpringCqlDataTest.
 * 
 * @author Anish Sneh
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ CassandraUnitTestExecutionListener.class })
@CassandraDataSet(value = { "data/cql/data.cql" })
@EmbeddedCassandra
public class SpringCqlDataTest {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(SpringCqlDataTest.class);
	
	/**
	 * Cleanup.
	 */
	@After
	public void cleanup(){
		ConnectionUtil.waitForMillis(ConnectionUtil.DEFAULT_COOLING_OFF_DURATION);
	}
	
	/**
	 * Test select data.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSelectData() throws Exception {
		
		final Cluster cluster = Cluster.builder().addContactPoints(ConnectionUtil.CONTACT_POINT).withPort(ConnectionUtil.NATIVE_TRANSPORT_PORT).build();
		final Session session = cluster.connect(ConnectionUtil.USER_KEY_SPACE_NAME);
		logger.info("Connecting to embedded cassandra cluster contactPoint:{}, port:{}, keySpace:{}", new Object[] {ConnectionUtil.CONTACT_POINT, ConnectionUtil.NATIVE_TRANSPORT_PORT, ConnectionUtil.USER_KEY_SPACE_NAME});
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
		session.close();
		cluster.close();
	}
}
