package com.anishsneh.demo.quick.core;

public class OutOfMemory {

	/**
	 * @param args
	 */
	public static void main(final String[] args) throws Exception {
		try {
			Thread.sleep(5000);
		} 
		catch (final InterruptedException e) {			
			e.printStackTrace();
		}
		new ThreadC1().start();
		while(true)
		{
			System.out.println( ">>>>>>>>>>>>>>>>>>>>");
			Thread.sleep(2000);
		}
	}
}

class ThreadC1 extends Thread{
	@Override
	public void run() {		
		System.out.println("ThreadC1.run()");
		String[] s = new String[Integer.MAX_VALUE];
	}
	
}
