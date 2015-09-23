package de.samdev.absgdx.framework.menu.attributes;

/** The focus state of an menu element */
public enum FocusState {
	/** element is focused */
	FOCUSED,
	/** element is not focused */
	UNFOCUSED;
	
	/**
	 * Convert an boolean value to an FocusState enum
	 * 
	 * @param input the boolean input
	 * @return FOCUSED if input else UNFOCUSED
	 */
	public static FocusState fromBoolean(boolean input) {
		return input ? FOCUSED : UNFOCUSED;
	}
}
