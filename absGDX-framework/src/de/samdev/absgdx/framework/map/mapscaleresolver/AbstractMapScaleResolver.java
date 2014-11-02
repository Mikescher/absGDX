package de.samdev.absgdx.framework.map.mapscaleresolver;


/**
 * The abstract Superclass for all MapScaleResolvers
 * 
 * A Map scale Resolver determines the scale of the game (depending on the display size)
 *
 */
public interface AbstractMapScaleResolver {

	/**
	 * @param screenWidth The width of the screen
	 * @param screenHeight The height of the screen
	 * @param mapWidth the map width (in tiles)
	 * @param mapHeight the map height (in tiles)
	 * @return the size of a single tile (width == height)
	 */
	public abstract float getTileSize(int screenWidth, int screenHeight, int mapWidth, int mapHeight);

}
