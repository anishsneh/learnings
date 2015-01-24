package com.anishsneh.demo.testng;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class SampleDataProvider {

	@DataProvider(name = "DataProvider001")
	public static Object[][] testSumInputExternal001() {
		return new Object[][] { { 10, 10 }, { 20, 20 }, { 30, 30 } };
	}
	
	@DataProvider(name = "DataProvider002")
	public static Object[][] testSumInputExternal002() {
		return new Object[][] { { 100, 100 }, { 200, 200 }, { 300, 300 } };
	}
	
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
	
	@DataProvider(name = "DataProvider004")
	public static Object[][] testSumInputExternal004() {
		return new Object[][] { { 10000, 10000 }, { 20000, 20000 }, { 30000, 30000 } };
	}

}
