package com.anishsneh.demo.cep.esper.subscriber;

import java.util.Map;

import org.slf4j.Logger;

import com.anishsneh.demo.cep.esper.event.AlarmEvent;
import com.anishsneh.demo.spring.framework.ApplicationLogger;

/**
 * The Class CriticalAlarmEventSubscriber.
 * 
 * @author Anish Sneh
 */
public class CriticalAlarmEventSubscriber implements AlarmEventSubscriber{
	
	/** The event counter. */
	private static long eventCounter;
	
	/** The logger. */
	@ApplicationLogger 
	private Logger logger;

	/* (non-Javadoc)
	 * @see com.anishsneh.demo.cep.subscriber.AlarmEventSubscriber#getStatement()
	 */
	@Override
	public String getStatement() {
		return "select * from AlarmEvent(counterValue < " + this.getThreshold() + ").win:time(10 sec)";
	}

	/* (non-Javadoc)
	 * @see com.anishsneh.demo.cep.subscriber.AlarmEventSubscriber#getThreshold()
	 */
	@Override
	public double getThreshold() {
		return 30.00D;
	}

	/* (non-Javadoc)
	 * @see com.anishsneh.demo.cep.subscriber.AlarmEventSubscriber#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return "CRITICAL_EVENT_SUBSCRIBER";
	}

	/* (non-Javadoc)
	 * @see com.anishsneh.demo.cep.subscriber.AlarmEventSubscriber#onEvent(java.util.Map)
	 */
	@Override
	public void onEvent(final Map<String, AlarmEvent> eventMap) {
		eventCounter++;
		logger.info("Processing event: {}, size:{}, processed:{}", this.getIdentifier(), eventMap.size(), eventCounter);
	}
}
