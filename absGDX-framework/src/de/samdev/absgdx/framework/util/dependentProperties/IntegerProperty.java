package de.samdev.absgdx.framework.util.dependentProperties;

public class IntegerProperty extends DependentProperty{
	private int value;
	
	public IntegerProperty(String name, int startValue, DependentProperty dependsOn) {
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
			this.value =  Integer.parseInt(rawvalue);
		} catch (NumberFormatException e) {
			this.value = 0; // TODO Log it or something
		}
	}

	@Override
	public boolean getBooleanValue() {
		return true;
	}
	
	public void setValue(int newvalue) {
		this.value = newvalue;
	}
	
	public int getValue() {
		return this.value;
	}
}
