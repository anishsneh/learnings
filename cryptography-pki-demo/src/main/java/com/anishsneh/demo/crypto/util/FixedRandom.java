package com.anishsneh.demo.crypto.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class FixedRandom, used for testing purpose only
 * 
 * @author Anish Sneh
 */
@SuppressWarnings("all")
public class FixedRandom extends SecureRandom {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(FixedRandom.class);

	/** The sha. */
	private MessageDigest sha;
	
	/** The state. */
	private byte[] state;

	/**
	 * Instantiates a new fixed random.
	 */
	public FixedRandom() {
		try {
			this.sha = MessageDigest.getInstance("SHA-1");
			this.state = sha.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Can't find SHA-1!");
		}
	}

	/* (non-Javadoc)
	 * @see java.security.SecureRandom#nextBytes(byte[])
	 */
	public void nextBytes(final byte[] bytes) {
		int off = 0;
		sha.update(state);
		while (off < bytes.length) {
			state = sha.digest();
			if (bytes.length - off > state.length) {
				System.arraycopy(state, 0, bytes, off, state.length);
			} else {
				System.arraycopy(state, 0, bytes, off, bytes.length - off);
			}
			off += state.length;
			sha.update(state);
		}
	}
}