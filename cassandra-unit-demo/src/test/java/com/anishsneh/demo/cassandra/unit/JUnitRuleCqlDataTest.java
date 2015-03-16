package com.anishsneh.demo.cassandra.unit;

import java.util.Iterator;

import org.assertj.core.api.Assertions;
import org.cassandraunit.CassandraCQLUnit;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.util.ConnectionUtil;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * The Class JUnitRuleCqlDataTest.
 * 
 * @author Anish Sneh
 * 
 */
public class JUnitRuleCqlDataTest {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(JUnitRuleCqlDataTest.class);
	
	/** The cassandra cql unit. */
	@Rule
    public CassandraCQLUnit cassandraCqlUnit = new CassandraCQLUnit(new ClassPathCQLDataSet("data/cql/data.cql"));
	
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
		
		logger.info("Selecting data loaded by CQL via JUnit Rule");
		final Session session = cassandraCqlUnit.session;
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
		logger.info("Closing session");
		session.close();
	}
}
