package de.samdev.absgdx.framework.menu.attributes;

/** The checked state of an menu element (checkbox, radiobutton, ...) */
public enum CheckState {
	/** element is checked */
	CHECKED,
	/** element is not checked */
	UNCHECKED;
	
	/**
	 * Convert an boolean value to an CheckState enum
	 * 
	 * @param input the boolean input
	 * @return CHECKED if input else UNCHECKED
	 */
	public static CheckState fromBoolean(boolean input) {
		return input ? CHECKED : UNCHECKED;
	}
}
