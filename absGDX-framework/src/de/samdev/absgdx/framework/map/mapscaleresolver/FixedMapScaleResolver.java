package de.samdev.absgdx.framework.map.mapscaleresolver;


/**
 * This MapScaleResolver always sets the tilesize to a fixed value
 *
 */
public class FixedMapScaleResolver implements AbstractMapScaleResolver {
	private final float fixedSize;
	
	/**
	 * Creates a new FixedMapScaleResolver
	 * 
	 * @param size The size of a single tile
	 */
	public FixedMapScaleResolver(float size) {
		super();
		
		this.fixedSize = size;
	}

	@Override
	public float getTileSize(int screenWidth, int screenHeight, int mapWidth, int mapHeight) {
		return fixedSize;
	}

}
