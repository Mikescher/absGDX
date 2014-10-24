package de.samdev.absgdx.framework.util.dependentProperties;

public abstract class DependentProperty {

	public final String name;
	public final DependentProperty dependsOn;
	
	public DependentProperty(String name, DependentProperty dependsOn) {
		super();
		this.name = name;
		this.dependsOn = dependsOn;
	}

	public boolean isActive() {
		if (getBooleanValue()) return dependsOn == null || dependsOn.isActive();
		return false;
	}
	
	public abstract String serialize();
	public abstract void deserialize(String rawvalue);
	
	public abstract boolean getBooleanValue();
}
