package com.anishsneh.demo.quick.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ThreadPoolCustomBlockingQueue {

	public static void main(final String[] args) throws Exception{

		ThreadPool threadPool = new ThreadPool(5, 5);
		threadPool.execute(new TestTaskThread());
		threadPool.execute(new TestTaskThread());
		threadPool.execute(new TestTaskThread());
		threadPool.execute(new TestTaskThread());
		threadPool.execute(new TestTaskThread());
		threadPool.execute(new TestTaskThread());
	}
}

//Number of worker threads equals to thread pool size i.e. one WorkerThread per thread in pool
class ThreadPool {

	private BlockingQueue taskQueue = null;
	private List<WorkerThread> threads = new ArrayList<WorkerThread>();
	private boolean isStopped = false;

	public ThreadPool(final int noOfThreads, final int maxNoOfTasks) {
		taskQueue = new BlockingQueue(maxNoOfTasks);

		for (int i = 0; i < noOfThreads; i++) {
			threads.add(new WorkerThread(taskQueue));
		}
		for (final WorkerThread thread : threads) {
			thread.start();
		}
	}

	public synchronized void execute(final Runnable task) throws Exception {
		if (this.isStopped){
			throw new IllegalStateException("ThreadPool is stopped");
		}
		this.taskQueue.enqueue(task);
	}

	public synchronized void stop() {
		this.isStopped = true;
		for (WorkerThread thread : threads) {
			thread.stop();
		}
	}

}

//Number of worker threads equals to thread pool size i.e. one WorkerThread per thread in pool
class WorkerThread extends Thread {

	private BlockingQueue taskQueue = null;
	private boolean isStopped = false;

	public WorkerThread(final BlockingQueue queue) {
		taskQueue = queue;
	}

	public void run() {
		while (!isStopped()) {
			try {
				final Runnable runnable = (Runnable) taskQueue.dequeue();
				runnable.run();
			} 
			catch (final Exception e) {
				// log or otherwise report exception, but keep pool thread alive.
			}
		}
	}

	public synchronized void doStop() {
		isStopped = true;
		this.interrupt(); // break pool thread out of dequeue() call.
	}

	public synchronized boolean isStopped() {
		return isStopped;
	}
}

class BlockingQueue {

	private List queue = new LinkedList();
	private int limit = 10;

	public BlockingQueue(int limit) {
		this.limit = limit;
	}

	public synchronized void enqueue(final Object item) throws InterruptedException {
		while (this.queue.size() == this.limit) {
			wait();
		}		
		this.queue.add(item);
		notifyAll();
	}

	public synchronized Object dequeue() throws InterruptedException {
		while (this.queue.size() == 0) {
			wait();
		}
		final Object o = this.queue.remove(0);
		notifyAll();
		return o; 
	}
}

class TestTaskThread extends Thread{
	
	@Override
	public void run() {
		System.out.println("Running for: " + Thread.currentThread().getId());		
	}
}
