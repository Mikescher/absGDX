package de.samdev.absgdx.tests;

import de.samdev.absgdx.framework.AgdxGame;

public class DummyAgdxGame extends AgdxGame {
	private final int scrn_w;
	private final int scrn_h;
	
	public DummyAgdxGame(int screenwidth, int screenheight) {
		super();
		
		this.scrn_w = screenwidth;
		this.scrn_h = screenheight;
	}

	@Override
	public void onCreate() {
		// NOTHING
	}

	@Override
	public int getScreenWidth() {
		return scrn_w;
	}

	@Override
	public int getScreenHeight() {
		return scrn_h;
	}

}
