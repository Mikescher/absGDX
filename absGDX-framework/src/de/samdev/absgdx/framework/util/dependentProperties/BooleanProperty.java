package de.samdev.absgdx.framework.util.dependentProperties;

/**
 * A true/false property
 */
public class BooleanProperty extends DependentProperty {
	/** Text representation of "true" */
	public final static String REP_TRUE = "true";
	/** Text representation of "false" */
	public final static String REP_FALSE = "false";
	
	private boolean value;
	
	/**
	 * Creates a new BooleanProperty
	 * 
	 * @param name the name of the property
	 * @param startValue the initial value
	 * @param dependsOn the parent-property (can be null)
	 */
	public BooleanProperty(String name, boolean startValue, DependentProperty dependsOn) {
		super(name, dependsOn);
		
		this.value = startValue;
	}

	@Override
	public String serialize() {
		return value ? REP_TRUE : REP_FALSE;
	}

	@Override
	public void deserialize(String rawvalue) {
		this.value =  rawvalue.toLowerCase().equals(REP_TRUE);
	}

	@Override
	public boolean getBooleanValue() {
		return value;
	}
	
	/**
	 * Sets the value
	 * 
	 * @param newvalue
	 */
	public void set(boolean newvalue) {
		this.value = newvalue;
	}
	
	/**
	 * Gets the current value
	 * 
	 * @return
	 */
	public boolean get() {
		return this.value;
	}
	
	/**
	 * Switched the current value (true <-> false)
	 * 
	 * @return
	 */
	public boolean doSwitch() {
		set(! get());
		return get();
	}
}
