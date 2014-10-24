package de.samdev.absgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.map.Tile;

public class SkyTile extends Tile {
	private static TextureRegion tex = new TextureRegion(new Texture("badlogic.jpg"), 9*16, 9*16, 16, 16);
	
	public SkyTile() {
		super(tex);
	}
}
