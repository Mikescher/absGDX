package de.samdev.absgdx.framework.util.exceptions;

public class TmxMapParsingException extends Exception {
	private static final long serialVersionUID = -4785629136981676053L;

	public TmxMapParsingException(String s) {
		super(s);
	}
	
	public TmxMapParsingException(Exception e) {
		super(e);
	}
}
