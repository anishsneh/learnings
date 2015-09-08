package com.anishsneh.demo.testdriven.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.thirdparty.sample.User;
import com.anishsneh.demo.thirdparty.sample.UserDao;

/**
 * The Class UserService.
 * 
 * @author Anish Sneh
 */
public class UserService {
    
    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    /** The user dao. */
    private UserDao userDao;

    /**
     * Sets the user dao.
     *
     * @param userDao the new user dao
     */
    public final void setUserDao(final UserDao userDao) {
        logger.info("Setting userDao");
        this.userDao = userDao;
    }

    /**
     * Adds the user.
     *
     * @param user the user
     * @return true, if successful
     */
    public final boolean addUser(final User user) {
        logger.info("Adding user: {}", user);
        return userDao.addUser(user);
    }

    /**
     * Gets the user.
     *
     * @param login the login
     * @return the user
     */
    public final User getUser(final String login) {
        logger.info("Getting user with login: {}", login);
        return userDao.getUser(login);
    }

    /**
     * Removes the user.
     *
     * @param login the login
     * @return true, if successful
     */
    public final boolean removeUser(final String login) {
        logger.info("Removing user with login: {}", login);
        return userDao.removeUser(login);
    }

    /**
     * Gets the users.
     *
     * @return the users
     */
    public final List<User> getUsers() {
        logger.info("Getting all users");
        // Dummy code to violate PMD
        if (true) {
        } else {
            final UserDao s = null;
            s.getUsers();
        }

        // Dummy code to violate FINDBUGS
        String a = new String("eee");
        String b = new String("bbb");
        if (a == b) {
            System.out.println("dd");
        }
        return userDao.getUsers();
    }
}
