package de.samdev.absgdx.framework.map.mapsizeresolver;


public class FixedScreenScaleResolver implements AbstractMapScaleResolver {
	private final float fixedSize;
	
	public FixedScreenScaleResolver(float size) {
		super();
		
		this.fixedSize = size;
	}

	@Override
	public float getTileSize(int screenWidth, int screenHeight, int mapHeight, int mapWidth) {
		return fixedSize;
	}

}
