package de.samdev.absgdx.framework.menu.elements;

import java.util.ArrayList;
import java.util.List;

import de.samdev.absgdx.framework.layer.MenuLayer;
import de.samdev.absgdx.framework.menu.events.MenuFrameListener;

/**
 * this is the root element of every menu
 *
 */
public class MenuFrame extends MenuPanel {
	
	/**
	 * Create a new (empty) MenuFrame
	 */
	public MenuFrame() {
		this(new ArrayList<MenuElement>());
	}
	
	/**
	 * Create a new MenuFrame (with children)
	 * 
	 * @param children the children of this frame
	 */
	public MenuFrame(List<MenuElement> children) {
		super(children);
		
		pack(null, null);
	}
	
	@Override
	public void pack(MenuLayer layer, MenuElement owner) {
		super.pack(layer, owner);
		
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
