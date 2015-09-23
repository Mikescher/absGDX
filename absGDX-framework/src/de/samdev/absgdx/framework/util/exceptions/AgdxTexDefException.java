package de.samdev.absgdx.framework.util.exceptions;

/**
 * Abstract base class for exception with agdxtexdef files
 *
 */
public abstract class AgdxTexDefException extends Exception {
	private static final long serialVersionUID = 1948750367694176069L;

	/**
	 * Creates a new AgdxTexDefException
	 */
	public AgdxTexDefException() {
		super();
	}

	/**
	 * Creates a new AgdxTexDefException
	 * 
	 * @param s
	 */
	public AgdxTexDefException(String s) {
		super(s);
	}
	
	/**
	 * Creates a new AgdxTexDefException
	 * 
	 * @param e
	 */
	public AgdxTexDefException(Exception e) {
		super(e);
	}
}
