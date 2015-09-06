package com.anishsneh.demo.zookeeper.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.zookeeper.service.NodeState;
import com.anishsneh.demo.zookeeper.service.ZooKeeperService;
import com.anishsneh.demo.zookeeper.util.ElectionUtil;

/**
 * The Class LeadershipManager.
 * 
 * @author Anish Sneh
 */
public class LeadershipManager {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(LeadershipManager.class);

	/** The zoo keeper service. */
	private ZooKeeperService zooKeeperService;

	/**
	 * Instantiates a new leadership manager.
	 *
	 * @param zooKeeperService the zoo keeper service
	 */
	public LeadershipManager(final ZooKeeperService zooKeeperService) {
		this.zooKeeperService = zooKeeperService;
	}
	
	/**
	 * Try leadership.
	 */
	public void tryLeadership() {

		final List<String> childNodePaths = zooKeeperService.getChildren(ElectionUtil.ZK_LEADER_ELECTION_ROOT_NODE, false);
		logger.info("{} Found total children nodes:{}", ElectionUtil.node(), childNodePaths.size());		
		final String processNode = NodeState.getInstance().getProcessNode();

		if(ElectionUtil.isProcessNodeOldestAliveChild(childNodePaths, processNode)){
			logger.info("{} =======================================================", ElectionUtil.node());
			logger.info("{} {}", ElectionUtil.node(), ElectionUtil.ZK_LEADER_GREETINGS);
			logger.info("{} =======================================================", ElectionUtil.node());
		} 
		else {
			final int previousNodeIndex = ElectionUtil.getPreviousNodeIndex(childNodePaths, processNode);
			logger.info("{} I lost the election, will try next time", ElectionUtil.node());
			final String watchedNode = childNodePaths.get(previousNodeIndex);
			final String watchedNodePath = ElectionUtil.ZK_LEADER_ELECTION_ROOT_NODE + "/" + watchedNode;
			logger.info("{} Setting watch on node with path: {}", ElectionUtil.node(), watchedNodePath);
			NodeState.getInstance().setWatchedNodePath(watchedNodePath);
			zooKeeperService.watchNode(watchedNodePath, true);
		}
		logger.info("{} {}", ElectionUtil.node(), NodeState.getInstance().toString());
	}
}
