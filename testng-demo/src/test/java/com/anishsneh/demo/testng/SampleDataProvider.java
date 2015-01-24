package com.anishsneh.demo.testng;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

/**
 * The Class SampleDataProvider.
 * 
 * @author Anish Sneh
 */
public class SampleDataProvider {

	/**
	 * Test sum input external001.
	 *
	 * @return the object[][]
	 */
	@DataProvider(name = "DataProvider001")
	public static Object[][] testSumInputExternal001() {
		return new Object[][] { { 10, 10 }, { 20, 20 }, { 30, 30 } };
	}
	
	/**
	 * Test sum input external002.
	 *
	 * @return the object[][]
	 */
	@DataProvider(name = "DataProvider002")
	public static Object[][] testSumInputExternal002() {
		return new Object[][] { { 100, 100 }, { 200, 200 }, { 300, 300 } };
	}
	
	/**
	 * Test sum input external003.
	 *
	 * @param testMethod the test method
	 * @return the object[][]
	 */
	@DataProvider(name = "DataProvider003")
	public static Object[][] testSumInputExternal003(final Method testMethod) {
		System.out.println("Test method name: " + testMethod.getName());
		Special special = testMethod.getAnnotation(Special.class);
		if(null != special){
			System.out.println("Annotation name parameter: " + special.name());
		}
		else{
			System.out.println("Annotation not found");
		}
		return new Object[][] { { 1000, 1000 }, { 2000, 2000 }, { 3000, 3000 } };
	}
	
	/**
	 * Test sum input external004.
	 *
	 * @return the object[][]
	 */
	@DataProvider(name = "DataProvider004")
	public static Object[][] testSumInputExternal004() {
		return new Object[][] { { 10000, 10000 }, { 20000, 20000 }, { 30000, 30000 } };
	}

}
