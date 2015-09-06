package com.anishsneh.demo.zookeeper.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.zookeeper.manager.LeadershipManager;
import com.anishsneh.demo.zookeeper.service.NodeState;
import com.anishsneh.demo.zookeeper.util.ElectionUtil;

/**
 * The Class NodeWatcher.
 * 
 * @author Anish Sneh
 */
public class NodeWatcher implements Watcher{
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(NodeWatcher.class);
	
	/** The leadership manager. */
	private LeadershipManager leadershipManager;

	/* (non-Javadoc)
	 * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
	 */
	@Override
	public void process(final WatchedEvent event) {
			
		logger.info("{} Event received: {}", ElectionUtil.node(), event);		
		final EventType eventType = event.getType();
		if(EventType.NodeDeleted == eventType) {
			if(event.getPath().equalsIgnoreCase(NodeState.getInstance().getWatchedNodePath())) {
				logger.info("{} Node disconnected/deleted: {}", ElectionUtil.node(), event);
				if(!isLeadershipManagerRegistered()){
					throw new IllegalStateException("Leadership manager is not registered");
				}
				leadershipManager.tryLeadership();
			}
		}
	}
	
	/**
	 * Register leadership manager.
	 *
	 * @param leadershipManager the leadership manager
	 */
	public void registerLeadershipManager(final LeadershipManager leadershipManager){
		this.leadershipManager = leadershipManager;
	}
	
	/**
	 * Checks if is leadership manager registered.
	 *
	 * @return true, if is leadership manager registered
	 */
	public boolean isLeadershipManagerRegistered(){
		return leadershipManager != null;
	}
}
