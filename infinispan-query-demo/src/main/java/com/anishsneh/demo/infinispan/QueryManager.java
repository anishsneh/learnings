package com.anishsneh.demo.infinispan;

import java.util.List;

import org.infinispan.Cache;
import org.infinispan.query.Search;
import org.infinispan.query.SearchManager;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class QueryManager.
 * 
 * @author Anish Sneh
 */
@SuppressWarnings("all")
public class QueryManager {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(QueryManager.class);

	/**
	 * Query cache_ equals.
	 *
	 * @param cache the cache
	 * @param name the name
	 */
	public static void queryCache_Equals(final Cache cache, final String name){		
						
		final QueryFactory qf = getQueryFactory(cache);
		final Query q = qf.from(Person.class).having("name").eq(name).toBuilder().build();
		logger.info(q.toString());
		final List<Person> list = q.list();		
		logger.info("Got total {} results for name {}", list.size(), name);
		logger.info("Got result: " + list.get(0).toString());
	}
	
	/**
	 * Query cache_ range.
	 *
	 * @param cache the cache
	 * @param from the from
	 * @param to the to
	 */
	public static void queryCache_Range(final Cache cache, final int from, final int to){	
		
		final QueryFactory qf = getQueryFactory(cache);
		final Query q = qf.from(Person.class).having("age").between(from, to).toBuilder().build();
		logger.info(q.toString());
		List<Person> list = q.list();		
		logger.info("Got total {} results for age between {} and {}", list.size(), from, to);		
	}
	
	/**
	 * Query cache_ like.
	 *
	 * @param cache the cache
	 * @param countryKey the country key
	 */
	public static void queryCache_Like(final Cache cache, final String countryKey){	
		
		final QueryFactory qf = getQueryFactory(cache);
		final Query q = qf.from(Person.class).having("country").like("%" + countryKey + "%").toBuilder().build();
		logger.info(q.toString());
		List<Person> list = q.list();		
		logger.info("Got total {} results for countryKey {}", list.size(), countryKey);		
	}
	
	/**
	 * Query cache_ in.
	 *
	 * @param cache the cache
	 * @param names the names
	 */
	public static void queryCache_In(final Cache cache, final String[] names){	
		
		final QueryFactory qf = getQueryFactory(cache);
		final Query q = qf.from(Person.class).having("name").in(names).toBuilder().build();
		logger.info(q.toString());
		List<Person> list = q.list();		
		logger.info("Got total {} results for names {}", list.size(), names);		
	}
	
	/**
	 * Gets the query factory.
	 *
	 * @param cache the cache
	 * @return the query factory
	 */
	private static QueryFactory getQueryFactory(final Cache cache){
		final SearchManager sm = Search.getSearchManager(cache); 
		final QueryFactory qf = sm.getQueryFactory();
		return qf;
	}
}
