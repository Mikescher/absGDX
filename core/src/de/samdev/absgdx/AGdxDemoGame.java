package de.samdev.absgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import de.samdev.absgdx.framework.AgdxGame;

public class AGdxDemoGame extends AgdxGame {
	
	@Override
	public void onCreate() {
		pushLayer(new DemoGameLayer(this));
		
	}
}
