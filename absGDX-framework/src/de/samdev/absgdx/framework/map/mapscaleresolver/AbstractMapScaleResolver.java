package de.samdev.absgdx.framework.map.mapscaleresolver;


public interface AbstractMapScaleResolver {

	public abstract float getTileSize(int screenWidth, int screenHeight, int mapWidth, int mapHeight);

}
