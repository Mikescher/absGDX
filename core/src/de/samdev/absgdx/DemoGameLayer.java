package de.samdev.absgdx;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.GameLayer;

public class DemoGameLayer extends GameLayer {

	public DemoGameLayer(AgdxGame owner) {
		super(owner);

		loadEmptyMap(20, 20);
	}

}
