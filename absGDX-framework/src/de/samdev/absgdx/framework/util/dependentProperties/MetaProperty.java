package de.samdev.absgdx.framework.util.dependentProperties;

/**
 * A meta property is used to categorize the property tree
 *
 * It is always true
 */
public class MetaProperty extends ConstantBooleanProperty {

	/**
	 * Creates a new MetaProperty
	 * 
	 * @param name the name of the property
	 * @param dependsOn the parent-property (can be null)
	 */
	public MetaProperty(String name, DependentProperty dependsOn) {
		super(name, true, dependsOn);
	}

}
