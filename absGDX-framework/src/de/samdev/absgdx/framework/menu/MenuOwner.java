package de.samdev.absgdx.framework.menu;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.menu.elements.MenuFrame;

/**
 * This interface is the base of an menu/hud
 * 
 * it can hold an tree of MenuElements
 *
 * The default implementer are MenuLayer (MENU) and GameLayer (HUD)
 *
 */
public interface MenuOwner {
	
	/**
	 * This method gets called when the parent layer gets an event and forwards it to the menu root
	 * 
	 * @param character the typed character
	 * @return true if the input was processed
	 */
	public boolean keyTyped(char character);

	/**
	 * This method gets called when the parent layer gets an event and forwards it to the menu root
	 * 
	 * @param amount the scroll amount
	 * @return true if the input was processed
	 */
	public boolean scrolled(int amount);

	/**
	 * This method gets called when the parent layer gets an event and forwards it to the menu root
	 * 
	 * @param keycode the typed key-code
	 * @return true if the input was processed
	 */
	public boolean keyDown(int keycode);
	
	/**
	 * @return the root element (a MenuFrame)
	 */
	public MenuFrame getMenuRoot();

	/**
	 * Get the overlaying game object
	 * 
	 * @return the current game object
	 */
	public AgdxGame getAgdxGame();
}
