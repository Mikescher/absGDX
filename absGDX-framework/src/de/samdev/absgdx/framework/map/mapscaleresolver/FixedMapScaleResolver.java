package de.samdev.absgdx.framework.map.mapscaleresolver;


public class FixedMapScaleResolver implements AbstractMapScaleResolver {
	private final float fixedSize;
	
	public FixedMapScaleResolver(float size) {
		super();
		
		this.fixedSize = size;
	}

	@Override
	public float getTileSize(int screenWidth, int screenHeight, int mapWidth, int mapHeight) {
		return fixedSize;
	}

}
