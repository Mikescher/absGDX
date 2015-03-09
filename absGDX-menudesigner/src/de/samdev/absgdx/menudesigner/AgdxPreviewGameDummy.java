package de.samdev.absgdx.menudesigner;

import com.badlogic.gdx.Gdx;

import de.samdev.absgdx.framework.AgdxGame;

public class AgdxPreviewGameDummy extends AgdxGame {

	public int width = 900;
	public int height = 300;
	
	public AgdxPreviewGameDummy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onUpdate(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

	}

	public int getScreenWidth() {
		return width;
	}

	/**
	 * @return the height (in pixel) of the display
	 */
	public int getScreenHeight() {
		return height;
	}
}
