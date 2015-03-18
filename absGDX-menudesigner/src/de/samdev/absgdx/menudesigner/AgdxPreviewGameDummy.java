package de.samdev.absgdx.menudesigner;

import de.samdev.absgdx.framework.AgdxGame;

public class AgdxPreviewGameDummy extends AgdxGame {

	public int width = -1;
	public int height = -1;
	
	public AgdxPreviewGameDummy() { /** NOP */ }
	
	public AgdxPreviewGameDummy(int w, int h) { width = w; height = h;}

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
