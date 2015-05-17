package com.anishsneh.demo.hbase.phoenix;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.hbase.phoenix.dao.UserDao;
import com.anishsneh.demo.hbase.phoenix.dao.impl.UserDaoImpl;
import com.anishsneh.demo.hbase.phoenix.util.DbUtil;
import com.anishsneh.demo.hbase.phoenix.vo.UserVo;

/**
 * The Class HelloPhoenix.
 * 
 * NOTE: Before execution of this code HADOOP_HOME variable MUST BE pointing to valid HADOOP installations, hence it might not work on WINDOWS
 * 
 */
public class HelloPhoenix {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(HelloPhoenix.class);
	
	/** The Constant JDBC_CONNECTION_URL. */
	public static final String JDBC_CONNECTION_URL = "jdbc:phoenix:localhost:2181";
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		logger.info("Started HelloPhoenix at: " + System.currentTimeMillis());
		final UserDao userDao = new UserDaoImpl(DbUtil.getConnection());
		logger.info("Initializing sample data");
		userDao.init();
		userDao.createUser(new UserVo("USR0001", 20, "Male", "United States"));
		userDao.createUser(new UserVo("USR0002", 25, "Female", "India"));
		userDao.createUser(new UserVo("USR0003", 32, "Male", "United Kingdom"));
		userDao.createUser(new UserVo("USR0004", 67, "Female", "India"));
		userDao.createUser(new UserVo("USR0005", 34, "Male", "United States"));
		logger.info("Created users");		
		final List<UserVo> userDaoList = userDao.getUser("SELECT * FROM " + UserDaoImpl.TABLE_NAME);
		logger.info("Got total:{} users", userDaoList.size());
		for(final UserVo userVo : userDaoList){
			logger.info("=====================================================================");
			logger.info("USER INFO: {}", userVo.toString());
		}
		logger.info("=====================================================================");
	}
}
