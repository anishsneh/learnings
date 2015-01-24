package com.anishsneh.demo.testng.impl;

import com.anishsneh.demo.testng.DemoService;

public class DemoServiceImpl implements DemoService {

	public int add(int a, int b) {
		return a + b;
	}

	public int multiply(int a, int b) {
		return a * b;
	}

	public int divide(int a, int b) {
		return a / b;
	}

	public int subtract(int a, int b) {
		return a - b;
	}

}
