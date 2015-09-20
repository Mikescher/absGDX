package de.samdev.absgdx.example;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.samdev.absgdx.example.mainmenu.ManualMenu;
import de.samdev.absgdx.framework.AgdxGame;

public class AGdxDemoGame extends AgdxGame {	
	@Override
	public void onCreate() {
		Textures.init();
		
		pushLayer(new ManualMenu(this));
		
		setDebugFont(new BitmapFont(Gdx.files.internal("consolefont.fnt")));
	}

	@Override
	public void onUpdate(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.F1) || Gdx.input.isKeyJustPressed(Keys.MENU)) settings.debugEnabled.doSwitch();
		if (Gdx.input.isKeyJustPressed(Keys.F2)) settings.debugVisualEntities.doSwitch();
		if (Gdx.input.isKeyJustPressed(Keys.F3)) settings.debugVisualMap.doSwitch();
		if (Gdx.input.isKeyJustPressed(Keys.F4)) settings.debugVisualMenu.doSwitch();
		if (Gdx.input.isKeyJustPressed(Keys.F5)) settings.debugTextInfos.doSwitch();
		if (Gdx.input.isKeyJustPressed(Keys.F6)) settings.debugEntitiesPhysicVectors.doSwitch();
	}
}
