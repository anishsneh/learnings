package com.anishsneh.demo.quick.core;

public class NotThreadSafeExample {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		
		final NotThreadSafe sharedInstance = new NotThreadSafe();

		new Thread(new MyRunnable(sharedInstance)).start();
		new Thread(new MyRunnable(sharedInstance)).start();

	}		
}

class MyRunnable implements Runnable{
	  NotThreadSafe instance = null;
	  
	  public MyRunnable(final NotThreadSafe instance){
	    this.instance = instance;
	  }

	  public void run(){
	    this.instance.add("some text");
	    System.out.println("VAL: " + System.currentTimeMillis() + this.instance.builder.toString());
	  }
}

class NotThreadSafe{
	final StringBuilder builder = new StringBuilder();
    
    public void add(final String text){
        this.builder.append(text);
    }	
}