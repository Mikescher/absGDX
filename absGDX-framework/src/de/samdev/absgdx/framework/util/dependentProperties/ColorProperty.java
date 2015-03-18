package de.samdev.absgdx.framework.util.dependentProperties;

import com.badlogic.gdx.graphics.Color;

/**
 * A string property
 */
public class ColorProperty extends DependentProperty {
	private final static String NULL_VALUE = "NULL";
	
	private Color value;
	
	/**
	 * Creates a new StringProperty
	 * 
	 * @param name the name of the property
	 * @param startValue the initial value
	 * @param dependsOn the parent-property (can be null)
	 */
	public ColorProperty(String name, Color startValue, DependentProperty dependsOn) {
		super(name, dependsOn);
		
		this.value = startValue;
	}

	@Override
	public String serialize() {
		return (value == null) ? NULL_VALUE : value.toString(); //TODO escape ??
	}

	@Override
	public void deserialize(String rawvalue) {
		if (rawvalue.equals(NULL_VALUE))
			this.value = null;
		else
			this.value =  Color.valueOf(rawvalue);
	}

	@Override
	public boolean getBooleanValue() {
		return value != null;
	}
	
	/**
	 * Sets the value
	 * 
	 * @param newvalue
	 */
	public void set(Color newvalue) {
		this.value = newvalue;
	}
	
	/**
	 * Gets the current value
	 * 
	 * @return the value of this property
	 */
	public Color get() {
		return this.value;
	}
}
