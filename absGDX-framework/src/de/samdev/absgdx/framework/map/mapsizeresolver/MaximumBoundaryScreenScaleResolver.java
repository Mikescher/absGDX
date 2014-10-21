package de.samdev.absgdx.framework.map.mapsizeresolver;


public class MaximumBoundaryScreenScaleResolver implements AbstractMapScaleResolver {

	private final int tilesX;
	private final int tilesY;
	
	public MaximumBoundaryScreenScaleResolver(int visibleTilesX, int visibleTilesY) {
		super();
		
		this.tilesX = visibleTilesX;
		this.tilesY = visibleTilesY;
	}

	@Override
	public float getTileSize(int screenWidth, int screenHeight, int mapHeight, int mapWidth) {
		float tx = Math.min(tilesX,  mapWidth);
		float ty = Math.min(tilesY, mapHeight);
		
		if ((screenWidth * 1f / screenHeight) < (tilesY / tx))
		{
			return screenWidth * 1f / tx;
		}
		else if ((screenWidth * 1f / screenHeight) > (tilesY / tx))
		{
			return screenHeight / ty;
		}
		else // equals
		{
			return screenHeight / ty;
		}
	}

}
