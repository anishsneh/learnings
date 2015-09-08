package com.anishsneh.demo.testdriven.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.thirdparty.sample.User;

/**
 * The Class UserManager.
 * 
 * @author Anish Sneh
 */
public class UserManager {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);
    
    /** The user count. */
    private final int USER_COUNT = 10;

    /** The user service. */
    private UserService userService;

    /**
     * Sets the user service.
     *
     * @param userService the new user service
     */
    public final void setUserService(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Populate user store.
     */
    public final void populateUserStore() {
        
        for (int i = 0; i < USER_COUNT; i++) {
            logger.info("Populating users, adding user at index: {}", i);
            String countryCode = null;
            if (0 == i % 3) {
                countryCode = "US";
            } else if (1 == i % 3) {
                countryCode = "UK";
            } else {
                countryCode = "IN";
            }
            final User user = new User("User_" + i, "user_" + i, "user_" + i + "@abcdef.ghi", (i + 20), countryCode);
            userService.addUser(user);
        }
        logger.info("Populated total: {} users", USER_COUNT);
    }

    /**
     * Prints the users.
     */
    public final void printUsers() {        
        final List<User> users = userService.getUsers();
        logger.info("Printing total: {} users", users.size());
        for (final User user : users) {
            logger.info(user.toString());
        }
    }

}
