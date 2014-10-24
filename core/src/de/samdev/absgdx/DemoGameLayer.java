package de.samdev.absgdx;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.mapsizeresolver.SectionMapScaleResolver;

public class DemoGameLayer extends GameLayer {

	public DemoGameLayer(AgdxGame owner) {
		super(owner);

		loadEmptyMap(20, 20);
		for (int x = 0; x < 20; x++) {
			for (int y = 0; y < 20; y++) {
//				map.setTile(x, y, new SkyTile());
			}
		}
		
		setMapScaleResolver(new SectionMapScaleResolver(12, 8, 0.5f, 20f));
	}

}
