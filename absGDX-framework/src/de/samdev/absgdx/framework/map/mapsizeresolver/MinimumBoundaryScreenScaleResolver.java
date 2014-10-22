package de.samdev.absgdx.framework.map.mapsizeresolver;

import com.badlogic.gdx.math.Vector2;

public class MinimumBoundaryScreenScaleResolver implements AbstractMapScaleResolver {

	protected final int tilesX;
	protected final int tilesY;
	
	public MinimumBoundaryScreenScaleResolver(int visibleTilesX, int visibleTilesY) {
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
