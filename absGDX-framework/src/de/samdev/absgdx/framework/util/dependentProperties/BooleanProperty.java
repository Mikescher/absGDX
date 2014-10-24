package de.samdev.absgdx.framework.util.dependentProperties;

public class BooleanProperty extends DependentProperty {
	public final static String REP_TRUE = "true";
	public final static String REP_FALSE = "false";
	
	private boolean value;
	
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
	
	public void setValue(boolean newvalue) {
		this.value = newvalue;
	}
	
	public boolean getValue() {
		return this.value;
	}
}
