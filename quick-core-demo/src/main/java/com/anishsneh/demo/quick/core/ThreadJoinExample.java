package com.anishsneh.demo.quick.core;

public class ThreadJoinExample {

	public static void main(final String[] args) throws Exception {

		DemoThread t1 = new DemoThread();
		t1.start();
		t1.join(); //if t1 is already dead then join will not wait
		System.out.println("Running main");
	}

}

class DemoThread extends Thread {
	public void run() {		
		System.out.println("Running");
		try {
			Thread.sleep(3000);
		} 
		catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}
}
