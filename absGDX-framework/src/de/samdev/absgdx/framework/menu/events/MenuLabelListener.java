package de.samdev.absgdx.framework.menu.events;

import de.samdev.absgdx.framework.menu.elements.MenuElement;

/**
 * An event listener for MenuLabel
 */
public interface MenuLabelListener extends MenuElementListener {
	/**
	 * Called when the content changes
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 * @param newtext the new text
	 */
	public void onContentChanged(MenuElement element, String identifier, String newtext);
}
