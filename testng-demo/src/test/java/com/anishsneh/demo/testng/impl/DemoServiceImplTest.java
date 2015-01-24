package com.anishsneh.demo.testng.impl;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.anishsneh.demo.testng.DemoService;
import com.anishsneh.demo.testng.Special;
import com.anishsneh.demo.testng.impl.DemoServiceImpl;

/**
 * The Class DemoServiceImplTest.
 * 
 * @author Anish Sneh 
 */
public class DemoServiceImplTest {

	/** The service. */
	private DemoService service;

	/**
	 * Inits the.
	 */
	@BeforeClass
	public void init() {
		System.out.println("@BeforeClass: The annotated method will be run before the first test method in the current class is invoked.");
		System.out.println("init service");
		service = new DemoServiceImpl();
	}

	/**
	 * Test sum.
	 *
	 * @param a the a
	 * @param b the b
	 */
	@Test(groups = { "AAA" }, description = "Method data provider", dataProvider = "testSumInput")
	public void testSum(int a, int b) {
		int result = service.add(a, b);
		System.out.println("AAA RESULT: " + result);
		Assert.assertEquals(result, a + b);
	}
	
	/**
	 * Test sum external001.
	 *
	 * @param a the a
	 * @param b the b
	 */
	@Test(groups = { "BBB" }, description = "Class data provider", dataProviderClass = DataProvider.class, dataProvider = "DataProvider001")
	public void testSumExternal001(int a, int b) {
		int result = service.add(a, b);
		System.out.println("BBB RESULT: " + result);
		Assert.assertEquals(result, a + b);
	}
	
	/**
	 * Test sum external002.
	 *
	 * @param a the a
	 * @param b the b
	 */
	@Test(groups = { "CCC" }, description = "Class data provider", dataProviderClass = DataProvider.class, dataProvider = "DataProvider002")
	public void testSumExternal002(int a, int b) {
		int result = service.add(a, b);
		System.out.println("CCC RESULT: " + result);
		Assert.assertEquals(result, a + b);
	}
	
	/**
	 * Test sum external003.
	 *
	 * @param a the a
	 * @param b the b
	 */
	@Test(groups = { "DDD" }, description = "Class data provider", dataProviderClass = DataProvider.class, dataProvider = "DataProvider003")
	@Special(name = "ANISHSNEH", fileName = "src/test/resources/test.csv")
	public void testSumExternal003(int a, int b) {
		int result = service.add(a, b);
		System.out.println("DDD RESULT: " + result);
		Assert.assertEquals(result, a + b);
	}

	/**
	 * Test sum input.
	 *
	 * @return the object[][]
	 */
	@DataProvider
	public Object[][] testSumInput() {
		return new Object[][] { { 1, 1 }, { 2, 2 }, { 3, 3} };
	}
}
