package com.anishsneh.demo.cep.esper.subscriber;

import java.util.Map;

import com.anishsneh.demo.cep.esper.event.AlarmEvent;

/**
 * The Interface AlarmEventSubscriber.
 * 
 * @author Anish Sneh
 */
public interface AlarmEventSubscriber {

    /**
     * Gets the statement.
     *
     * @return the statement
     */
    public String getStatement();
    
    /**
     * Gets the threshold.
     *
     * @return the threshold
     */
    public double getThreshold();
    
    /**
     * Gets the identifier.
     *
     * @return the identifier
     */
    public String getIdentifier();
    
    /**
     * On event.
     */
    public void onEvent(final Map<String, AlarmEvent> eventMap);
}
