package com.anishsneh.demo.cep.esper.helper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;

import com.anishsneh.demo.cep.esper.subscriber.AlarmEventSubscriber;
import com.anishsneh.demo.cep.esper.util.EPServiceUtil;
import com.anishsneh.demo.spring.framework.ApplicationLogger;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;

/**
 * The Class AlarmEventManager.
 * 
 * @author Anish Sneh
 */
public class AlarmEventManager {
	
	/** The logger. */
	@ApplicationLogger 
	private Logger logger;
	
	/** The critical alarm event subscriber. */
	private AlarmEventSubscriber criticalAlarmEventSubscriber;
	
	/** The major alarm event subscriber. */
	private AlarmEventSubscriber majorAlarmEventSubscriber;
	
	/** The minor alarm event subscriber. */
	private AlarmEventSubscriber minorAlarmEventSubscriber;
	
	/** The warning alarm event subscriber. */
	private AlarmEventSubscriber warningAlarmEventSubscriber;
	
	/**
	 * Inits the.
	 */
	public void init(){
		logger.info("Initializing alarm event manager: " + criticalAlarmEventSubscriber);
		initSubscribers();		
	}
	
	/**
	 * Inits the subscribers.
	 */
	private void initSubscribers(){
		initSubscriber(criticalAlarmEventSubscriber);
		initSubscriber(majorAlarmEventSubscriber);
		initSubscriber(minorAlarmEventSubscriber);
		initSubscriber(warningAlarmEventSubscriber);
	}
	
	/**
	 * Inits the subscriber.
	 *
	 * @param alarmEventSubscriber the alarm event subscriber
	 */
	private void initSubscriber(final AlarmEventSubscriber alarmEventSubscriber){		
		logger.info("Initializing subscriber:{}", alarmEventSubscriber);
		final EPServiceProvider epServiceProvider = EPServiceUtil.getEpServiceProvider();
		final EPStatement eventStatement = epServiceProvider.getEPAdministrator().createEPL(alarmEventSubscriber.getStatement());
		logger.info("Initializing subscriber:{}, event statement:{}", alarmEventSubscriber.getIdentifier(), eventStatement);
		eventStatement.setSubscriber(alarmEventSubscriber, "onEvent");
	}
		
	/**
	 * Generate sample events.
	 *
	 * @param eventCount the event count
	 */
	public void generateSampleEvents(final int eventCount){
		logger.info("Will generate total:{} events", eventCount);
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(new AlarmEventGenerator(eventCount));
	}
	
	/**
	 * Sets the critical alarm event subscriber.
	 *
	 * @param criticalAlarmEventSubscriber the new critical alarm event subscriber
	 */
	public void setCriticalAlarmEventSubscriber(final AlarmEventSubscriber criticalAlarmEventSubscriber) {
		this.criticalAlarmEventSubscriber = criticalAlarmEventSubscriber;
	}

	/**
	 * Sets the major alarm event subscriber.
	 *
	 * @param majorAlarmEventSubscriber the new major alarm event subscriber
	 */
	public void setMajorAlarmEventSubscriber(final AlarmEventSubscriber majorAlarmEventSubscriber) {
		this.majorAlarmEventSubscriber = majorAlarmEventSubscriber;
	}

	/**
	 * Sets the minor alarm event subscriber.
	 *
	 * @param minorAlarmEventSubscriber the new minor alarm event subscriber
	 */
	public void setMinorAlarmEventSubscriber(final AlarmEventSubscriber minorAlarmEventSubscriber) {
		this.minorAlarmEventSubscriber = minorAlarmEventSubscriber;
	}

	/**
	 * Sets the warning alarm event subscriber.
	 *
	 * @param warningAlarmEventSubscriber the new warning alarm event subscriber
	 */
	public void setWarningAlarmEventSubscriber(final AlarmEventSubscriber warningAlarmEventSubscriber) {
		this.warningAlarmEventSubscriber = warningAlarmEventSubscriber;
	}
}
