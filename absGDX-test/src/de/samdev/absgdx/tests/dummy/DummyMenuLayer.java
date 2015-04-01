package de.samdev.absgdx.tests.dummy;

import de.samdev.absgdx.framework.layer.MenuLayer;

public class DummyMenuLayer extends MenuLayer {

	public DummyMenuLayer(int screenwidth, int screenheight) {
		super(new DummyAgdxGame(screenwidth, screenheight), null);
	}

	@Override
	public void onResize() {
		// NOP
	}

}
