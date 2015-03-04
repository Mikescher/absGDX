package de.samdev.absgdx.framework.menu.elements;

import java.util.List;

import de.samdev.absgdx.framework.layer.MenuLayer;
import de.samdev.absgdx.framework.menu.events.MenuFrameListener;

/**
 * this is the root element of every menu
 *
 */
public class MenuFrame extends MenuPanel {

	/**
	 * Create a new MenuFrame (with children)
	 * 
	 * @param children the children of this frame
	 */
	public MenuFrame(List<MenuElement> children) {
		super(children);
		
		pack(null);
	}
	
	@Override
	public void pack(MenuLayer owner) {
		super.pack(owner);
		
		setDepth(0);
	}
	
	/**
	 * Adds a new listener
	 * 
	 * @param l the new listener
	 */
	public void addFrameListener(MenuFrameListener l) {
		super.addElementListener(l);
	}
}
