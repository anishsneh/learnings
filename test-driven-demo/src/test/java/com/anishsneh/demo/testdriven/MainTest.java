package com.anishsneh.demo.testdriven;

import org.junit.Assert;
import org.junit.Test;

/**
 * This class is used to trigger as such not testing much. Asserts true if call succeeds.
 * 
 * @author Anish Sneh
 */
public class MainTest {

	/**
	 * Test main.
	 */
	@Test
	public void testMain() {
		Main.main(new String[]{"Dummy"});
		Assert.assertTrue(true);
	}
}
