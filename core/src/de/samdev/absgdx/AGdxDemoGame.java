package de.samdev.absgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.sidescrollergame.SidescrollerGameLayer;
import de.samdev.absgdx.topdowngame.TopDownGameLayer;

public class AGdxDemoGame extends AgdxGame {	
	@Override
	public void onCreate() {
		Textures.init();
		
		pushLayer(new TopDownGameLayer(this));
		pushLayer(new SidescrollerGameLayer(this));
		
		setDebugFont(new BitmapFont(Gdx.files.internal("consolefont.fnt")));
	}

	@Override
	public void onUpdate(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.F1)) settings.debugEnabled.doSwitch();
		if (Gdx.input.isKeyJustPressed(Keys.F2)) settings.debugVisualEntities.doSwitch();
		if (Gdx.input.isKeyJustPressed(Keys.F3)) settings.debugVisualMap.doSwitch();
		if (Gdx.input.isKeyJustPressed(Keys.F4)) settings.debugTextInfos.doSwitch();
		if (Gdx.input.isKeyJustPressed(Keys.F5)) settings.debugEntitiesPhysicVectors.doSwitch();
	}
}
