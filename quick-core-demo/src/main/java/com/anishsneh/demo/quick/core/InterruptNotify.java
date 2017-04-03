package com.anishsneh.demo.quick.core;

/**
 * 
 * The reason why notify(), notifyAll(), wait() should be called from
 * synchronized block:
 * 
 * wait(): a thread can only wait and let others take lock if and only if it
 * holds the lock, if it does not hold the lock why it needs to wait
 * 
 * notify(), notifyAll(): a thread can only notify others that now you can
 * proceed because I am done, if and only if it holds the lock.
 * 
 * Why they are in Object.class because they are directly related to monitor
 * object the lock object.
 * 
 * We can only interrupt a thread if it is waiting
 * We cannot interrupt a running thread in-between
 * 
 */
public class InterruptNotify {

	public static void main(final String[] args) {

		final Object LOCK1 = new Object();
		final Object LOCK2 = new Object();

		notifyTest(LOCK1);
		sleep(5000);
		interruptTest(LOCK2);

	}

	public static void notifyAllAfter(final Object o, final long millis) {
		sleep(millis);
		synchronized (o) {
			o.notifyAll();
		}
	}

	public static void interruptAfter(final Thread t, final long millis) {
		sleep(millis);
		t.interrupt();
	}

	public static void sleep(final long millis) {
		try {
			Thread.sleep(millis);
		} 
		catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void notifyTest(final Object lock) {
		Thread forNotifyTest = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("forNotifyTest Running: " + Thread.currentThread().getName());
				try {
					synchronized (lock) {
						sleep(2000);
						System.out.println("forNotifyTest Going to wait");
						lock.wait();
						System.out.println("forNotifyTest After calling wait");
					}
				} 
				catch (final InterruptedException e) {
					System.out.println("forNotifyTest Interrupted: " + e.getMessage());
					e.printStackTrace();
				}
				System.out.println("forNotifyTest Must have got notify signal hence proceeding");
			}
		});
		forNotifyTest.start();
		notifyAllAfter(lock, 5000L);
	}

	public static void interruptTest(final Object lock) {
		Thread forInterruptTest = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("forInterruptTest Running: " + Thread.currentThread().getName());
				synchronized (lock) {
					while(true){
						System.out.println("Running infintely");
					}
				}				
			}
		});
		forInterruptTest.start();
		interruptAfter(forInterruptTest, 2000);
	}
}
