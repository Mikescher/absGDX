package de.samdev.absgdx.framework.menu.attributes;

/**
 * Describes how the texture of an MenuImage shall be displayed
 *
 */
public enum ImageBehavior {
	/** DOnt stretch, size as big as possible */
	FIT,
	/** leave original texture size */
	NOSCALE,
	/** stretch to image size*/
	STRETCH,
}
