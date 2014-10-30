package de.samdev.absgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.map.Tile;

public class GroundTile_BR extends Tile {
	private static TextureRegion tex = Textures.tex_GroundTile_BR;
	
	public GroundTile_BR() {
		super(tex);
	}
}
