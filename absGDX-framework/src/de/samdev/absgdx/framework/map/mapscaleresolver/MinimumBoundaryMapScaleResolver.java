package de.samdev.absgdx.framework.map.mapscaleresolver;

/**
 * This MapScaleResolver never shows more than a specific amount of tiles in X/Y direction
 * 
 * Depending of the actual display size it can show less tiles - but the boundary is never exceeded
 */
public class MinimumBoundaryMapScaleResolver implements AbstractMapScaleResolver {

	protected final int tilesX;
	protected final int tilesY;
	
	/**
	 * Creates a new MinimumBoundaryMapScaleResolver
	 * 
	 * @param visibleTilesX the width of the boundary box
	 * @param visibleTilesY the height of the boundary box
	 */
	public MinimumBoundaryMapScaleResolver(int visibleTilesX, int visibleTilesY) {
		super();
		
		this.tilesX = visibleTilesX;
		this.tilesY = visibleTilesY;
	}

	@Override
	public float getTileSize(int screenWidth, int screenHeight, int mapWidth, int mapHeight) {
		float tx = Math.min(tilesX, mapWidth);
		float ty = Math.min(tilesY, mapHeight);
		
		if ((screenWidth * 1f / screenHeight) < (tx / ty))
		{
			return screenHeight / ty;
		}
		else if ((screenWidth * 1f / screenHeight) > (tx / ty))
		{
			return screenWidth * 1f / tx;
		}
		else // equals
		{
			return screenHeight / ty;
		}
	}

}
