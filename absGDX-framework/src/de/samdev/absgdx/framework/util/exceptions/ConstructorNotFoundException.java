package de.samdev.absgdx.framework.util.exceptions;

public class ConstructorNotFoundException extends Exception {
	private static final long serialVersionUID = -4785629136981676053L;

	public ConstructorNotFoundException(String s) {
		super(s);
	}
	
	public ConstructorNotFoundException(Exception e) {
		super(e);
	}
}
