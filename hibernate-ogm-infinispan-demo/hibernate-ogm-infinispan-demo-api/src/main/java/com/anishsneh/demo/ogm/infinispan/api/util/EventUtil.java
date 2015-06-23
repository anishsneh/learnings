package com.anishsneh.demo.ogm.infinispan.api.util;

import java.util.Objects;

import com.anishsneh.demo.ogm.infinispan.api.dto.Event;

/**
 * The Class EventUtil.
 * 
 * @author Anish Sneh
 * 
 */
public class EventUtil {

	/**
	 * Checks if is valid event.
	 *
	 * @param event the event
	 * @return true, if is valid event
	 */
	public static boolean isValidEvent(final Event event){
		boolean isValid = false;
		Objects.requireNonNull(event);		
		if(null != event.getCounterName() && !"".equals(event.getCounterName().trim()) && isValidSeverity(event.getSeverity())){			
			isValid = true;			
		}
		return isValid;
	}
	
	/**
	 * Checks if is valid severity.
	 *
	 * @param severity the severity
	 * @return true, if is valid severity
	 */
	public static boolean isValidSeverity(final String severity){
		boolean isValid = false;
		if(null != severity && !"".equals(severity.trim())){
			if(severity.trim().equals("MAJOR") || severity.trim().equals("MINOR") || severity.trim().equals("CRITICAL")){
				isValid = true;
			}
		}
		return isValid;
	}	
}
