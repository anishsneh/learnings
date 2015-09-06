package com.anishsneh.demo.hbase.phoenix.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.hbase.phoenix.dao.UserDao;
import com.anishsneh.demo.hbase.phoenix.vo.UserVo;

/**
 * The Class UserDaoImpl.
 * 
 * @author Anish Sneh
 */
public class UserDaoImpl implements UserDao{
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "DEMO_USERS";
	
	/** The connection. */
	private Connection connection;
		
	/**
	 * Instantiates a new user dao impl.
	 *
	 * @param connection the connection
	 */
	public UserDaoImpl(final Connection connection){
		this.connection = connection;	
	}
	
	/* (non-Javadoc)
	 * @see com.anishsneh.demo.hbase.phoenix.dao.UserDao#init()
	 */
	public void init(){
		try {
			final Statement statement = connection.createStatement();
			logger.info("Statement created from connection:{}", statement);
			statement.execute("DROP TABLE " + TABLE_NAME);
			statement.execute("CREATE TABLE " + TABLE_NAME + "(id VARCHAR NOT NULL PRIMARY KEY, age INTEGER, gender VARCHAR, birthplace VARCHAR)");
			connection.commit();
		} 
		catch (final SQLException ex) {
			logger.error("Failed to init db:{}", ex);
		}
	}

	/* (non-Javadoc)
	 * @see com.anishsneh.demo.hbase.phoenix.dao.UserDao#createUser(com.anishsneh.demo.hbase.phoenix.vo.UserVo)
	 */
	public int createUser(final UserVo userVo) {
		try {
			final Statement statement = connection.createStatement();
			logger.info("Statement created from connection:{}", statement);
			statement.execute("UPSERT INTO " + TABLE_NAME + "(id, age, gender, birthplace) VALUES ('" + userVo.getId() + "', " + userVo.getAge() + ", '" + userVo.getGender() + "', '" + userVo.getBirthplace() + "')");
			connection.commit();
		} 
		catch (final SQLException ex) {
			logger.error("Failed to create user:{}", ex);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.anishsneh.demo.hbase.phoenix.dao.UserDao#getUserById(java.lang.String)
	 */
	public UserVo getUserById(final String id) {
		UserVo userVo = null;
		try {
			final Statement statement = connection.createStatement();
			logger.info("Statement created from connection:{}", statement);
			final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = '" + id + "'");
			if(resultSet.next()){
				userVo = new UserVo(resultSet.getString("id"), resultSet.getInt("age"), resultSet.getString("gender"), resultSet.getString("birthplace"));
			}			
		} 
		catch (final SQLException ex) {
			logger.error("Failed to get user by id:{}", ex);
		}
		logger.info("User: " + userVo.toString());
		return userVo;
	}

	/* (non-Javadoc)
	 * @see com.anishsneh.demo.hbase.phoenix.dao.UserDao#getUserByGender(java.lang.String)
	 */
	public List<UserVo> getUserByGender(final String gender) {
		final List<UserVo> userVoList = new ArrayList<UserVo>();
		try {
			final Statement statement = connection.createStatement();
			logger.info("Statement created from connection:{}", statement);
			final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE gender = '" + gender + "'");
			while(resultSet.next()){
				final UserVo userVo = new UserVo(resultSet.getString("id"), resultSet.getInt("age"), resultSet.getString("gender"), resultSet.getString("birthplace"));
				userVoList.add(userVo);
			}			
		} 
		catch (final SQLException ex) {
			logger.error("Failed to get user by gender:{}", ex);
		}
		return userVoList;
	}

	/* (non-Javadoc)
	 * @see com.anishsneh.demo.hbase.phoenix.dao.UserDao#getUser(java.lang.String)
	 */
	public List<UserVo> getUser(final String query) {
		final List<UserVo> userVoList = new ArrayList<UserVo>();
		try {
			final Statement statement = connection.createStatement();
			logger.info("Statement created from connection:{}", statement);
			final ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				final UserVo userVo = new UserVo(resultSet.getString("id"), resultSet.getInt("age"), resultSet.getString("gender"), resultSet.getString("birthplace"));
				userVoList.add(userVo);
			}			
		} 
		catch (final SQLException ex) {
			logger.error("Failed to get user by query:{}", ex);
		}
		return userVoList;
	}

	/* (non-Javadoc)
	 * @see com.anishsneh.demo.hbase.phoenix.dao.UserDao#deleteUser(com.anishsneh.demo.hbase.phoenix.vo.UserVo)
	 */
	public int deleteUser(final UserVo userVo) {
		try {
			final Statement statement = connection.createStatement();
			logger.info("Statement created from connection:{}", statement);
			statement.execute("DELETE FROM " + TABLE_NAME + " WHERE id = '" + userVo.getId() + "'");
			connection.commit();
		} 
		catch (final SQLException ex) {
			logger.error("Failed to create user:{}", ex);
		}
		return 0;
	}
}
