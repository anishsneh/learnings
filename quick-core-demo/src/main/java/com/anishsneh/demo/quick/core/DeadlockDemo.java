package com.anishsneh.demo.quick.core;

/**
 * The Class DeadlockDemo.
 */
public class DeadlockDemo {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {

		final Object lock1 = new Object();
		final Object lock2 = new Object();

		final ThreadDemo1 T1 = new ThreadDemo1(lock1, lock2);
		final ThreadDemo2 T2 = new ThreadDemo2(lock1, lock2);
		T1.start();
		T2.start();
	}
}

class ThreadDemo1 extends Thread {

	private Object lock1;
	private Object lock2;

	public ThreadDemo1(final Object lock1, final Object lock2) {
		this.lock1 = lock1;
		this.lock2 = lock2;
	}

	public void run() {
		synchronized (lock1) {
			System.out.println("Thread 1: Holding lock 1...");
			try {
				Thread.sleep(10);
			} 
			catch (final InterruptedException e) {
			}
			System.out.println("Thread 1: Waiting for lock 2...");
			synchronized (lock2) {
				System.out.println("Thread 1: Holding lock 1 & 2...");
			}
		}
	}
}

class ThreadDemo2 extends Thread {

	private Object lock1;
	private Object lock2;

	public ThreadDemo2(final Object lock1, final Object lock2) {
		this.lock1 = lock1;
		this.lock2 = lock2;
	}

	public void run() {
		synchronized (lock2) {
			System.out.println("Thread 2: Holding lock 2...");
			try {
				Thread.sleep(10);
			} 
			catch (final InterruptedException e) {
			}
			System.out.println("Thread 2: Waiting for lock 1...");
			synchronized (lock1) {
				System.out.println("Thread 2: Holding lock 1 & 2...");
			}
		}
	}
}