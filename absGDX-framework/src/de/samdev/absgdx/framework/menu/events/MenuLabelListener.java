package de.samdev.absgdx.framework.menu.events;

import de.samdev.absgdx.framework.menu.elements.MenuBaseElement;

/**
 * An event listener for MenuLabel
 */
public interface MenuLabelListener extends MenuBaseElementListener {
	/**
	 * Called when the content changes
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 * @param newtext the new text
	 */
	public void onContentChanged(MenuBaseElement element, String identifier, String newtext); //TODO add event (is currently not called)
}
