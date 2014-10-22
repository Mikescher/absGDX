package de.samdev.absgdx;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.mapsizeresolver.LimitedMinimumBoundaryScreenScaleResolver;

public class DemoGameLayer extends GameLayer {

	public DemoGameLayer(AgdxGame owner) {
		super(owner);

		loadEmptyMap(20, 20);
		
		setMapScaleResolver(new LimitedMinimumBoundaryScreenScaleResolver(map.width, map.height, 0.5f));
	}

}
