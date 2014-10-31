package de.samdev.absgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.map.StaticTile;

public class SkyTile extends StaticTile {
	private static TextureRegion tex = Textures.tex_dirt;
	
	public SkyTile() {
		super(tex);
	}
}
