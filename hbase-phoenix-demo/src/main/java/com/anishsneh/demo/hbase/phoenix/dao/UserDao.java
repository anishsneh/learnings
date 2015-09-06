package com.anishsneh.demo.hbase.phoenix.dao;

import java.util.List;

import com.anishsneh.demo.hbase.phoenix.vo.UserVo;

/**
 * The Interface UserDao.
 * 
 * @author Anish Sneh
 */
public interface UserDao {

	/**
	 * Inits the Dao.
	 */
	public void init();
	
	/**
	 * Creates the user.
	 *
	 * @param userVo the user vo
	 * @return the int
	 */
	public int createUser(final UserVo userVo);
	
	/**
	 * Gets the user by id.
	 *
	 * @param id the id
	 * @return the user by id
	 */
	public UserVo getUserById(final String id);
	
	/**
	 * Gets the user by gender.
	 *
	 * @param gender the gender
	 * @return the user by gender
	 */
	public List<UserVo> getUserByGender(final String gender);
	
	/**
	 * Gets the user.
	 *
	 * @param query the query
	 * @return the user
	 */
	public List<UserVo> getUser(final String query);
	
	/**
	 * Delete user.
	 *
	 * @param userVo the user vo
	 * @return the int
	 */
	public int deleteUser(final UserVo userVo);
	
}
