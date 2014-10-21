package de.samdev.absgdx;

import de.samdev.absgdx.framework.AgdxGame;

public class AGdxDemoGame extends AgdxGame {
	
	@Override
	public void onCreate() {
		pushLayer(new DemoGameLayer(this));
	}
}
