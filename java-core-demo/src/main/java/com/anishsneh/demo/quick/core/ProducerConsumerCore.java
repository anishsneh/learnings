package com.anishsneh.demo.quick.core;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumerCore {

	public static void main(final String[] args) {
		final Vector sharedQueue = new Vector();
		int size = 4;
		final Thread prodThread = new Thread(new Producer(sharedQueue, size), "Producer");
		final Thread consThread = new Thread(new Consumer(sharedQueue, size), "Consumer");
		prodThread.start();
		consThread.start();
	}
}

class Producer implements Runnable {

	private final Vector sharedQueue;
	private final int SIZE;

	public Producer(final Vector sharedQueue, final int size) {
		this.sharedQueue = sharedQueue;
		this.SIZE = size;
	}

	@Override
	public void run() {
		for (int i = 0; i < 7; i++) {
			System.out.println("Produced: " + i);
			try {
				produce(i);
			} catch (final InterruptedException ex) {
				Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
			}

		}
	}

	private void produce(final int i) throws InterruptedException {

		// wait if queue is full
		while (sharedQueue.size() == SIZE) {
			synchronized (sharedQueue) {
				System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting , size: " + sharedQueue.size());
				sharedQueue.wait();
			}
		}

		// producing element and notify consumers
		synchronized (sharedQueue) {
			sharedQueue.add(i);
			sharedQueue.notifyAll();
		}
	}
}

class Consumer implements Runnable {

	private final Vector sharedQueue;
	private final int SIZE;

	public Consumer(final Vector sharedQueue, final int size) {
		this.sharedQueue = sharedQueue;
		this.SIZE = size;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Consumed: " + consume());
				Thread.sleep(50);
			} catch (InterruptedException ex) {
				Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
			}

		}
	}

	private int consume() throws InterruptedException {
		// wait if queue is empty
		while (sharedQueue.isEmpty()) {
			synchronized (sharedQueue) {
				System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting , size: " + sharedQueue.size());
				sharedQueue.wait();
			}
		}
		// Otherwise consume element and notify waiting producer
		synchronized (sharedQueue) {
			sharedQueue.notifyAll();
			return (Integer) sharedQueue.remove(0);
		}
	}
}
