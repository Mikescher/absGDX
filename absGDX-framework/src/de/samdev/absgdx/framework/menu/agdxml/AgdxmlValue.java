package de.samdev.absgdx.framework.menu.agdxml;

import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

public class AgdxmlValue {

	public final float value;
	public final AgdxmlGridDefinitionsUnit unit;
	
	public AgdxmlValue(float value, AgdxmlGridDefinitionsUnit unit) {
		this.value = value;
		this.unit = unit;
	}

	public float get(float referencevalue) throws AgdxmlParsingException {
		switch (unit) {
		case PIXEL:
			return value;
		case PERCENTAGE:
			return (value/100f) * referencevalue;
		case WEIGHT:
			throw new AgdxmlParsingException("Star notation not allowed");
		default:
			throw new AgdxmlParsingException("WTF");
		}
	}
}
