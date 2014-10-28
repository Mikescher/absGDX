package de.samdev.absgdx.framework.map.mapscaleresolver;

public class SectionMapScaleResolver extends LimitedMinimumBoundaryMapScaleResolver {

	private final float minimumTileSize;
	
	public SectionMapScaleResolver(int visibleTilesX, int visibleTilesY, float maximumCutPrecentage, float minimumTileSize) {
		super(visibleTilesX, visibleTilesY, maximumCutPrecentage);
		
		this.minimumTileSize = minimumTileSize;
	}

	@Override
	public float getTileSize(int screenWidth, int screenHeight, int mapWidth, int mapHeight) {
		float scale = super.getTileSize(screenWidth, screenHeight, mapWidth, mapHeight);
		
		return Math.max(scale, minimumTileSize);
	}

}
