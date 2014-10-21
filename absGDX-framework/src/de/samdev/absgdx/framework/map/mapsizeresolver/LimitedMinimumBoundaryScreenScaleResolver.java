package de.samdev.absgdx.framework.map.mapsizeresolver;

import com.badlogic.gdx.math.Vector2;

public class LimitedMinimumBoundaryScreenScaleResolver implements AbstractMapScaleResolver {

	private final int tilesX;
	private final int tilesY;
	
	public LimitedMinimumBoundaryScreenScaleResolver(int visibleTilesX, int visibleTilesY, float minimumSizePrecentage) {
		super();
		
		this.tilesX = visibleTilesX;
		this.tilesY = visibleTilesY;
	}

	@Override
	public float getTileSize(int screenWidth, int screenHeight, int mapHeight, int mapWidth) {
		return 1; // TODO implement
	}

}
