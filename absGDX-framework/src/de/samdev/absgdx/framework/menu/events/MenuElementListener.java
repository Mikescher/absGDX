package de.samdev.absgdx.framework.menu.events;

import de.samdev.absgdx.framework.menu.elements.MenuElement;

/**
 * An event listener for a MenuElement
 */
public abstract interface MenuElementListener {
	/**
	 * Called when this component is clicked (down + release)
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 */
	public void onClicked(MenuElement element, String identifier);

	/**
	 * Called when this component is clicked (down)
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 */
	public void onPointerDown(MenuElement element, String identifier);

	/**
	 * Called when this component is clicked (released)
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 */
	public void onPointerUp(MenuElement element, String identifier);

	/**
	 * Called when the pointer enters this component
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 */
	public void onHover(MenuElement element, String identifier);

	/**
	 * Called when the pointer leaves this component
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 */
	public void onHoverEnd(MenuElement element, String identifier);

	/**
	 * Called when the component gains the focus
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 */
	public void onFocus(MenuElement element, String identifier);

	/**
	 * Called when the the component looses the focus
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 */
	public void onFocusLost(MenuElement element, String identifier);
}
