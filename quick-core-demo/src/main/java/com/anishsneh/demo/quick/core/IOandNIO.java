package com.anishsneh.demo.quick.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 
 * ---------------------------------------
 * 	IO 				 |	NIO
 * ------------------|--------------------
 *  Stream oriented  |	Buffer oriented
 *  Blocking IO 	 |	Non blocking IO
 *  				 |	Selectors
 *
 */
public class IOandNIO {
	public static void main(final String[] args) throws Exception {
		useIO();
		useNIO();
	}

	private static void useIO() throws Exception {
		final File file = new File("C:/Documents and Settings/ggne0602/My Documents/EJB3Demo.zip");
		final File oFile = new File("C:/Documents and Settings/ggne0602/My Documents/EJB3Demo01.zip");

		final long time1 = System.currentTimeMillis();
		final InputStream is = new FileInputStream(file);
		final FileOutputStream fos = new FileOutputStream(oFile);
		final byte[] buf = new byte[64 * 1024];
		int len = 0;
		while ((len = is.read(buf)) != -1) {
			fos.write(buf, 0, len);
		}
		fos.flush();
		fos.close();
		is.close();
		final long time2 = System.currentTimeMillis();
		System.out.println("IO Time taken: " + (time2 - time1) + " ms");
	}

	private static void useNIO() throws Exception {
		final RandomAccessFile fromFile = new RandomAccessFile("C:/Documents and Settings/ggne0602/My Documents/EJB3Demo.zip", "rw");
		final RandomAccessFile toFile = new RandomAccessFile("C:/Documents and Settings/ggne0602/My Documents/EJB3Demo02.zip", "rw");
		final long time1 = System.currentTimeMillis();
		final FileChannel fromChannel = fromFile.getChannel();		
		final FileChannel toChannel = toFile.getChannel();

		long position = 0;
		final long count = fromChannel.size();

		toChannel.transferFrom(fromChannel, position, count);
		fromChannel.transferTo(position, count, toChannel);
		long time2 = System.currentTimeMillis();
		System.out.println("NIO Time taken: " + (time2 - time1) + " ms");
	}
}
