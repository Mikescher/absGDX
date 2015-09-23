package de.samdev.absgdx.framework.util.exceptions;

/**
 * Gets thrown when an error while loading textures from an already parsed AGDTEXDEF file occurs
 */
public class AgdtexdefLoadException extends AgdxTexDefException {
	private static final long serialVersionUID = 4236560264535604705L;

	/**
	 * Creates a new AgdtexdefLoadException
	 */
	public AgdtexdefLoadException() {
		super();
	}

	/**
	 * Creates a new AgdtexdefLoadException
	 * 
	 * @param s
	 */
	public AgdtexdefLoadException(String s) {
		super(s);
	}
	
	/**
	 * Creates a new AgdtexdefLoadException
	 * 
	 * @param e
	 */
	public AgdtexdefLoadException(Exception e) {
		super(e);
	}
}
