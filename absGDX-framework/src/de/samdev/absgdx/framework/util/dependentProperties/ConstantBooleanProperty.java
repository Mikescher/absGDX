package de.samdev.absgdx.framework.util.dependentProperties;

/**
 * A true/false immutable property
 */
public class ConstantBooleanProperty extends DependentProperty {
	private final boolean value;
	
	/**
	 * Creates a new ConstantBooleanProperty
	 * 
	 * @param name the name of the property
	 * @param fixValue the (fix) value
	 * @param dependsOn the parent-property (can be null)
	 */
	public ConstantBooleanProperty(String name, boolean fixValue, DependentProperty dependsOn) {
		super(name, dependsOn);
		
		this.value = fixValue;
	}

	@Override
	public String serialize() {
		return value ? BooleanProperty.REP_TRUE : BooleanProperty.REP_FALSE;
	}

	@Override
	public void deserialize(String rawvalue) {
		// Do nothing
	}
	
	/**
	 * Gets the current value
	 * 
	 * @return the value of this property
	 */
	public boolean get() {
		return this.value;
	}

	@Override
	public boolean getBooleanValue() {
		return value;
	}
}
