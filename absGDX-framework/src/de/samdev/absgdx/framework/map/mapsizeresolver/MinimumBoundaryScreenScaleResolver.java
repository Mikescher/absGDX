package de.samdev.absgdx.framework.map.mapsizeresolver;

import com.badlogic.gdx.math.Vector2;

public class MinimumBoundaryScreenScaleResolver implements AbstractMapScaleResolver {

	private final int tilesX;
	private final int tilesY;
	
	public MinimumBoundaryScreenScaleResolver(int visibleTilesX, int visibleTilesY) {
		super();
		
		this.tilesX = visibleTilesX;
		this.tilesY = visibleTilesY;
	}

	@Override
	public float getTileSize(int screenWidth, int screenHeight, int mapHeight, int mapWidth) {
		return 1; // TODO implement
	}

}
