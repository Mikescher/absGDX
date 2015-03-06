package de.samdev.absgdx.framework.menu.elements;

import de.samdev.absgdx.framework.menu.GUITextureProvider;

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
			
			if (owner != null)
				for (MenuElement element : owner.getChildren()) {
					if (element != this && element instanceof MenuRadioButton) {
						((MenuRadioButton)element).setUnchecked();
					}
				}
		}
	}
}
