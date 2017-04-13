package com.anishsneh.demo.spring.microservice.testdriven.integration;

import static com.jayway.restassured.RestAssured.when;

import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.anishsneh.demo.spring.microservice.testdriven.HelloTestDrivenApplication;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import static org.assertj.core.api.Assertions.assertThat;

import com.jayway.restassured.response.Response;

/**
 * The Class HelloTestDrivenResourceITest.
 * 
 * @author Anish Sneh
 * 
 * Use:
 * 	$ mvn integration-test -P integration
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HelloTestDrivenApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class HelloTestDrivenResourceITest {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(HelloTestDrivenResourceITest.class);

	/** The Constant EVENTS_RESOURCE. */
	private static final String EVENTS_RESOURCE = "/events";

	/** The server port. */
	@Value("${local.server.port}")
	private int serverPort;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		RestAssured.port = serverPort;
	}

	/**
	 * Test get events.
	 */
	@Test
	public void testGetEvents() {
		logger.info("Testing get events");
		final Response response = when()
		.get(EVENTS_RESOURCE)
		.then()
		.contentType(ContentType.JSON)
		.statusCode(HttpStatus.SC_OK).extract().response();
		final String jsonAsString = response.asString();
		logger.info(jsonAsString);
		List<Map<String,?>> eventsList = JsonPath.from(jsonAsString).get("events");
		logger.info(eventsList.size() + "");
		assertThat(eventsList.size()).isEqualTo(5);
	}
}
