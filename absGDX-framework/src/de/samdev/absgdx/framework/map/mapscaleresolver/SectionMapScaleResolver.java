package de.samdev.absgdx.framework.map.mapscaleresolver;

/**
 * This MapScaleResolver is an extension to the LimitedMinimumBoundaryMapScaleResolver.
 * 
 * Additional to the maximum cut amount (from LimitedMinimumBoundaryMapScaleResolver) this becomes a minimum tile size
 * 
 * This is a MinimumMapScaleResolver with an additional upper and lower limit for the tile size
 */
public class SectionMapScaleResolver extends LimitedMinimumBoundaryMapScaleResolver {

	private final float minimumTileSize;
	
	/**
	 * Creates a new SectionMapScaleResolver
	 * 
	 * @param visibleTilesX the width of the boundary box
	 * @param visibleTilesY the height of the boundary box
	 * @param maximumCutPrecentage the maximum amount of "cut away" tiles
	 * @param minimumTileSize the minimum size for the tiles
	 */
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
