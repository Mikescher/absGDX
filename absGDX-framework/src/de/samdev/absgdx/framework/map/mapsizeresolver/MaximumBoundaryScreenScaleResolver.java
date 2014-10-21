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
		if (screenWidth / screenHeight < tilesY / tilesX)
		{
			return new Vector2(screenHeight / tilesY, screenHeight / tilesY);
		}
		else if (screenWidth / screenHeight > tilesY / tilesX)
		{
			return new Vector2(screenWidth / tilesX, screenWidth / tilesX);
		}
		else // equals
		{
			return new Vector2(screenWidth / tilesX, screenHeight / tilesY);
		}
	}

}
