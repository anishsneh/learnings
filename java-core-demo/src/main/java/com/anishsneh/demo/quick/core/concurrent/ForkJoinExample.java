package com.anishsneh.demo.quick.core.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {

	public static void main(final String[] args) {

		// Check the number of available processors
		int processors = Runtime.getRuntime().availableProcessors();
		System.out.println("No of processors: " + processors);

		int n = 10;

		FibonacciProblem bigProblem = new FibonacciProblem(n);

		FibonacciTask task = new FibonacciTask(bigProblem);
		ForkJoinPool pool = new ForkJoinPool(processors);
		pool.invoke(task);

		long result = task.result;
		System.out.println("Computed Result: " + result);
	}

}

class FibonacciProblem {

	public int n;

	public FibonacciProblem(int n) {
		this.n = n;
	}

	public long solve() {
		return fibonacci(n);
	}

	private long fibonacci(int n) {
		System.out.println("Thread: " + Thread.currentThread().getName() + " calculates " + n);
		if (n <= 1)
			return n;
		else
			return fibonacci(n - 1) + fibonacci(n - 2);
	}

}

class FibonacciTask extends RecursiveTask<Long> {

	private static final long serialVersionUID = 6136927121059165206L;

	private static final int THRESHOLD = 5;

	private FibonacciProblem problem;
	public long result;

	public FibonacciTask(FibonacciProblem problem) {
		this.problem = problem;
	}

	@Override
	public Long compute() {
		if (problem.n < THRESHOLD) { // easy problem, don't bother with
										// parallelism
			result = problem.solve();
		} else {
			FibonacciTask worker1 = new FibonacciTask(new FibonacciProblem(problem.n - 1));
			FibonacciTask worker2 = new FibonacciTask(new FibonacciProblem(problem.n - 2));
			worker1.fork();
			result = worker2.compute() + worker1.join();

		}
		return result;
	}

}
