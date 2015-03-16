package de.samdev.absgdx.framework.map.mapscaleresolver;

/**
 * This MapScaleResolver always shows the complete map.
 * 
 * Depending on the display size this can result in unused space, there you will only see the clear-color
 */
public class ShowCompleteMapScaleResolver implements AbstractMapScaleResolver {
	
	/**
	 * Creates a new ShowCompleteMapScaleResolver
	 */
	public ShowCompleteMapScaleResolver() {
		// NOP
	}

	@Override
	public float getTileSize(int screenWidth, int screenHeight, int mapWidth, int mapHeight) {
		AbstractMapScaleResolver resolver = new MaximumBoundaryMapScaleResolver(mapWidth, mapHeight);
		
		return resolver.getTileSize(screenWidth, screenHeight, mapWidth, mapHeight);
	}
}
