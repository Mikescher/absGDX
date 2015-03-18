package de.samdev.absgdx.framework.util.dependentProperties;

/**
 * A string property
 */
public class StringProperty extends DependentProperty {
	private String value;
	
	/**
	 * Creates a new StringProperty
	 * 
	 * @param name the name of the property
	 * @param startValue the initial value
	 * @param dependsOn the parent-property (can be null)
	 */
	public StringProperty(String name, String startValue, DependentProperty dependsOn) {
		super(name, dependsOn);
		
		this.value = startValue;
	}

	@Override
	public String serialize() {
		return value; //TODO escape ??
	}

	@Override
	public void deserialize(String rawvalue) {
		this.value =  rawvalue;
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
	public void set(String newvalue) {
		this.value = newvalue;
	}
	
	/**
	 * Gets the current value
	 * 
	 * @return the value of this property
	 */
	public String get() {
		return this.value;
	}
}
