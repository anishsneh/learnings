package com.anishsneh.demo.spring.microservice.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class User.
 * 
 * @author Anish Sneh
 */
@XmlRootElement
public class User {
	
    /** The key. */
    private String key;
    
    /** The description. */
    private String description;

    /**
     * Instantiates a new user.
     */
    public User() {
    }

    /**
     * Instantiates a new user.
     *
     * @param key the key
     * @param description the description
     */
    public User(final String key, final String description) {
        this.key = key;
        this.description = description;
    }

    /**
     * Gets the key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key the new key
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(final String description) {
        this.description = description;
    }
}