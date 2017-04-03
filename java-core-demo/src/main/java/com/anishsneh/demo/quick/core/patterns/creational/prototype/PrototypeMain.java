package com.anishsneh.demo.quick.core.patterns.creational.prototype;

/**
 * 
 * Prototype pattern refers to creating duplicate object while keeping
 * performance in mind. This type of design pattern comes under creational
 * pattern as this pattern provides one of the best ways to create an object.
 * This pattern involves implementing a prototype interface which tells to
 * create a clone of the current object. This pattern is used when creation of
 * object directly is costly. For example, an object is to be created after a
 * costly database operation. We can cache the object, returns its clone on next
 * request and update the database as and when needed thus reducing database
 * calls.
 *
 */
public class PrototypeMain {

	public static void main(final String args[]) {
		Person person1 = new Person("Fred");
		System.out.println("person 1:" + person1);
		Person person2 = (Person) person1.doClone();
		System.out.println("person 2:" + person2);

		Dog dog1 = new Dog("Wooof!");
		System.out.println("dog 1:" + dog1);
		Dog dog2 = (Dog) dog1.doClone();
		System.out.println("dog 2:" + dog2);
	}
}

interface Prototype {

	public Prototype doClone();

}

class Person implements Prototype {

	String name;

	public Person(String name) {
		this.name = name;
	}

	@Override
	public Prototype doClone() {
		return new Person(name);
	}

	public String toString() {
		return "This person is named " + name;
	}
}

class Dog implements Prototype {

	String sound;

	public Dog(String sound) {
		this.sound = sound;
	}

	@Override
	public Prototype doClone() {
		return new Dog(sound);
	}

	public String toString() {
		return "This dog says " + sound;
	}
}
