package de.samdev.absgdx.framework.menu.events;

import de.samdev.absgdx.framework.menu.elements.MenuBaseElement;

/**
 * An event listener for a MenuBaseElement
 */
public abstract interface MenuBaseElementListener {
	/**
	 * Called when this component is clicked (down + release)
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 */
	public void onClicked(MenuBaseElement element, String identifier);

	/**
	 * Called when this component is clicked (down)
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 */
	public void onPointerDown(MenuBaseElement element, String identifier);

	/**
	 * Called when this component is clicked (released)
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 */
	public void onPointerUp(MenuBaseElement element, String identifier);

	/**
	 * Called when the pointer enters this component
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 */
	public void onHover(MenuBaseElement element, String identifier);

	/**
	 * Called when the pointer leaves this component
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 */
	public void onHoverEnd(MenuBaseElement element, String identifier);

	/**
	 * Called when the component gains the focus
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 */
	public void onFocus(MenuBaseElement element, String identifier);

	/**
	 * Called when the the component looses the focus
	 * 
	 * @param element the element of this event
	 * @param identifier the unique element identifier
	 */
	public void onFocusLost(MenuBaseElement element, String identifier);
}
