package com.anishsneh.demo.cassandra.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The Class IntegrationSuite.
 * 
 * @author Anish Sneh
 */
@RunWith(Suite.class)
@SuiteClasses({ 
	CustomYamlConfigTest.class,
	SpringCqlDataTest.class, 
	JUnitRuleCqlDataTest.class
})
public class IntegrationSuite {

}
