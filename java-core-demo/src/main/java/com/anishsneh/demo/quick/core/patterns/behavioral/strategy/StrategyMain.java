package com.anishsneh.demo.quick.core.patterns.behavioral.strategy;

import java.io.File;
import java.util.ArrayList;

public class StrategyMain {

	public static void main(final String[] args) {
		CompressionContext ctx = new CompressionContext();
		// we could assume context is already set by preferences
		ctx.setCompressionStrategy(new ZipCompressionStrategy());
		// get a list of files...
		ctx.createArchive(new ArrayList<File>());
	}
}

// Strategy Interface
interface CompressionStrategy {
	public void compressFiles(ArrayList<File> files);
}

class ZipCompressionStrategy implements CompressionStrategy {
	public void compressFiles(ArrayList<File> files) {
		System.out.println("using ZIP approach");
	}
}

class RarCompressionStrategy implements CompressionStrategy {
	public void compressFiles(ArrayList<File> files) {
		System.out.println("using RAR approach");
	}
}

class CompressionContext {
	private CompressionStrategy strategy;

	// this can be set at runtime by the application preferences
	public void setCompressionStrategy(CompressionStrategy strategy) {
		this.strategy = strategy;
	}

	// use the strategy
	public void createArchive(ArrayList<File> files) {
		strategy.compressFiles(files);
	}
}