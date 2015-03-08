package com.anishsneh.demo.dwr.ajax.reverse;

import java.io.Serializable;

/**
 * The Class Message.
 * 
 * @author Anish Sneh
 * 
 */
public class Message implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -14613791181674419L;

	/** The data. */
	private String data = null;
	
	/**
	 * Instantiates a new message.
	 *
	 * @param data the data
	 */
	public Message(final String data){
		this.data = data;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(final String data) {
		this.data = data;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[data=" + data + "]";
	}
	
}
