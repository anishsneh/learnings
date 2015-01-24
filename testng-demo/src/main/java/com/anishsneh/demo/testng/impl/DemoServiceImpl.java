package com.anishsneh.demo.testng.impl;

import com.anishsneh.demo.testng.DemoService;

/**
 * The Class DemoServiceImpl.
 * 
 * @author Anish Sneh
 */
public class DemoServiceImpl implements DemoService {

	/* (non-Javadoc)
	 * @see com.anishsneh.demo.testng.DemoService#add(int, int)
	 */
	public int add(int a, int b) {
		return a + b;
	}

	/* (non-Javadoc)
	 * @see com.anishsneh.demo.testng.DemoService#multiply(int, int)
	 */
	public int multiply(int a, int b) {
		return a * b;
	}

	/* (non-Javadoc)
	 * @see com.anishsneh.demo.testng.DemoService#divide(int, int)
	 */
	public int divide(int a, int b) {
		return a / b;
	}

	/* (non-Javadoc)
	 * @see com.anishsneh.demo.testng.DemoService#subtract(int, int)
	 */
	public int subtract(int a, int b) {
		return a - b;
	}

}
