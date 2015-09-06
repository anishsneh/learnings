package com.anishsneh.demo.zookeeper.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.zookeeper.service.ZooKeeperService;
import com.anishsneh.demo.zookeeper.util.ElectionUtil;

/**
 * The Class NodeManager.
 * 
 * @author Anish Sneh
 */
public class NodeManager {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(NodeManager.class);
	
	/** The zoo keeper service. */
	private ZooKeeperService zooKeeperService;
	
	/**
	 * Instantiates a new node manager.
	 *
	 * @param zkService the zk service
	 */
	public NodeManager(final ZooKeeperService zkService){
		this.zooKeeperService = zkService;
	}
	
	/**
	 * Creates the leader election root node.
	 *
	 * @return the string
	 */
	public String createLeaderElectionRootNode(){
		
		final String path = zooKeeperService.createNode(ElectionUtil.ZK_LEADER_ELECTION_ROOT_NODE, false, false);
		if(null == path) {
			throw new RuntimeException("Failed to root node with path: " + ElectionUtil.ZK_LEADER_ELECTION_ROOT_NODE);
		}
		logger.info("{} Root node created at path: {}", ElectionUtil.node(),  path);
		return path;
	}
	
	/**
	 * Creates the process node.
	 *
	 * @param rootNodePath the root node path
	 * @return the string
	 */
	public String createProcessNode(final String rootNodePath){
		
		final String path = zooKeeperService.createNode(rootNodePath + ElectionUtil.ZK_PROCESS_NODE_PREFIX, false, true);
		if(null == path) {
			throw new RuntimeException("Failed to create process (child) node with root path: " + rootNodePath);
		}
		logger.info("{} Process node created at path: {}", ElectionUtil.node(), path);
		return path;
	}
}
