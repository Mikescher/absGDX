package de.samdev.absgdx.framework.util.dependentProperties;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public abstract class DependentProperty {
	public final String name;
	public final DependentProperty dependsOn;
	
	private final List<DependentProperty> children = new ArrayList<DependentProperty>();
	
	public DependentProperty(String name, DependentProperty dependsOn) {
		super();
		this.name = name;
		this.dependsOn = dependsOn;
		
		if (dependsOn != null)
			dependsOn.registerChild(this);
	}

	public boolean isActive() {
		if (getBooleanValue()) return dependsOn == null || dependsOn.isActive();
		return false;
	}
	
	public void getTreeDebugString(StringBuilder b, int depth) {
		b.append(StringUtils.repeat(" ", depth * 3));
		b.append(name);
		b.append(getBooleanValue() ? " [X]" : " [ ]");
		b.append(System.lineSeparator());
		
		for (DependentProperty child : children) {
			child.getTreeDebugString(b, depth + 1);
		}
	}
	
	public void registerChild(DependentProperty child) {
		children.add(child);
	}
	
	public abstract String serialize();
	public abstract void deserialize(String rawvalue);
	
	public abstract boolean getBooleanValue();
}
