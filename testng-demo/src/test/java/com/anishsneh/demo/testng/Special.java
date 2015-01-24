package com.anishsneh.demo.testng;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface Special.
 * 
 * @author Anish Sneh
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Special {
	
	/**
	 * Name.
	 *
	 * @return the string
	 */
	String name();
	
	/**
	 * File name.
	 *
	 * @return the string
	 */
	String fileName();
}
