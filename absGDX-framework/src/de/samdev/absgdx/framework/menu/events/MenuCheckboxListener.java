package de.samdev.absgdx.framework.menu.events;

import de.samdev.absgdx.framework.menu.elements.MenuBaseElement;

/**
 * An event listener for MenuCheckbox
 */
public interface MenuCheckboxListener extends MenuBaseElementListener {
	/**
	 * Called when this component is checked or unchecked
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 * @param checked the new isChecked() value
	 */
	public void onChecked(MenuBaseElement element, String identifier, boolean checked); //TODO add event (is currently not called)
}
