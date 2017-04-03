package com.anishsneh.demo.quick.core.concurrent;

/**
 * 
 * ThreadLocal is an alternative to the usage of global variable passed from methods to methods as argument (brute force technique) 
 * or static variable (visible within the current ClassLoader).
 * However, unlike global or static variable, the ThreadLocal scope is limited to the execution of the current thread.
 * A ThreadLocal attached to Thread1 cannot be access by Thread2 and vice versa.
 *
 */
public class ThreadLocalExample {

	public static void main(final String[] args) {
		new Thread(new Runnable() {
            public void run() {
            	UserTransaction threadContext = UserTransaction.get();
                threadContext.setTransactionId(1l);
                threadContext.setUserId("User 1");
                //here we call a method where the thread context is not passed as parameter
                PrintThreadContextValues.printThreadContextValues();
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
            	UserTransaction threadContext = UserTransaction.get();
                threadContext.setTransactionId(2l);
                threadContext.setUserId("User 2");
                //here we call a method where the thread context is not passed as parameter
                PrintThreadContextValues.printThreadContextValues();
            }
        }).start();
	}
}

class UserTransaction {
	
	private String userId;
	private Long transactionId;

	private static ThreadLocal<UserTransaction> threadLocal = new ThreadLocal<UserTransaction>() {
		@Override
		protected UserTransaction initialValue() {
			return new UserTransaction();
		}

	};

	public static UserTransaction get() {
		return threadLocal.get();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String toString() {
		return "userId:" + userId + ",transactionId:" + transactionId;
	}
}

class PrintThreadContextValues {
    public static void printThreadContextValues(){
        System.out.println(UserTransaction.get());
    }
}