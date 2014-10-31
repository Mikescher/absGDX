package de.samdev.absgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.map.StaticTile;

public class GroundTile_BR extends StaticTile {
	private static TextureRegion tex = Textures.tex_GroundTile_BR;
	
	public GroundTile_BR() {
		super(tex);
	}
}
