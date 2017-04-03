package com.anishsneh.demo.quick.core.patterns.structural.flyweight;

/**
 * 
 * Flyweight pattern is primarily used to reduce the number of objects created and to decrease 
 * memory footprint and increase performance. This type of design pattern comes under structural pattern as 
 * this pattern provides ways to decrease object count thus improving the object structure of application. 
 * Flyweight pattern tries to reuse already existing similar kind objects by storing them and 
 * creates new object when no matching object is found.
 * 
 * OBJECT_POOL vs FLYWEIGHT
 * 
 * Pooled objects can simultaneously be used by a single "client" only. 
 * For that, a pooled object must be checked out from the pool, then it can be used by a client, 
 * and then the client must return the object back to the pool. 
 * Multiple instances of identical objects may exist, up to the maximal capacity of the pool.
 * 
 * In contrast, a Flyweight object is singleton, and it can be used simultaneously by multiple clients.
 * 
 * As for concurrent access, pooled objects can be mutable and they usually don't need to be thread safe, 
 * as typically, only one thread is going to use a specific instance at the same time. 
 * Flyweight must either be immutable (the best option), or implement thread safety.
 * 
 * flyweights are commonly immutable instances, while resources acquired from the pool usually are mutable.
 * 
 * object pool is a creational pattern and the flyweight is a structural pattern
 *
 */
public class FlyweightMain {

	public static void main(final String[] args) {

		FlyweightFactory flyweightFactory = FlyweightFactory.getInstance();

		for (int i = 0; i < 5; i++) {
			Flyweight flyweightAdder = flyweightFactory.getFlyweight("add");
			flyweightAdder.doMath(i, i);

			Flyweight flyweightMultiplier = flyweightFactory.getFlyweight("multiply");
			flyweightMultiplier.doMath(i, i);
		}
	}
}
