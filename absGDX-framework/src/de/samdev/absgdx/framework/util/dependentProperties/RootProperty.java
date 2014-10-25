package de.samdev.absgdx.framework.util.dependentProperties;

public class RootProperty extends MetaProperty {

	public RootProperty(String name) {
		super(name, null);
	}

	public String getTreeDebugString() {
		StringBuilder sb = new StringBuilder();
		
		this.getTreeDebugString(sb, 0);
		
		return sb.toString();
	}
}
