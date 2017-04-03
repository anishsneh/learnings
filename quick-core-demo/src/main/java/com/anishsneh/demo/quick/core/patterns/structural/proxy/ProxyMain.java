package com.anishsneh.demo.quick.core.patterns.structural.proxy;

/**
 * 
 * In proxy pattern, a class represents functionality of another class. This
 * type of design pattern comes under structural pattern. In proxy pattern, we
 * create object having original object to interface its functionality to outer
 * world.
 *
 */
public class ProxyMain {

	public static void main(final String[] args) {
		Image image = new ProxyImage("test_10mb.jpg");
		// image will be loaded from disk
		image.display();
		System.out.println("");
		// image will not be loaded from disk
		image.display();
	}
}

interface Image {
	void display();
}

class RealImage implements Image {

	private String fileName;

	public RealImage(final String fileName) {
		this.fileName = fileName;
		loadFromDisk(fileName);
	}

	@Override
	public void display() {
		System.out.println("Displaying " + fileName);
	}

	private void loadFromDisk(final String fileName) {
		System.out.println("Loading " + fileName);
	}
}

class ProxyImage implements Image {

	private RealImage realImage;
	private String fileName;

	public ProxyImage(final String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void display() {
		if (realImage == null) {
			realImage = new RealImage(fileName);
		}
		realImage.display();
	}
}