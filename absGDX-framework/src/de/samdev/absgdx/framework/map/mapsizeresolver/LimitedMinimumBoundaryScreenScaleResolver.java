package de.samdev.absgdx.framework.map.mapsizeresolver;

public class LimitedMinimumBoundaryScreenScaleResolver implements AbstractMapScaleResolver {

	private final int tilesX;
	private final int tilesY;
	
	/**
	 * @param visibleTilesX the (optimal / minimal) amount of  
	 * @param visibleTilesY
	 * @param maximumCutPrecentage
	 */
	public LimitedMinimumBoundaryScreenScaleResolver(int visibleTilesX, int visibleTilesY, float maximumCutPrecentage) {
		super();
		
		this.tilesX = visibleTilesX;
		this.tilesY = visibleTilesY;
	}

	@Override
	public float getTileSize(int screenWidth, int screenHeight, int mapWidth, int mapHeight) {
		return 1; // TODO implement
	}

}
