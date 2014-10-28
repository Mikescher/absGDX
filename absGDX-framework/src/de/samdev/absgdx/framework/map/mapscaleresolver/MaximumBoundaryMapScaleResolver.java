package de.samdev.absgdx.framework.map.mapscaleresolver;


public class MaximumBoundaryMapScaleResolver implements AbstractMapScaleResolver {

	private final int tilesX;
	private final int tilesY;
	
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
