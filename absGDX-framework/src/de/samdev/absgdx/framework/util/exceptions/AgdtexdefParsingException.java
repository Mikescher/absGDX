package de.samdev.absgdx.framework.util.exceptions;

/**
 * Gets thrown when an Error while parsing a AGDTEXDEF file occurs
 */
public class AgdtexdefParsingException extends Exception {
	private static final long serialVersionUID = -4927888687576415436L;

	/**
	 * Creates a new AgdtexdefParsingException
	 */
	public AgdtexdefParsingException() {
		super();
	}

	/**
	 * Creates a new AgdtexdefParsingException
	 * 
	 * @param s
	 */
	public AgdtexdefParsingException(String s) {
		super(s);
	}
	
	/**
	 * Creates a new AgdtexdefParsingException
	 * 
	 * @param e
	 */
	public AgdtexdefParsingException(Exception e) {
		super(e);
	}
}
