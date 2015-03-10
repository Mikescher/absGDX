package de.samdev.absgdx.menudesigner;

import de.samdev.absgdx.framework.AgdxGame;

public class AgdxPreviewGameDummy extends AgdxGame {

	public int width = 900;
	public int height = 300;
	
	public AgdxPreviewGameDummy() { /** NOP */ }

	@Override
	public void onUpdate(float delta) { /** NOP */ }

	@Override
	public void onCreate() { /** NOP */ }

	@Override
	public int getScreenWidth() {
		return width;
	}

	@Override
	public int getScreenHeight() {
		return height;
	}
}
