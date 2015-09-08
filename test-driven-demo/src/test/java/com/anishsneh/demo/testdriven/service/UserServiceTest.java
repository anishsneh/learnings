package com.anishsneh.demo.testdriven.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.thirdparty.sample.User;
import com.anishsneh.demo.thirdparty.sample.UserDao;

/**
 * This class tests user service, it mocks underlying third party UserDao API
 * 
 * @author Anish Sneh
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	
	/** The dummy user. */
	final User dummyUser = createDummyUser();
	
	/** The dummy users. */
	final List<User> dummyUsers = createDummyUsers();
	
	/** The mock user dao. */
	@Mock
	private UserDao mockUserDao;
	
	/** The user service. */
	private UserService userService;		 

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		logger.info("Initializing test");
		setupMocks();
		userService = new UserService();		
		userService.setUserDao(mockUserDao);		
	}
	
	/**
	 * Setup mocks. This method is used to setup underlying third party mocked UserDao API
	 */
	private void setupMocks(){
		
		Mockito.when(mockUserDao.getUser(Mockito.eq(dummyUser.getLogin()))).thenReturn(dummyUser);
		Mockito.when(mockUserDao.addUser(Mockito.eq(dummyUser))).thenReturn(true);
		Mockito.when(mockUserDao.removeUser(Mockito.eq(dummyUser.getLogin()))).thenReturn(true);
		Mockito.when(mockUserDao.getUsers()).thenReturn(dummyUsers);
	}
	
	/**
	 * Cleanup.
	 */
	@After
	public void cleanup() {
		logger.info("Cleaning up test");
	}

	/**
	 * Test add user.
	 */
	@Test
	public void testAddUser() {
		logger.info("================================= ADDING USER =================================");
		Assert.assertNotNull(userService);
		final boolean result = userService.addUser(dummyUser);
		Mockito.verify(mockUserDao).addUser(dummyUser);		
		Assert.assertTrue(result);
	}
	
	/**
	 * Test get user.
	 */
	@Test
	public void testGetUser() {
		logger.info("================================= GETTING USER =================================");
		final User user = userService.getUser(dummyUser.getLogin());
		Mockito.verify(mockUserDao).getUser(dummyUser.getLogin());
		Assert.assertEquals(dummyUser, user);
	}
	
	/**
	 * Test remove user.
	 */
	@Test
	public void testRemoveUser() {
		logger.info("================================= REMOVING USER =================================");
		final boolean result = userService.removeUser(dummyUser.getLogin());
		Mockito.verify(mockUserDao).removeUser(dummyUser.getLogin());	
		Assert.assertTrue(result);
	}
	
	/**
	 * Test get users.
	 */
	@Test
	public void testGetUsers() {
		logger.info("================================= GETTING ALL USERS =================================");
		final List<User> users = userService.getUsers();
		Mockito.verify(mockUserDao).getUsers();	
		Assert.assertNotNull(users);
		Assert.assertEquals(dummyUsers.size(), users.size());
	}
	
	/**
	 * Creates the dummy user.
	 *
	 * @return the user
	 */
	private User createDummyUser(){
		return new User("USER", "username", "user@abcdef.ghi", 30, "IN");
	}
	
	/**
	 * Creates the dummy users.
	 *
	 * @return the list
	 */
	private List<User> createDummyUsers(){
		
		final List<User> users = new ArrayList<>();
		final User dummyUser_01 = new User("USER_01", "username_01", "user_01@abcdef.ghi", 30, "IN");
		final User dummyUser_02 = new User("USER_02", "username_02", "user_02@abcdef.ghi", 30, "IN");
		final User dummyUser_03 = new User("USER_03", "username_03", "user_03@abcdef.ghi", 30, "IN");
		users.add(dummyUser_01);
		users.add(dummyUser_02);
		users.add(dummyUser_03);
		return users;
	}

}
