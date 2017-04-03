package com.anishsneh.demo.quick.core.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * if lock.lockInterruptibly() the thread waiting for lock CAN BE interrupted from other thread
 * 
 * if lock.lock() the thread waiting for lock CANNOT BE interrupted from other thread
 * 
 */
public class InterruptibleLock {

	public static void main(final String[] args) {

		final Lock LOCK = new ReentrantLock();		
		holdLockInterruptibly(LOCK);
		interruptTest(LOCK, true);
	}

	public static void interruptAfter(Thread t, long millis) {
		sleep(millis);
		t.interrupt();
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void holdLockInterruptibly(final Lock lock) {
		Thread forInterruptTest = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("holdLockInterruptibly Running: " + Thread.currentThread().getName());				
				try {
					lock.lock();
					boolean keepRunning = true;
					while(keepRunning){
						System.out.println("holdLockInterruptibly Running infintely with lock");	
						sleep(5000);
						keepRunning = false;
					}
					System.out.println("holdLockInterruptibly released lock");	
					lock.unlock();
				} catch (Exception e) {
					e.printStackTrace();
				}												
			}
		});
		forInterruptTest.setPriority(Thread.MIN_PRIORITY);
		forInterruptTest.start();		
	}
	
	public static void interruptTest(final Lock lock, final boolean interruptibly) {
		Thread forInterruptTest = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("interruptTest Running: " + Thread.currentThread().getName());				
				try {
					System.out.println("interruptTest trying to acquire lockInterruptibly");
					if(interruptibly){
						lock.lockInterruptibly();
						System.out.println("interruptTest acquired lock.lockInterruptibly()");	
					}
					else{
						lock.lock();
						System.out.println("interruptTest acquired lock.lock()");	
					}
					System.out.println("interruptTest isInterrupted: " + Thread.currentThread().isInterrupted());					
					lock.unlock();
				} catch (Exception e) {
					System.out.println("interruptTest NEVER acquired lock.lockInterruptibly() threw exception");	
					e.printStackTrace();
					System.exit(1);
				}												
			}
		});
		forInterruptTest.setPriority(Thread.MAX_PRIORITY);
		forInterruptTest.start();
		interruptAfter(forInterruptTest, 2000);
	}

}
