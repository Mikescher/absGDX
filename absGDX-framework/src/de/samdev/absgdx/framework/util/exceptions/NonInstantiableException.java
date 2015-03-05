package de.samdev.absgdx.framework.util.exceptions;

/**
 * Throw this in the (private) constructors of non instantiable classes
 */
public class NonInstantiableException extends Exception {
	private static final long serialVersionUID = 4554450301675740019L;

	/**
	 * Creates a new NonInstantiableException
	 */
	public NonInstantiableException() {
		super();
	}
}
