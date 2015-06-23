package com.anishsneh.demo.ogm.infinispan.ejb;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.ogm.infinispan.api.EventManager;
import com.anishsneh.demo.ogm.infinispan.api.dto.Event;

/**
 * The Class EventManagerTest.
 * 
 * $ mvn clean install -Dts -Pwildfly-managed-arquillian
 * 
 * @author Anish Sneh
 * 
 */
@RunWith(Arquillian.class)
public class EventManagerTest {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(EventManagerTest.class);

	/** The event manager. */
	@EJB
	private EventManager eventManager;
	
	/** The testable ear file path. */
	private static final String TESTABLE_EAR_FILE_PATH = "../hibernate-ogm-infinispan-demo-ear/target/hibernate-ogm-infinispan-demo.ear";
	
	/**
	 * Creates the deployment.
	 *
	 * @return the enterprise archive
	 */
	@Deployment
    public static EnterpriseArchive createDeployment() {
		logger.info("=========================== CREATING ARCHIVE FOR ARQUILLIAN ===========================");
		final JavaArchive testArchive = ShrinkWrap.create(JavaArchive.class, "test.jar").addClass(EventManagerTest.class);
		final File deployableArchive = new File(TESTABLE_EAR_FILE_PATH);
		logger.info("deployableArchive timestamp: " + deployableArchive.lastModified());
        return ShrinkWrap.createFromZipFile(EnterpriseArchive.class, deployableArchive)
        		.addAsLibraries(testArchive);
    }
	
	/**
	 * Test add event.
	 */
	@Test
	@InSequence(1)
	public void testAddEvent() {
		logger.info("=========================== ADDING EVENT ===========================");
		logger.info("CURRENT>>" + new File("").getAbsolutePath());
		logger.info("eventManager: " + eventManager);
		final List<Event> eventListBefore = eventManager.getEventsBySeverity("MAJOR");
		Assert.assertNotNull(eventListBefore);
		final int sizeBefore = eventListBefore.size();
		final Event eventResult = eventManager.addEvent(new Event(UUID.randomUUID().toString(), "MAJOR", "UPLINK_RATE", 200));
		Assert.assertNotNull(eventResult);
		final List<Event> eventListAfter = eventManager.getEventsBySeverity("MAJOR");
		Assert.assertNotNull(eventListAfter);
		final int sizeAfter = eventListAfter.size();
		Assert.assertTrue(sizeAfter > sizeBefore);
	}
	
	/**
	 * Test get events by severity.
	 */
	@Test
	@InSequence(2)
	public void testGetEventsBySeverity() {
		logger.info("=========================== GETTING EVENTS BY SEVERITY ===========================");
		logger.info("eventManager: " + eventManager);
		eventManager.addEvent(new Event(UUID.randomUUID().toString(), "MAJOR", "UPLINK_RATE", 200));
		eventManager.addEvent(new Event(UUID.randomUUID().toString(), "CRITICAL", "UPLINK_RATE", 10));
		eventManager.addEvent(new Event(UUID.randomUUID().toString(), "MINOR", "DOWNLINK_RATE", 500));
		eventManager.addEvent(new Event(UUID.randomUUID().toString(), "CRITICAL", "THROUGHPUT", 20));
		final List<Event> eventList = eventManager.getEventsBySeverity("CRITICAL");
		Assert.assertNotNull(eventList);
		logger.info("event list size: " + eventList.size());
		Assert.assertTrue(eventList.size() > 0);
	}
	
	/**
	 * Test get events by time.
	 */
	@Test
	@InSequence(3)
	public void testGetEventsByTime() {
		logger.info("=========================== GETTING EVENTS BY TIME ===========================");
		logger.info("eventManager: " + eventManager);
		eventManager.addEvent(new Event(UUID.randomUUID().toString(), "MAJOR", "UPLINK_RATE", 200));
		eventManager.addEvent(new Event(UUID.randomUUID().toString(), "CRITICAL", "UPLINK_RATE", 10));
		eventManager.addEvent(new Event(UUID.randomUUID().toString(), "MINOR", "DOWNLINK_RATE", 500));
		eventManager.addEvent(new Event(UUID.randomUUID().toString(), "CRITICAL", "THROUGHPUT", 20));
		final List<Event> eventList = eventManager.getEventsByTime(10);
		Assert.assertNotNull(eventList);
		logger.info("event list size: " + eventList.size());
		Assert.assertTrue(eventList.size() > 0);
	}
	
	/**
	 * Test remove events.
	 */
	@Test
	@InSequence(4)
	public void testRemoveEvents() {
		logger.info("=========================== REMOVING EVENTS ===========================");
		logger.info("eventManager: " + eventManager);
		final List<Event> eventListBefore = eventManager.getEventsBySeverity("CRITICAL");
		Assert.assertNotNull(eventListBefore);
		final int sizeBefore = eventListBefore.size();
		final int deletionCount = eventManager.removeEvents("CRITICAL");
		Assert.assertTrue(deletionCount > 0);
		final List<Event> eventListAfter = eventManager.getEventsBySeverity("CRITICAL");
		Assert.assertNotNull(eventListAfter);
		final int sizeAfter = eventListAfter.size();
		Assert.assertTrue(sizeAfter < sizeBefore);
	}
}
