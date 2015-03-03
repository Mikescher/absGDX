package de.samdev.absgdx.framework.menu.elements;

import java.util.List;

public class MenuFrame extends MenuPanel {

	public MenuFrame(List<MenuElement> children) {
		super(children);
		
		pack();
	}
	
	@Override
	public void pack() {
		super.pack();
		
		setDepth(0);
	}
}
