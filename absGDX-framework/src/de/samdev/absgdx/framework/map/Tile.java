package de.samdev.absgdx.framework.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Tile {

	private final TextureRegion texture;
	
	public Tile(TextureRegion region) {
		super();
		
		this.texture = region;
	}
	
	public Tile(Texture tex) {
		super();
		
		this.texture = new TextureRegion(tex);
	}

	public TextureRegion getTexture() {
		return texture;
	}
	
	public abstract void update(float delta);
}
