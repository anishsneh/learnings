package com.anishsneh.demo.cep.esper.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.cep.esper.event.AlarmEvent;
import com.anishsneh.demo.cep.esper.util.EPServiceUtil;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;

/**
 * The Class AlarmEventGenerator.
 * 
 * @author Anish Sneh
 */
public class AlarmEventGenerator implements Runnable{
	
	/** The event count. */
	private int eventCount;
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(AlarmEventGenerator.class);
	
	/**
	 * Instantiates a new alarm event generator.
	 *
	 * @param eventCount the event count
	 */
	public AlarmEventGenerator(final int eventCount){
		this.eventCount = eventCount;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run(){		
		for(int i=0; i < this.eventCount; i++){						
			double counterValue = 0;
			if(i % 5 == 0 ){
				counterValue = 25.00D;
			}
			else if(i % 3 == 0 ){
				counterValue = 35.00D;
			}
			else if(i % 2 == 0 ){
				counterValue = 45.00D;
			}
			else{
				counterValue = 55.00D;
			}
			final AlarmEvent alarmEvent = new AlarmEvent("CTR_" + i, counterValue);			
			final EPServiceProvider epServiceProvider = EPServiceUtil.getEpServiceProvider();
			final EPRuntime epRuntime = epServiceProvider.getEPRuntime();
			epRuntime.sendEvent(alarmEvent);
			logger.info("\n========================== Sent event ==========================\n");
			try {
				Thread.sleep(200);
			} 
			catch (final InterruptedException ex) {				
				logger.error("Error in event generation", ex);
			}
		}
	}

}
