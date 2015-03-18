package de.samdev.absgdx.framework.util.dependentProperties;

/**
 * A float property
 */
public class FloatProperty extends DependentProperty{
	private float value;
	
	/**
	 * Creates a new FloatProperty
	 * 
	 * @param name the name of the property
	 * @param startValue the initial value
	 * @param dependsOn the parent-property (can be null)
	 */
	public FloatProperty(String name, float startValue, DependentProperty dependsOn) {
		super(name, dependsOn);
		
		this.value = startValue;
	}

	@Override
	public String serialize() {
		return Float.toString(value);
	}

	@Override
	public void deserialize(String rawvalue) {
		try {			
			this.value =  Float.parseFloat(rawvalue);
		} catch (NumberFormatException e) {
			this.value = 0.0f; // TODO Log it or something
		}
	}

	@Override
	public boolean getBooleanValue() {
		return true;
	}
	
	/**
	 * Sets the value
	 * 
	 * @param newvalue the new value
	 */
	public void set(float newvalue) {
		this.value = newvalue;
	}
	
	/**
	 * Gets the current value
	 * 
	 * @return the value of this property
	 */
	public float get() {
		return this.value;
	}
}
