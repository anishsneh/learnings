package com.anishsneh.demo.zookeeper.service;

/**
 * The Class NodeState.
 * 
 * @author Anish Sneh
 */
public class NodeState {

	/** The Constant INSTANCE. */
	private static final NodeState INSTANCE = new NodeState();

	/** The root node path. */
	private String rootNodePath;
	
	/** The process node path. */
	private String processNodePath;
	
	/** The process node. */
	private String processNode;
	
	/** The watched node path. */
	private String watchedNodePath;

	/**
	 * Instantiates a new node state.
	 */
	private NodeState() {
	}

	/**
	 * Gets the single instance of NodeState.
	 *
	 * @return single instance of NodeState
	 */
	public static NodeState getInstance() {
		return INSTANCE;
	}

	/**
	 * Gets the root node path.
	 *
	 * @return the root node path
	 */
	public String getRootNodePath() {
		return rootNodePath;
	}

	/**
	 * Sets the root node path.
	 *
	 * @param rootNodePath the new root node path
	 */
	public void setRootNodePath(final String rootNodePath) {
		this.rootNodePath = rootNodePath;
	}

	/**
	 * Gets the process node path.
	 *
	 * @return the process node path
	 */
	public String getProcessNodePath() {
		return processNodePath;
	}

	/**
	 * Sets the process node path.
	 *
	 * @param processNodePath the new process node path
	 */
	public void setProcessNodePath(final String processNodePath) {
		this.processNodePath = processNodePath;
	}

	/**
	 * Gets the watched node path.
	 *
	 * @return the watched node path
	 */
	public String getWatchedNodePath() {
		return watchedNodePath;
	}

	/**
	 * Sets the watched node path.
	 *
	 * @param watchedNodePath the new watched node path
	 */
	public void setWatchedNodePath(final String watchedNodePath) {
		this.watchedNodePath = watchedNodePath;
	}

	/**
	 * Gets the process node.
	 *
	 * @return the process node
	 */
	public String getProcessNode() {
		return processNode;
	}

	/**
	 * Sets the process node.
	 *
	 * @param processNode the new process node
	 */
	public void setProcessNode(final String processNode) {
		this.processNode = processNode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NodeState [rootNodePath=" + rootNodePath + ", processNodePath=" + processNodePath + ", processNode=" + processNode + ", watchedNodePath=" + watchedNodePath + "]";
	}
}
