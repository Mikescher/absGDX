package de.samdev.absgdx.tests;

import de.samdev.absgdx.framework.layer.GameLayer;

public class DummyGameLayer extends GameLayer {

	public DummyGameLayer(int screenwidth, int screenheight) {
		super(new DummyAgdxGame(screenwidth, screenheight));
	}

	@Override
	public void onUpdate() {
		// NOP
	}

}
