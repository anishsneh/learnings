package com.anishsneh.demo.testdriven;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.testdriven.service.UserManager;
import com.anishsneh.demo.testdriven.service.UserService;
import com.anishsneh.demo.thirdparty.sample.UserDao;
import com.anishsneh.demo.thirdparty.sample.UserDaoFactory;

/**
 * The Class Main.
 * 
 * To run
 * 
 * $ mvn clean install exec:java -Dexec.classpathScope=compile
 * 
 * @author Anish Sneh
 */
public class Main {
    
    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    
    /** The Constant DEFAULT_USER_TYPE. */
    private static final String DEFAULT_USER_TYPE = "DEMO_TYPE";

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(final String[] args) {
        triggerUserService();
    }

    /**
     * Trigger user service.
     */
    public static void triggerUserService() {
        logger.info("Triggering user service API");
        final UserDao userDao = UserDaoFactory.getUserDao(DEFAULT_USER_TYPE);
        final UserService userService = new UserService();
        userService.setUserDao(userDao);
        final UserManager userManager = new UserManager();
        userManager.setUserService(userService);
        userManager.populateUserStore();
        userManager.printUsers();
    }
}
