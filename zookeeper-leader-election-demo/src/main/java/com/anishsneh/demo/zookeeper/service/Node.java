package com.anishsneh.demo.zookeeper.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.zookeeper.manager.LeadershipManager;
import com.anishsneh.demo.zookeeper.manager.NodeManager;
import com.anishsneh.demo.zookeeper.util.ElectionUtil;
import com.anishsneh.demo.zookeeper.watcher.NodeWatcher;

/**
 * The Class Node.
 * 
 * @author Anish Sneh
 */
public class Node implements Runnable {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(Node.class);
	
	/** The zk server url. */
	private String zkServerUrl;
	
	/**
	 * Instantiates a new node.
	 *
	 * @param zkServerUrl the zk server url
	 */
	public Node(final String zkServerUrl){
		this.zkServerUrl = zkServerUrl;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run(){
		
		logger.info("{} Starting node", ElectionUtil.node());
		final NodeWatcher nodeWatcher = new NodeWatcher();
		ZooKeeperService zkService = null;
		NodeManager nodeManager = null;
		LeadershipManager leadershipManager = null;
		try {
			zkService = new ZooKeeperService(zkServerUrl, nodeWatcher);
			nodeManager = new NodeManager(zkService);
			leadershipManager = new LeadershipManager(zkService);
		} 
		catch (final Exception e) {
			logger.error("Failed to initialize system", e);
			throw new RuntimeException(e);
		}		
		nodeWatcher.registerLeadershipManager(leadershipManager);
		
		logger.info("{} Process started", ElectionUtil.node());
		
		final String rootNodePath = nodeManager.createLeaderElectionRootNode();
		NodeState.getInstance().setRootNodePath(rootNodePath);
		final String processNodePath = nodeManager.createProcessNode(rootNodePath);
		NodeState.getInstance().setProcessNodePath(processNodePath);
		NodeState.getInstance().setProcessNode(ElectionUtil.getNodeNameFromPath(processNodePath));
		logger.info("{} Process node created with path: {}", ElectionUtil.node(), processNodePath);
		logger.info("{} {}", ElectionUtil.node(), NodeState.getInstance().toString());
		
		leadershipManager.tryLeadership();
	}
}
