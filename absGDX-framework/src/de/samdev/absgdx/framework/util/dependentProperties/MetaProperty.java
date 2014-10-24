package de.samdev.absgdx.framework.util.dependentProperties;

public class MetaProperty extends ConstantBooleanProperty {

	public MetaProperty(String name, DependentProperty dependsOn) {
		super(name, true, dependsOn);
	}

}
