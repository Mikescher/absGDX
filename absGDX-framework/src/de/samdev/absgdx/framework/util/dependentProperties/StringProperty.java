package de.samdev.absgdx.framework.util.dependentProperties;

public class StringProperty extends DependentProperty {
	private String value;
	
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
		return true;
	}
	
	public void setValue(String newvalue) {
		this.value = newvalue;
	}
	
	public String getValue() {
		return this.value;
	}
}
