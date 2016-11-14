package com.anishsneh.demo.spring.microservice.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.anishsneh.demo.spring.microservice.vo.User;
import com.anishsneh.demo.spring.microservice.vo.Users;

/**
 * The Class UserUtil.
 * 
 * @author Anish Sneh
 */
public class UserUtil {

    /**
     * Gets the users.
     *
     * @return the users
     */
    public static Users getUsers() {
    	
    	final Users users = new Users();
    	final List<User> usersList = Collections.synchronizedList(new ArrayList<User>());
    	usersList.add(new User("HW001", "Hello: " + UUID.randomUUID().toString()));
    	usersList.add(new User("HW002", "World: " + UUID.randomUUID().toString()));
    	users.setUsers(usersList);
        return users;
    }
}
