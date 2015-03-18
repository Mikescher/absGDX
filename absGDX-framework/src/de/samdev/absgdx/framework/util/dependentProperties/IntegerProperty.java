package de.samdev.absgdx.framework.util.dependentProperties;

/**
 * A integer property
 */
public class IntegerProperty extends DependentProperty{
	private int value;
	
	/**
	 * Creates a new IntegerProperty
	 * 
	 * @param name the name of the property
	 * @param startValue the initial value
	 * @param dependsOn the parent-property (can be null)
	 */
	public IntegerProperty(String name, int startValue, DependentProperty dependsOn) {
		super(name, dependsOn);
		
		this.value = startValue;
	}

	@Override
	public String serialize() {
		return Integer.toString(value);
	}

	@Override
	public void deserialize(String rawvalue) {
		try {			
			this.value =  Integer.parseInt(rawvalue);
		} catch (NumberFormatException e) {
			this.value = 0; // TODO Log it or something
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
	public void set(int newvalue) {
		this.value = newvalue;
	}
	
	/**
	 * Gets the current value
	 * 
	 * @return the value of this property
	 */
	public int get() {
		return this.value;
	}
}
