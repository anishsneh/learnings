package com.anishsneh.demo.quick.core;

public class WaitNotifyExample {
	public static void main(final String[] args) throws InterruptedException {
		ThreadB b = new ThreadB();
		b.start();
		synchronized (b) // thread got lock
		{
			System.out.println("I am calling wait method");
			b.wait();
			System.out.println("I got notification");
		}
		System.out.println(b.total);
	}
}

class ThreadB extends Thread {
	int total = 0;

	public void run() {
		synchronized (this)
		{
			System.out.println("I am starting calculation");
			for (int i = 0; i <= 1000; i++) {
				total = total + i;
			}
			System.out.println("I am giving notification call");
			notify();
		}
	}
}
