package com.anishsneh.demo.infinispan.main;

import org.infinispan.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.infinispan.CacheManager;
import com.anishsneh.demo.infinispan.Person;
import com.anishsneh.demo.infinispan.QueryManager;

/**
 * The Class Main.
 * 
 * @author Anish Sneh
 */
public class Main {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		try {
			logger.info("=============================INIT_CACHE=============================" );
			CacheManager.init();
		} 
		catch (Exception e) {
			logger.error("Failed to init cache", e);
		}
		
		logger.info("=============================POPULATE_CACHE=============================" );
		CacheManager.populateCache();
		
		logger.info("=============================QUERING_CACHE=============================" );		
		final Cache<String, Person> cache = CacheManager.getCache();			
		QueryManager.queryCache_Equals(cache, "NAME_1");
		QueryManager.queryCache_Range(cache, 30, 50);
		QueryManager.queryCache_Like(cache, "IE");
		QueryManager.queryCache_In(cache, new String[]{ "NAME_1", "NAME_2", "NAME_3", "NAME_4", "NAME_5" });
		System.exit(0);
	}	
}
