package com.anishsneh.demo.quick.core.concurrent;

import java.util.concurrent.Exchanger;

/**
 * 
 * Need more explanation
 *
 */
public class ExchangerDemo {

	public static void main(final String[] args) {

		Exchanger<String> exchanger = new Exchanger<String>();
		Thread t1 = new MessageThread(exchanger, "I like coffee.");
		Thread t2 = new MessageThread(exchanger, "I like tea");
		t1.start();
		t2.start();
	}
}

class MessageThread extends Thread {

	Exchanger<String> exchanger;
	String message;

	MessageThread(Exchanger<String> exchanger, String message) {
		this.exchanger = exchanger;
		this.message = message;
	}

	public void run() {
		try {
			System.out.println(this.getName() + " message: " + message);
			message = exchanger.exchange(message);
			System.out.println(this.getName() + " message: " + message);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
