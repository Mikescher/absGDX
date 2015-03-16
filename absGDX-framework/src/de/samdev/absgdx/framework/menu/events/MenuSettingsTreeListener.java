package de.samdev.absgdx.framework.menu.events;

import de.samdev.absgdx.framework.menu.elements.MenuBaseElement;
import de.samdev.absgdx.framework.util.dependentProperties.DependentProperty;

/**
 * An event listener for MenuSettingsTree
 */
public interface MenuSettingsTreeListener extends MenuBaseElementListener {
	/**
	 * Called when a DependentProperty was changed by this component
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 * @param property the changed property
	 */
	public void onPropertyChanged(MenuBaseElement element, String identifier, DependentProperty property); //TODO add event (is currently not called)
}
