package com.anishsneh.demo.quick.core.concurrent;

import java.util.concurrent.Semaphore;

/*
 * ### MISUSE ### 
 * - Not releasing after acquire (either missing release call or an exception is thrown and there is no finally block)
 * - Long held semaphores, causing thread starvation
 * - Deadlocks
 * ##############
 */
public class SemaphoreExample {

	Semaphore binary = new Semaphore(1);

	public static void main(final String args[]) {
		final SemaphoreExample test = new SemaphoreExample();
		new Thread() {
			@Override
			public void run() {
				test.mutualExclusion();
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				test.mutualExclusion();
			}
		}.start();
	}

	private void mutualExclusion() {
		try {
			binary.acquire();
			// mutual exclusive region
			System.out.println(Thread.currentThread().getName() + " inside mutual exclusive region");
			Thread.sleep(1000);

		} catch (InterruptedException ie) {
			ie.printStackTrace();
		} finally {
			binary.release();
			System.out.println(Thread.currentThread().getName() + " outside of mutual exclusive region");
		}
	}

}
