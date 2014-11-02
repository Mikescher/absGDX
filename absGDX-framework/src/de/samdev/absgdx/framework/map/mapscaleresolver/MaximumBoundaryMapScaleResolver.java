package de.samdev.absgdx.framework.map.mapscaleresolver;

/**
 * This MapScaleResolver always shows a specific amount of tiles in X/Y direction.
 * 
 * Depending of the actual display size it can show more tiles - but the boundary is always on screen.
 */
public class MaximumBoundaryMapScaleResolver implements AbstractMapScaleResolver {

	private final int tilesX;
	private final int tilesY;
	
	/**
	 * Creates a new MaximumBoundaryMapScaleResolver
	 * 
	 * @param visibleTilesX the boundary width
	 * @param visibleTilesY the boundary height
	 */
	public MaximumBoundaryMapScaleResolver(int visibleTilesX, int visibleTilesY) {
		super();
		
		this.tilesX = visibleTilesX;
		this.tilesY = visibleTilesY;
	}

	@Override
	public float getTileSize(int screenWidth, int screenHeight, int mapWidth, int mapHeight) {
		float tx = Math.min(tilesX,  mapWidth);
		float ty = Math.min(tilesY, mapHeight);
		
		if ((screenWidth * 1f / screenHeight) < (tx / tilesY))
		{
			return screenWidth * 1f / tx;
		}
		else if ((screenWidth * 1f / screenHeight) > (tx / tilesY))
		{
			return screenHeight / ty;
		}
		else // equals
		{
			return screenHeight / ty;
		}
	}

}
