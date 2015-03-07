package de.samdev.absgdx.framework.util.dependentProperties;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * A dependent Property.
 * 
 * Dependent properties form a tree, each property (except the root) depend on an other one.
 * And each property is only "active" when the parent node is active
 */
public abstract class DependentProperty {
	/**
	 * The name of the property
	 */
	public final String name;
	
	/**
	 * The property this one depends on (can be null)
	 */
	public final DependentProperty dependsOn;
	
	private final List<DependentProperty> children = new ArrayList<DependentProperty>();
	
	/**
	 * Creates a new DependentProperty
	 * 
	 * @param name the name of the property
	 * @param dependsOn the parent-property (can be null)
	 */
	public DependentProperty(String name, DependentProperty dependsOn) {
		super();
		this.name = name;
		this.dependsOn = dependsOn;
		
		if (dependsOn != null)
			dependsOn.registerChild(this);
	}

	/**
	 * Gets if this Property is enabled (the property and all parents must be "on")
	 * 
	 * @return
	 */
	public boolean isActive() {
		if (getBooleanValue()) return dependsOn == null || dependsOn.isActive();
		return false;
	}
	
	/**
	 * Creates an entry in the debug tree
	 * 
	 * @param b the used StringBuilder
	 * @param depth the current depth
	 */
	public void getTreeDebugString(StringBuilder b, int depth) {
		b.append(StringUtils.repeat(" ", depth * 3));
		b.append(name);
		b.append(getBooleanValue() ? " [X]" : " [ ]");
		b.append(System.lineSeparator());
		
		for (DependentProperty child : children) {
			child.getTreeDebugString(b, depth + 1);
		}
	}
	
	/**
	 * is called when an other property is created with this one as parent
	 * 
	 * @param child
	 */
	public void registerChild(DependentProperty child) {
		children.add(child);
	}
	
	/**
	 * Returns a string representation of this value
	 * 
	 * @return
	 */
	public abstract String serialize();
	
	/**
	 * Deserializes the value from a string representation
	 * 
	 * @param rawvalue
	 */
	public abstract void deserialize(String rawvalue);
	
	/**
	 * GEts the current value as an true/false value ( for isActive() )
	 * 
	 * @return
	 */
	public abstract boolean getBooleanValue();
	
	/**
	 * @return all childrens of this node
	 */
	public List<DependentProperty> getChildren() {
		return children;
	}
}
