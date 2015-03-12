package com.anishsneh.demo.bpmn.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The Class SayHelloServiceTask.
 * 
 * @author Anish Sneh
 * 
 */
public class SayHelloServiceTask implements JavaDelegate{
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(SayHelloServiceTask.class);
	
	/** The Constant INPUT_VAR. */
	public static final String INPUT_VAR = "INPUT_PARAM_NAME";
	
	/** The Constant OUTPUT_VAR. */
	public static final String OUTPUT_VAR = "OUTPUT_PARAM_NAME";
	
	/** The Constant INPUT_LEADS_TO_SUCCESS. */
	public static final String INPUT_LEADS_TO_SUCCESS = "INPUT_LEADS_TO_SUCCESS";
	
	/** The Constant INPUT_LEADS_TO_FAILURE. */
	public static final String INPUT_LEADS_TO_FAILURE = "INPUT_LEADS_TO_FAILURE";
	
	/** The user name. */
	private String userName;
	
	/** The user uuid. */
	private String userUuid;
	
	/**
	 * Instantiates a new say hello service task.
	 */
	public SayHelloServiceTask(){
		this.userName = "anishsneh";
		this.userUuid = "de305d54-75b4-431b-adb2-eb6b9e546013";
	}
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(final String userName) {
		this.userName = userName;
	}
	
	/**
	 * Gets the user uuid.
	 *
	 * @return the user uuid
	 */
	public String getUserUuid() {
		return userUuid;
	}
	
	/**
	 * Sets the user uuid.
	 *
	 * @param userUuid the new user uuid
	 */
	public void setUserUuid(final String userUuid) {
		this.userUuid = userUuid;
	}
	
	/**
	 * Do business.
	 *
	 * @return true, if successful
	 */
	private boolean doBusiness(){
		logger.info(this.toString());
		try {
			Thread.sleep(10L);
		} 
		catch (final InterruptedException ex) {
			logger.error("Failed in business logic", ex);
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.camunda.bpm.engine.delegate.JavaDelegate#execute(org.camunda.bpm.engine.delegate.DelegateExecution)
	 */
	public void execute(DelegateExecution execution) throws Exception {
		doBusiness();
		final String inputVarValue = (String)execution.getVariable(INPUT_VAR);
		logger.info("Got inputVarValue: {}", inputVarValue);
		if(inputVarValue.equals(INPUT_LEADS_TO_SUCCESS)){
			execution.setVariable(OUTPUT_VAR, "true");
		}
		else{
			execution.setVariable(OUTPUT_VAR, "false");
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {		
		return "[userName=" + userName + ", userUuid=" + userUuid + "]";
	}
}
