package com.anishsneh.demo.bpmn.camunda;

import java.util.Collections;
import java.util.Map;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions;
import org.camunda.bpm.engine.test.assertions.ProcessEngineTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.bpmn.camunda.SayHelloServiceTask;

/**
 * The Class HelloWorldProcessTest.
 * 
 * @author Anish Sneh
 * 
 */
public class HelloWorldProcessTest {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldProcessTest.class);

	/** The process engine rule. */
	@Rule
	public ProcessEngineRule processEngineRule = new ProcessEngineRule("camunda.cfg.xml");

	/** The runtime service. */
	private RuntimeService runtimeService;
	
	/** The history service. */
	private HistoryService historyService;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		runtimeService = processEngineRule.getRuntimeService();
		historyService = processEngineRule.getHistoryService();
	}

	/**
	 * Test_ hello world process_ success.
	 */
	@Test
	@Deployment(resources = {"helloWorld.bpmn"})
	public void test_HelloWorldProcess_Success() {

		final Map<String, Object> flowInputVars = Collections.<String, Object> singletonMap(SayHelloServiceTask.INPUT_VAR, SayHelloServiceTask.INPUT_LEADS_TO_SUCCESS);
		final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("helloWorldProcess", flowInputVars);

		//Log process details
		logger.info("Process id:{}", processInstance.getId());
		logger.info("Process definition id:{}", processInstance.getProcessDefinitionId());		
		logger.info("Process instance id:{}", processInstance.getProcessInstanceId());

		//Check process instance details
		ProcessEngineAssertions.assertThat(processInstance).isActive();
		ProcessEngineAssertions.assertThat(ProcessEngineTests.processInstanceQuery().count()).isEqualTo(1);
		ProcessEngineAssertions.assertThat(ProcessEngineTests.task(processInstance)).isNotNull();
		
		//Process completion
		ProcessEngineTests.complete(ProcessEngineTests.task(processInstance));

		//Check if process ended
		ProcessEngineAssertions.assertThat(processInstance).isEnded();

		//Check initial activity or start event | from history
		final String startActivityName = historyService.createHistoricActivityInstanceQuery().activityId("StartEvent_1").singleResult().getActivityName();
		Assert.assertEquals(startActivityName, "Start");

		//Check last activity or end event | from history
		final HistoricActivityInstance historicActivityInstanceEndEvent = historyService.createHistoricActivityInstanceQuery().activityId("EndEvent_1").singleResult();
		Assert.assertNotNull(historicActivityInstanceEndEvent);
		final String endActivityName = historicActivityInstanceEndEvent.getActivityName();
		Assert.assertEquals(endActivityName, "Success");

		//Check input variable value | from history
		final HistoricVariableInstance inputParamInstance = historyService.createHistoricVariableInstanceQuery().variableName(SayHelloServiceTask.INPUT_VAR).singleResult();
		Assert.assertNotNull(inputParamInstance);
		Assert.assertEquals(inputParamInstance.getValue(), "INPUT_LEADS_TO_SUCCESS");

		//Check intermediate variable value | from history
		final HistoricVariableInstance outputParamInstance = historyService.createHistoricVariableInstanceQuery().variableName(SayHelloServiceTask.OUTPUT_VAR).singleResult();
		Assert.assertNotNull(outputParamInstance);
		Assert.assertEquals(outputParamInstance.getValue(), "true");

	}

	/**
	 * Test_ hello world process_ failure.
	 */
	@Test
	@Deployment(resources = {"helloWorld.bpmn"})
	public void test_HelloWorldProcess_Failure() {
		
		final Map<String, Object> flowInputVars = Collections.<String, Object> singletonMap(SayHelloServiceTask.INPUT_VAR, SayHelloServiceTask.INPUT_LEADS_TO_FAILURE);

		final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("helloWorldProcess", flowInputVars);

		//Log process details
		logger.info("Process id:{}", processInstance.getId());
		logger.info("Process definition id:{}", processInstance.getProcessDefinitionId());		
		logger.info("Process instance id:{}", processInstance.getProcessInstanceId());

		//Check process instance details
		ProcessEngineAssertions.assertThat(processInstance).isActive();
		ProcessEngineAssertions.assertThat(ProcessEngineTests.processInstanceQuery().count()).isEqualTo(1);
		ProcessEngineAssertions.assertThat(ProcessEngineTests.task(processInstance)).isNotNull();
		
		//Process completion
		ProcessEngineTests.complete(ProcessEngineTests.task(processInstance));

		//Check if process ended
		ProcessEngineAssertions.assertThat(processInstance).isEnded();

		//Check initial activity or start event | from history
		final String startActivityName = historyService.createHistoricActivityInstanceQuery().activityId("StartEvent_1").singleResult().getActivityName();
		Assert.assertEquals(startActivityName, "Start");

		//Check last activity or end event | from history
		final HistoricActivityInstance historicActivityInstanceEndEvent = historyService.createHistoricActivityInstanceQuery().activityId("EndEvent_2").singleResult();
		Assert.assertNotNull(historicActivityInstanceEndEvent);
		final String endActivityName = historicActivityInstanceEndEvent.getActivityName();
		Assert.assertEquals(endActivityName, "Failure");

		//Check input variable value | from history
		final HistoricVariableInstance inputParamInstance = historyService.createHistoricVariableInstanceQuery().variableName(SayHelloServiceTask.INPUT_VAR).singleResult();
		Assert.assertNotNull(inputParamInstance);
		Assert.assertEquals(inputParamInstance.getValue(), "INPUT_LEADS_TO_FAILURE");

		//Check intermediate variable value | from history
		final HistoricVariableInstance outputParamInstance = historyService.createHistoricVariableInstanceQuery().variableName(SayHelloServiceTask.OUTPUT_VAR).singleResult();
		Assert.assertNotNull(outputParamInstance);
		Assert.assertEquals(outputParamInstance.getValue(), "false");
	}
}
