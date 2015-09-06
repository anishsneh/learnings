package com.anishsneh.demo.zookeeper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.zookeeper.service.Node;
import com.anishsneh.demo.zookeeper.util.ElectionUtil;

/**
 * The Class Main.
 * 
 * Steps:
 * 		1). Compile:
 * 				$ mvn clean install
 * 		2). Start ZooKeeper server:
 * 				$ZOOKEEPER_HOME/bin/zkServer.sh start
 * 		3). Open N number of terminals (I used N=4)
 * 		4). In first terminal run 
 * 				$ java -jar target/zookeeper-leader-election-demo.jar 
 * 		5). Repeat step-4 for all the N terminals (in my case N=4)
 * 	
 * Note that:
 * 		- First terminal (or we can say process/node at first terminal) will become LEADER
 * 		- All other terminal will be active but NOT LEADERs
 * 		- As soon as LEADER dies or disconnects from process (i.e. it loses ZooKeeper connection), next node will be selected as NEW LEADER
 * 
 * 
 * @author Anish Sneh
 */
public class Main {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {

		final String zkServerUrl = (args.length < 1 ? ElectionUtil.ZK_DEFAULT_URL : args[0]);
		logger.info("{} ZooKeeper server address: {}", ElectionUtil.node(), zkServerUrl);
		final Node node = new Node(zkServerUrl);
		final ExecutorService service = Executors.newSingleThreadExecutor();
		final Future<?> status = service.submit(node);
		logger.info("{} ZooKeeper node initilization status: {}", ElectionUtil.node(), status);
		try {
			status.get();
		} 
		catch (final Exception e) {
			logger.error(e.getMessage(), e);
			service.shutdown();
		}
	}
}
