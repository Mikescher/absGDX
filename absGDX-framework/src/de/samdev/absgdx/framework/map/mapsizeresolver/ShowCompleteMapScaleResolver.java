package de.samdev.absgdx.framework.map.mapsizeresolver;


public class ShowCompleteMapScaleResolver implements AbstractMapScaleResolver {
	
	public ShowCompleteMapScaleResolver() {
		// NOP
	}

	@Override
	public float getTileSize(int screenWidth, int screenHeight, int mapWidth, int mapHeight) {
		AbstractMapScaleResolver resolver = new MaximumBoundaryMapScaleResolver(mapWidth, mapHeight);
		
		return resolver.getTileSize(screenWidth, screenHeight, mapWidth, mapHeight);
	}
}
