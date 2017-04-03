package com.anishsneh.demo.quick.core;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * NOTE THAT IF PARENT "DOES NOT" IMPLEMENT CLONEABLE AND CHILD CLASS "DOES"
 * IMPLEMENT, WHILE CLONNING CHILD OBJECT IT COPIES ALL THE PARENT PROPERTIES IN
 * CLONNED OBJECT (EVEN PARENT "DOES NOT" IMPLEMENT CLONEABLE INTERFACE).
 */

public class CloneParentChild {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {

		// Object
		final Employee emp = new Employee("Amardeep", 50000);
		emp.setHireDay(2005, 0, 0);

		// Normal clone
		final Employee emp1 = (Employee) emp.clone();
		emp1.raiseSalary(20);
		emp1.setHireDay(2008, 12, 31);
		System.out.println("Employee Clone hashCode=" + emp.hashCode() + "; Employee toString=" + emp.toString());
		System.out.println("Employee Clone hashCode=" + emp1.hashCode() + "; Employee Clone toString=" + emp1.toString());

		// Child object
		final Child c1 = new Child("Child1", "Parent1");
		System.out.println(c1.hashCode());
		System.out.println(c1.toString());

		// Child clone with
		// non-cloneable parent
		final Child c2 = (Child) c1.clone();
		System.out.println(c2.hashCode());
		System.out.println(c2.toString());
	}
}

class Parent {
	
	String parentName;
}

class Child extends Parent implements Cloneable {

	String childName;

	public Child(final String childName, final String parentName) {
		this.childName = childName;
		this.parentName = parentName;
	}

	public Object clone() {
		Child c2 = null;
		try {
			c2 = (Child) super.clone();
		} 
		catch (final CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return c2;
	}

	@Override
	public String toString() {
		return "childName: " + childName + "; parentName: " + parentName;
	}
}

class Employee implements Cloneable {

	private String name;
	private double salary;
	private Date hireDay;

	public Employee(final String str, final double dou) {
		name = str;
		salary = dou;
	}

	public Object clone() {
		try {
			final Employee cloned = (Employee) super.clone();
			cloned.hireDay = (Date) hireDay.clone();
			return cloned;
		} catch (final CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setHireDay(final int year, final int month, final int day) {
		hireDay = new GregorianCalendar(year, month - 1, day).getTime();
	}

	public void raiseSalary(final double byPercent) {
		final double raise = salary * byPercent / 100;
		salary += raise;
	}

	public String toString() {
		return "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
	}
}
