package de.samdev.absgdx.framework.util.exceptions;

/**
 * Gets thrown when an Error while parsing a AGDXML file occurs
 */
public class AgdxmlParsingException extends Exception {
	private static final long serialVersionUID = -4785629136981676053L;

	/**
	 * Creates a new AgdxmlParsingException
	 * 
	 * @param s
	 */
	public AgdxmlParsingException(String s) {
		super(s);
	}
	
	/**
	 * Creates a new AgdxmlParsingException
	 * 
	 * @param e
	 */
	public AgdxmlParsingException(Exception e) {
		super(e);
	}
}
