package de.samdev.absgdx.framework.map.mapsizeresolver;

import com.badlogic.gdx.math.Vector2;

public class FixedScreenScaleResolver implements AbstractMapScaleResolver {

	
	public FixedScreenScaleResolver(float size) {
		super();
	}

	@Override
	public float getTileSize(int screenWidth, int screenHeight, int mapHeight, int mapWidth) {
		return 1; // TODO implement
	}

}
