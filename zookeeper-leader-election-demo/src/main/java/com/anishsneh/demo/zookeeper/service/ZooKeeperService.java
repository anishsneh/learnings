package com.anishsneh.demo.zookeeper.service;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.zookeeper.util.ElectionUtil;
import com.anishsneh.demo.zookeeper.watcher.NodeWatcher;

/**
 * The Class ZooKeeperService.
 * 
 * @author Anish Sneh
 */
public class ZooKeeperService {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ZooKeeperService.class);
	
	/** The zoo keeper. */
	private ZooKeeper zooKeeper;
	
	/**
	 * Instantiates a new zoo keeper service.
	 *
	 * @param zkServerUrl the zk server url
	 * @param nodeWatcher the node watcher
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ZooKeeperService(final String zkServerUrl, final NodeWatcher nodeWatcher) throws IOException {		
		this.zooKeeper = new ZooKeeper(zkServerUrl, ElectionUtil.ZK_SESSION_TIMEOUT, nodeWatcher);
	}
	
	/**
	 * Creates the node.
	 *
	 * @param node the node
	 * @param watch the watch
	 * @param ephimeral the ephimeral
	 * @return the string
	 */
	public String createNode(final String node, final boolean watch, final boolean ephimeral) {
		
		String createdNodePath = null;
		try {
			
			final Stat nodeStat =  zooKeeper.exists(node, watch);
			
			if(nodeStat == null) {
				logger.info("{} ZooKeeper node does not exist, creating new", ElectionUtil.node());
				createdNodePath = zooKeeper.create(node, new byte[0], Ids.OPEN_ACL_UNSAFE, (ephimeral ?  CreateMode.EPHEMERAL_SEQUENTIAL : CreateMode.PERSISTENT));
			} else {
				logger.info("{} ZooKeeper node exists, using existing", ElectionUtil.node());
				createdNodePath = node;
			}
			
		} 
		catch (final Exception e) {
			logger.error("Failed to create ZooKeeper node", e);
			throw new RuntimeException(e);
		}
		return createdNodePath;
	}
	
	/**
	 * Watch node.
	 *
	 * @param node the node
	 * @param watch the watch
	 * @return true, if successful
	 */
	public boolean watchNode(final String node, final boolean watch) {
		
		boolean watched = false;
		try {
			final Stat nodeStat =  zooKeeper.exists(node, watch);
			if(null != nodeStat) {
				watched = true;
			}
		} 
		catch (final Exception e) {
			logger.error("Failed to setup watcher for ZooKeeper node", e);
			throw new RuntimeException(e);
		}
		return watched;
	}
	
	/**
	 * Gets the children.
	 *
	 * @param node the node
	 * @param watch the watch
	 * @return the children
	 */
	public List<String> getChildren(final String node, final boolean watch) {
		
		List<String> childNodes = null;
		
		try {
			childNodes = zooKeeper.getChildren(node, watch);
		} 
		catch (final Exception e) {
			logger.error("Failed to get ZooKeeper node children", e);
			throw new RuntimeException(e);
		}
		return childNodes;
	}
}
