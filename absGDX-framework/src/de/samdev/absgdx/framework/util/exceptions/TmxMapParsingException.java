package de.samdev.absgdx.framework.util.exceptions;

/**
 * Gets thrown when an Error while parsing a TMX map occurs
 */
public class TmxMapParsingException extends Exception {
	private static final long serialVersionUID = -4785629136981676053L;

	/**
	 * Creates a new TmxMapParsingException
	 * 
	 * @param s
	 */
	public TmxMapParsingException(String s) {
		super(s);
	}
	
	/**
	 * Creates a new TmxMapParsingException
	 * 
	 * @param e
	 */
	public TmxMapParsingException(Exception e) {
		super(e);
	}
}
