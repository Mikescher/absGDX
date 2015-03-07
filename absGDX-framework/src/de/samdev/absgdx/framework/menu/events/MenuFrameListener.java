package de.samdev.absgdx.framework.menu.events;

import de.samdev.absgdx.framework.menu.elements.MenuElement;

/**
 * An event listener for MenuFrame
 */
public interface MenuFrameListener extends MenuElementListener {
	/**
	 * Called when this component is checked or unchecked
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 * @param checked the new isChecked() value
	 */
	public void onChecked(MenuElement element, String identifier, boolean checked);
}
