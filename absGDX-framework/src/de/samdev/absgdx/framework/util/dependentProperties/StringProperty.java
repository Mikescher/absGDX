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
		return value != null;
	}
	
	public void set(String newvalue) {
		this.value = newvalue;
	}
	
	public String get() {
		return this.value;
	}
}
