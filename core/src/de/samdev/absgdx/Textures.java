package de.samdev.absgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class Textures {

	public static Texture texmap;
	
	public static TextureRegion tex_dirt;

	public static void init() {
		texmap = new Texture("map.png");
		
		tex_dirt = new TextureRegion(texmap, 9*16, 9*16, 16, 16);
	}
}
