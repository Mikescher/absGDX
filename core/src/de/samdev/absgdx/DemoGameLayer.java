package de.samdev.absgdx;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.mapsizeresolver.SectionMapScaleResolver;

public class DemoGameLayer extends GameLayer {

	public DemoGameLayer(AgdxGame owner) {
		super(owner);

		loadEmptyMap(20, 20);
		
		setMapScaleResolver(new SectionMapScaleResolver(map.width, map.height, 0.5f, 20f));
	}

}
