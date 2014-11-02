package de.samdev.absgdx.framework.map.mapscaleresolver;

/**
 * This MapScaleResolver is an extension to the MinimumBoundaryMapScaleResolver.
 * 
 * If the more than x % of the minimum boundary box is not shown (= is cut away) the boundary box can be exceeded
 *
 * The maximumCut serves as the lower limit
 */
public class LimitedMinimumBoundaryMapScaleResolver extends MinimumBoundaryMapScaleResolver {

	private final float maximumCut;
	
	/**
	 * Creates a new LimitedMinimumBoundaryMapScaleResolver
	 * 
	 * @param visibleTilesX the width of the boundary box
	 * @param visibleTilesY the height of the boundary box
	 * @param maximumCutPrecentage the maximum amount of "cut away" tiles
	 */
	public LimitedMinimumBoundaryMapScaleResolver(int visibleTilesX, int visibleTilesY, float maximumCutPrecentage) {
		super(visibleTilesX, visibleTilesY);
		
		this.maximumCut = maximumCutPrecentage;
	}

	@Override
	public float getTileSize(int screenWidth, int screenHeight, int mapWidth, int mapHeight) {
		float tx = Math.min(tilesX, mapWidth);
		float ty = Math.min(tilesY, mapHeight);
		
		float scale = super.getTileSize(screenWidth, screenHeight, mapWidth, mapHeight);
		float cut = 1 - ((screenWidth * screenHeight) / (tx * scale * ty * scale));
		
		if (cut > maximumCut)
		{
			if ((screenWidth * 1f / screenHeight) < (tx / ty)) {
				scale = ((screenWidth * ty) / (1 - maximumCut)) / (tx * ty);
			}
			else if ((screenWidth * 1f / screenHeight) > (tx / ty)) {
				scale = ((tx * screenHeight) / (1 - maximumCut)) / (tx * ty);
			}
		}
		
		return scale;
	}

}
