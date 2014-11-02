package de.samdev.absgdx.framework.util.dependentProperties;

/**
 * A root property, can be used as root node (always true / doesn't depend on anything)
 *
 */
public class RootProperty extends MetaProperty {

	/**
	 * Creates a new RootProperty
	 * 
	 * @param name the name
	 */
	public RootProperty(String name) {
		super(name, null);
	}

	/**
	 * Gets a debug representation of the whole property-tree
	 * 
	 * @return
	 */
	public String getTreeDebugString() {
		StringBuilder sb = new StringBuilder();
		
		this.getTreeDebugString(sb, 0);
		
		return sb.toString();
	}
}
