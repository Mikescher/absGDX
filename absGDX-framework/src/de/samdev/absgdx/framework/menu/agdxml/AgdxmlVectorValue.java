package de.samdev.absgdx.framework.menu.agdxml;

/**
 * A 2 Tuple consisting of two AgdxmlValue's
 * (Immutable class)
 */
public class AgdxmlVectorValue {
	/** the first  value */
	public final AgdxmlValue value_0;
	/** the second value */
	public final AgdxmlValue value_1;
	
	/**
	 * Create a new AgdxmlVectorValue
	 * 
	 * @param v0 the first value
	 * @param v1 the second value
	 */
	public AgdxmlVectorValue(AgdxmlValue v0, AgdxmlValue v1) {
		super();
		
		this.value_0 = v0;
		this.value_1 = v1;
	}
}
