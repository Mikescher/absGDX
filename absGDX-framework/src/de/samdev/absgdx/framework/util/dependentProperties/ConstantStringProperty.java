package de.samdev.absgdx.framework.util.dependentProperties;

/**
 * A string property
 */
public class ConstantStringProperty extends DependentProperty {
	private String value;
	
	/**
	 * Creates a new StringProperty (non modifiable)
	 * 
	 * @param name the name of the property
	 * @param fixValue the (fix) value
	 * @param dependsOn the parent-property (can be null)
	 */
	public ConstantStringProperty(String name, String fixValue, DependentProperty dependsOn) {
		super(name, dependsOn);
		
		this.value = fixValue;
	}

	@Override
	public String serialize() {
		return value; //TODO escape ??
	}

	@Override
	public void deserialize(String rawvalue) {
		// Do nothing
	}

	@Override
	public boolean getBooleanValue() {
		return value != null;
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
