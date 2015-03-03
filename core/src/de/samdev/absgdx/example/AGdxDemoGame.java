package de.samdev.absgdx.example;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.samdev.absgdx.example.mainmenu.ManualMenu;
import de.samdev.absgdx.example.sidescrollergame.SidescrollerGameLayer;
import de.samdev.absgdx.example.topdowngame.TopDownGameLayer;
import de.samdev.absgdx.framework.AgdxGame;

public class AGdxDemoGame extends AgdxGame {	
	@Override
	public void onCreate() {
		Textures.init();
		
		pushLayer(new TopDownGameLayer(this));
		pushLayer(new SidescrollerGameLayer(this));
		pushLayer(new ManualMenu(this));
		
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
