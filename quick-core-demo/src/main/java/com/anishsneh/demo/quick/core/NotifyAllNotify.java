package com.anishsneh.demo.quick.core;

public class NotifyAllNotify {
	
	public static void main(final String[] args) throws Exception{
		final NotifyAllNotify nof = new NotifyAllNotify();
		for(int i = 0;i < 10; ++i )
		{
			final Thread1 f = new Thread1( nof, false );
			f.setName( "Waiter-" + (i+1) );
			f.start();
		}
		final Thread1 f = new Thread1( nof, true );
		f.setName( "Notifier" );
		f.start();
	}
	
	public void checkNotifyAllNotify(final boolean toNotify) throws Exception{
		if(toNotify)
		{
			synchronized (this) {
				System.out.println( Thread.currentThread().getName() + " Going to notify()");
				//CALL notify() OR notifyAll()
				//notify();				
				notifyAll();			
			}
		}
		else
		{
			synchronized (this) {
				System.out.println(Thread.currentThread().getName() + " Going to wait()");
				wait();			
			}
			//Incase of notify() only one thread executes following line
			//Incase of notifyAll() all threads executes following line
			System.out.println(Thread.currentThread().getName() + " After wait() 1");			
			
			//Note that lock will be held by the single thread only whether we call notify() or notifyAll()
			//The only difference is BETWEEN synchronized{wait()} block LINE:30 and next synchronized{} block LINE:40
			synchronized (this) {
				System.out.println(Thread.currentThread().getName() + " After wait() synchronized 2");						
			}
		}
		
	}
}

class Thread1 extends Thread{
	private NotifyAllNotify y;
	private boolean notifier;
	public Thread1(final NotifyAllNotify y, final boolean notifier )
	{
		this.y = y;
		this.notifier = notifier;
	}
	public void run(){
		try {
			y.checkNotifyAllNotify( this.notifier);
		} 
		catch (final Exception e) {			
			e.printStackTrace();
		}
	}
}
