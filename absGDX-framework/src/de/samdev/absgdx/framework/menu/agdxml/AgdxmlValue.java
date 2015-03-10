package de.samdev.absgdx.framework.menu.agdxml;

import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

/**
 * A single AGDXML value (eg position, width, height ...)
 * 
 * this is an immutable class
 */
public class AgdxmlValue {

	/** the numerical component */
	public final float value;
	/** the unit of the value */
	public final AgdxmlValueUnit unit;
	
	/**
	 * Create a new instance of AgdxmlValue
	 * 
	 * @param value the numerical component
	 * @param unit the unit of the value
	 */
	public AgdxmlValue(float value, AgdxmlValueUnit unit) {
		this.value = value;
		this.unit = unit;
	}

	/**
	 * Get this as an absolute value
	 * (does not allow "WEIGHT" as unit)
	 * 
	 * @param referencevalue the reference value (for the percentage unit)
	 * @return this as an absolute value (in pixels)
	 * @throws AgdxmlParsingException if there are errors in the value
	 */
	public float getStarless(float referencevalue) throws AgdxmlParsingException {
		return get(referencevalue, 0, 0, false);
	}

	/**
	 * Get this as an absolute value
	 * 
	 * @param referencevalue the reference value (for the PERCENTAGE unit)
	 * @param weight_sum the sum of all weights in the parent (for the WEIGHT unit)
	 * @param freeDistributingSpace the amount of usable space by the WEIGHT values (for the WEIGHT unit)
	 * @param allowStar allow WEIGHT units (throws AgdxmlParsingException if used but not allowed)
	 * @return this as an absolute value (in pixels)
	 * @throws AgdxmlParsingException if there are errors in the value
	 */
	public float get(float referencevalue, int weight_sum, float freeDistributingSpace, boolean allowStar) throws AgdxmlParsingException {
		switch (unit) {
		case PIXEL:
			return value;
		case PERCENTAGE:
			return (value/100f) * referencevalue;
		case WEIGHT:
			if (allowStar && weight_sum == 0)
				throw new AgdxmlParsingException("Star notation failed: sum is zero");
			else if (allowStar)
				return (value / weight_sum) * freeDistributingSpace;
			else
				throw new AgdxmlParsingException("Star notation not allowed");
		default:
			throw new AgdxmlParsingException("WTF");
		}
	}
}
