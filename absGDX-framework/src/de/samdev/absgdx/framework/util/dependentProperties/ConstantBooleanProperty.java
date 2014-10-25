package de.samdev.absgdx.framework.util.dependentProperties;

public class ConstantBooleanProperty extends DependentProperty {
	public final static String REP_TRUE = "true";
	public final static String REP_FALSE = "false";
	
	private final boolean value;
	
	public ConstantBooleanProperty(String name, boolean fixValue, DependentProperty dependsOn) {
		super(name, dependsOn);
		
		this.value = fixValue;
	}

	@Override
	public String serialize() {
		return value ? REP_TRUE : REP_FALSE;
	}

	@Override
	public void deserialize(String rawvalue) {
		// Do nothing
	}
	
	public boolean get() {
		return this.value;
	}

	@Override
	public boolean getBooleanValue() {
		return value;
	}
}
