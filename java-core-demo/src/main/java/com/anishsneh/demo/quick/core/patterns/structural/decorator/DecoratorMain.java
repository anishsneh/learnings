package com.anishsneh.demo.quick.core.patterns.structural.decorator;

/**
 * 
 * @author Anish Sneh
 * 
 * Decorator pattern allows a user to add new functionality to an existing object without altering its structure. 
 * This type of design pattern comes under structural pattern as this pattern acts as a wrapper to existing class. 
 * This pattern creates a decorator class which wraps the original class and provides 
 * additional functionality keeping class methods signature intact.
 * 
 * The decorator pattern is a structural design pattern. 
 * Whereas inheritance adds functionality to classes, the decorator pattern adds functionality to objects by wrapping objects in other objects. 
 * Each time additional functionality is required, the object is wrapped in another object. 
 * JavaSW I/O streams are a well-known example of the decorator pattern. 
 *
 */
public class DecoratorMain {

	public static void main(final String args[]) {
		
		Shape circle = new Circle();

		Shape redCircle = new RedShapeDecorator(new Circle());

		Shape redRectangle = new RedShapeDecorator(new Rectangle());
		System.out.println("Circle with normal border");
		circle.draw();

		System.out.println("\nCircle of red border");
		redCircle.draw();

		System.out.println("\nRectangle of red border");
		redRectangle.draw();
	}
}

interface Shape {
	void draw();
}

class Rectangle implements Shape {

	@Override
	public void draw() {
		System.out.println("Shape: Rectangle");
	}
}

class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("Shape: Circle");
	}
}

abstract class ShapeDecorator implements Shape {
	protected Shape decoratedShape;

	public ShapeDecorator(Shape decoratedShape) {
		this.decoratedShape = decoratedShape;
	}

	public void draw() {
		decoratedShape.draw();
	}
}

class RedShapeDecorator extends ShapeDecorator {

	public RedShapeDecorator(Shape decoratedShape) {
		super(decoratedShape);
	}

	@Override
	public void draw() {
		decoratedShape.draw();
		setRedBorder(decoratedShape);
	}

	private void setRedBorder(Shape decoratedShape) {
		System.out.println("Border Color: Red");
	}
}
