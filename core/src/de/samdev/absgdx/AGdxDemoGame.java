package de.samdev.absgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.samdev.absgdx.framework.AgdxGame;

public class AGdxDemoGame extends AgdxGame {	
	@Override
	public void onCreate() {
		Textures.init();
		
		pushLayer(new DemoGameLayer(this));
		
		setDebugFont(new BitmapFont(Gdx.files.internal("consolefont.fnt")));
	}
}
