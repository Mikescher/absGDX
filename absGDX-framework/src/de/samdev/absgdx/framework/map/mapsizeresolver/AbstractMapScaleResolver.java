package de.samdev.absgdx.framework.map.mapsizeresolver;

import com.badlogic.gdx.math.Vector2;

public interface AbstractMapScaleResolver {

	public abstract Vector2 getTileSize(int screenWidth, int screenHeight, int mapHeight, int mapWidth);

}
