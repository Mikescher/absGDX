package de.samdev.absgdx.framework.map.mapsizeresolver;


public class ShowCompleteScreenScaleResolver implements AbstractMapScaleResolver {
	
	public ShowCompleteScreenScaleResolver() {
		// NOP
	}

	@Override
	public float getTileSize(int screenWidth, int screenHeight, int mapHeight, int mapWidth) {
		MaximumBoundaryScreenScaleResolver resolver = new MaximumBoundaryScreenScaleResolver(mapHeight, mapWidth);
		
		return resolver.getTileSize(screenWidth, screenHeight, mapHeight, mapWidth);
	}
}
