package de.samdev.absgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.map.AnimationTile;

public class GroundTile_BL extends AnimationTile {
	private static TextureRegion[] tex = {Textures.tex_GroundTile_BL, Textures.tex_GroundTile_BR, Textures.tex_GroundTile_TL, Textures.tex_GroundTile_TR};
	
	public GroundTile_BL() {
		super(tex, 1000);
	}
}
