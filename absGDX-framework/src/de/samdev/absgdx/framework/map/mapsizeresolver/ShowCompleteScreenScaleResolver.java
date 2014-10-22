package de.samdev.absgdx.framework.map.mapsizeresolver;


public class ShowCompleteScreenScaleResolver implements AbstractMapScaleResolver {
	
	public ShowCompleteScreenScaleResolver() {
		// NOP
	}

	@Override
	public float getTileSize(int screenWidth, int screenHeight, int mapWidth, int mapHeight) {
		AbstractMapScaleResolver resolver = new MaximumBoundaryScreenScaleResolver(mapWidth, mapHeight);
		
		return resolver.getTileSize(screenWidth, screenHeight, mapWidth, mapHeight);
	}
}
