package com.anishsneh.demo.spring.microservice.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Users.
 * 
 * @author Anish Sneh
 */
public class Users {

	/** The users. */
	private List<User> users;

	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	public List<User> getUsers() {
		if (null == users) {
			users = new ArrayList<>();
		}
		return users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(final List<User> users) {
		this.users = users;
	}

}
