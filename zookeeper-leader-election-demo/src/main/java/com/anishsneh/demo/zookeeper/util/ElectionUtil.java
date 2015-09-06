package com.anishsneh.demo.zookeeper.util;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ElectionUtil.
 * 
 * @author Anish Sneh
 */
public class ElectionUtil {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ElectionUtil.class);
	
	/** The Constant PROCESS_ID. */
	private static final String PROCESS_ID = UUID.randomUUID().toString();

	/** The Constant ZK_LEADER_ELECTION_ROOT_NODE. */
	public static final String ZK_LEADER_ELECTION_ROOT_NODE = "/election";
	
	/** The Constant ZK_PROCESS_NODE_PREFIX. */
	public static final String ZK_PROCESS_NODE_PREFIX = "/p_";
	
	/** The Constant ZK_SESSION_TIMEOUT. */
	public static final int ZK_SESSION_TIMEOUT = 3000;
	
	/** The Constant ZK_DEFAULT_URL. */
	public static final String ZK_DEFAULT_URL = "localhost:2181";
	
	/** The Constant ZK_LEADER_GREETINGS. */
	public static final String ZK_LEADER_GREETINGS = "HELLO EVERYONE, I AM THE NEW LEADER";
	
	/**
	 * Node.
	 *
	 * @return the string
	 */
	public static String node(){
		return "[NODE:" + PROCESS_ID + "]";
	}
	
	/**
	 * Gets the previous node index.
	 *
	 * @param sortedChildNodePaths the sorted child node paths
	 * @param processNodePath the process node path
	 * @return the previous node index
	 */
	public static int getPreviousNodeIndex(final List<String> sortedChildNodePaths, final String processNodePath){
		return (sortedChildNodePaths.indexOf(processNodePath) - 1);
	}
	
	/**
	 * Gets the node name from path.
	 *
	 * @param nodePath the node path
	 * @return the node name from path
	 */
	public static String getNodeNameFromPath(final String nodePath){
		return nodePath.substring(nodePath.lastIndexOf('/') + 1);
	}
	
	/**
	 * Checks if is process node oldest alive child.
	 *
	 * @param childNodePaths the child node paths
	 * @param processNodePath the process node path
	 * @return true, if is process node oldest alive child
	 */
	public static boolean isProcessNodeOldestAliveChild(final List<String> childNodePaths, final String processNodePath){
		logger.info("{} Children nodes (before sort): {}", ElectionUtil.node(), childNodePaths);
		Collections.sort(childNodePaths);
		logger.info("{} Children nodes (after sort): {}", ElectionUtil.node(), childNodePaths);
		final String zeroIndexChildNode = childNodePaths.get(0);
		return zeroIndexChildNode.equals(processNodePath);
	}
}
