package de.samdev.absgdx;

import de.samdev.absgdx.framework.AgdxGame;

public class AGdxDemoGame extends AgdxGame {	
	@Override
	public void onCreate() {
		Textures.init();
		
		pushLayer(new DemoGameLayer(this));
	}
}
