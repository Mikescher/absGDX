package de.samdev.absgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.map.Tile;

public class GroundTile_BL extends Tile {
	private static TextureRegion tex = Textures.tex_GroundTile_BL;
	
	public GroundTile_BL() {
		super(tex);
	}
}
