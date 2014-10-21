package de.samdev.absgdx.framework.map.mapsizeresolver;

import com.badlogic.gdx.math.Vector2;

public class MaximumBoundaryScreenScaleResolver implements AbstractMapScaleResolver {

	private final int tilesX;
	private final int tilesY;
	
	public MaximumBoundaryScreenScaleResolver(int visibleTilesX, int visibleTilesY) {
		super();
		
		this.tilesX = visibleTilesX;
		this.tilesY = visibleTilesY;
	}

	@Override
	public Vector2 getTileSize(int screenWidth, int screenHeight, int mapHeight, int mapWidth) {
		if ((screenWidth * 1f / screenHeight) < (tilesY * 1f / tilesX))
		{
			return new Vector2(screenWidth * 1f / tilesX, screenWidth * 1f / tilesX);
		}
		else if ((screenWidth * 1f / screenHeight) > (tilesY * 1f / tilesX))
		{
			return new Vector2(screenHeight * 1f / tilesY, screenHeight * 1f / tilesY);
		}
		else // equals
		{
			return new Vector2(screenWidth * 1f / tilesX, screenHeight * 1f / tilesY);
		}
	}

}
