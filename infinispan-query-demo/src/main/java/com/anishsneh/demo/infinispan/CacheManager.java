package com.anishsneh.demo.infinispan;

import java.io.IOException;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class CacheManager.
 * 
 * @author Anish Sneh
 */
@SuppressWarnings("all")
public class CacheManager {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(QueryManager.class);
	
	/** The cache manager. */
	private static DefaultCacheManager cacheManager;
	
	/** The cache. */
	private static Cache<String, Person> cache;

	/**
	 * Instantiates a new cache manager.
	 */
	private CacheManager() {
	}

	/**
	 * Gets the cache.
	 *
	 * @return the cache
	 */
	public static Cache<String, Person> getCache() {
		return cache;
	}

	/**
	 * Inits the.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void init() throws IOException {
		logger.info("Initializing the cache");
		System.setProperty("nodeName", "NODE_01");
		cacheManager = new DefaultCacheManager("infinispan.xml");
		cache = cacheManager.getCache("demoCache");
		cache.addListener(new CacheListener());
	}
	
	/**
	 * Populate cache.
	 */
	public static void populateCache() {
		logger.info("Populating cache");
		Cache<String, Person> cache = CacheManager.getCache();
		for(int i=1; i<=100; i++){
			Person person = null;
			if(0 == i%2){
				person = new Person("NAME_" + i, i, "IN_" + i,System.currentTimeMillis());
			}
			else{
				person = new Person("NAME_" + i, i, "IE_" + i,System.currentTimeMillis());
			}
			cache.put("P_" + i, person);
			try {
				Thread.sleep(2);
			}
			catch (Exception e) {
				logger.error("Failed to insert data to cache", e);
			}
		}
		logger.info("Cache populated");
	}
	
	
}
