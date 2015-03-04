package de.samdev.absgdx.framework.menu.events;

import de.samdev.absgdx.framework.menu.elements.MenuElement;

/**
 * An event listener for MenuEdit
 */
public interface MenuEditListener extends MenuElementListener {
	/**
	 * Called when the text changes
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 * @param newtext the new text
	 */
	public void onTextChanged(MenuElement element, String identifier, String newtext);
}
