package de.samdev.absgdx.framework.util.dependentProperties;

public class FloatProperty extends DependentProperty{
	private float value;
	
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
	
	public void set(float newvalue) {
		this.value = newvalue;
	}
	
	public float get() {
		return this.value;
	}
}
