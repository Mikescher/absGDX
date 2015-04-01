package de.samdev.absgdx.framework.menu.elements;

import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.events.MenuRadioButtonListener;

/**
 * A switch-able Button (RadioGroup is parent element)
 */
public class MenuRadioButton extends MenuCheckBox {

	/**
	 * Creates a new MenuRadioButton
	 */
	public MenuRadioButton() {
		// NOP
	}

	/**
	 * Creates a new MenuRadioButton
	 * 
	 * @param texprovider the texture provider for this element
	 */
	public MenuRadioButton(GUITextureProvider texprovider) {
		super(texprovider);
	}

	/**
	 * Creates a new MenuRadioButton
	 * 
	 * @param identifier the unique button identifier
	 * @param texprovider the texture provider for this element
	 */
	public MenuRadioButton(String identifier, GUITextureProvider texprovider) {
		super(identifier, texprovider);
	}

	private void setUnchecked() {
		super.setChecked(false);
	}
	
	/**
	 * Ckeck/Uncheck the checkbox
	 * 
	 * @param checked the new state
	 */
	@Override
	public void setChecked(boolean checked) {
		if (checked) {
			super.setChecked(true);
			
			if (parent != null)
				for (MenuBaseElement element : parent.getDirectInnerElements()) {
					if (element != this && element instanceof MenuRadioButton) {
						((MenuRadioButton)element).setUnchecked();
					}
				}
		}
	}
	
	/**
	 * Adds a new listener
	 * 
	 * @param l the new listener
	 */
	public void addRadiobuttonListener(MenuRadioButtonListener l) {
		super.addElementListener(l);
	}

	@Override
	public MenuBaseElement getElementByID(String id) {
		return identifier.equals(id) ? this : null;
	}
}
