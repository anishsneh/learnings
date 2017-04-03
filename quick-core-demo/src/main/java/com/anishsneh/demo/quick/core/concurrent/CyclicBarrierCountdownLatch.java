package com.anishsneh.demo.quick.core.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/*<p>CyclicBarrier takes an (optional) Runnable task which is run once the common barrier condition is met.</p>
<p>It also allows you to get the number of clients waiting at the barrier and the number required to trigger the barrier. 
Once triggered the barrier is reset and can be used again.</p>

<p>For simple use cases  services starting etc a CountdownLatch is fine. A CyclicBarrier is useful for more complex co-ordination tasks. 
An example of such a thing would be parallel computation  where multiple subtasks are involved in the computation  kind of like MapReduce.</p>
<p>When using a CyclicBarrier, the assumption is that you specify the number of waiting threads that trigger the barrier. If you specify 5, 
you must have at least 5 threads to call await().</p>

<p>When using a CountDownLatch, you specify the number of calls to countDown() that will result in all waiting threads being released. 
This means that you can use a CountDownLatch with only a single thread.</p>

<p>Why would you do that?, you may say. Imagine that you are using a mysterious API coded by someone else that performs callbacks.
You want one of your threads to wait until a certain callback has been called a number of times. You have no idea which threads the callback will be called on. 
In this case, a CountDownLatch is perfect, whereas I cant think of any way to implement this using a CyclicBarrier.</p>

CyclicBarrier: Seems for homogeneous threads
CountDownLatch: Seems for heterogeneous threads

*/

public class CyclicBarrierCountdownLatch {
	
	public static final int N = 10;
	
	public static void main(final String[] args) {
		CyclicBarrierCountdownLatch cbcl = new CyclicBarrierCountdownLatch();
		//cbcl.testCyclicBarrier();
		cbcl.testCountdownLatch();
	}
	
	public void testCyclicBarrier(){
	    final CyclicBarrier cb = new CyclicBarrier(N);  
	    for (int i = 0; i < N; i++) {  
	        final int idx = i;  
	        new Thread(new Runnable() {  
	            public void run() {  
	                System.out.println("T" + idx + ": await");  
	                try {  
	                    cb.await();  
	                } catch (InterruptedException ex) {  
	                    System.out.println("T" + idx + ": interrupted");  
	                    return;  
	                } catch (BrokenBarrierException ex) {  
	                    System.out.println("T" + idx + ": broken");  
	                    return;  
	                }  
	                System.out.println("T" + idx + ": continue");  
	            }  
	        }).start();  
	    }  
	}
	
	public void testCountdownLatch(){
	    final CountDownLatch cdl = new CountDownLatch(N);  
        new Thread(new Runnable() {  
            public void run() {  
                System.out.println("awaiting...");  
                try {  
                    cdl.await();  
                } catch (InterruptedException ex) {  
                    System.out.println("await has been iterrupted");                  
                    return;  
                }  
                System.out.println("ready");                  
            }  
        }).start();  
          
        for (int i = 0; i < N; i++) {  
            final int idx = i;  
            new Thread(new Runnable() {  
                public void run() {  
                    System.out.println("T" + idx + ":countDown");  
                    cdl.countDown();  
                    System.out.println("T" + idx + ":continue");                  
                }  
            }).start();  
        }  
	}	

}
