package de.samdev.absgdx.framework.map;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.util.tiled.TmxMapLoader;

/**
 * A simple Tile with no update method
 */
public abstract class AutoTile extends Tile {
	public AutoTile(Texture tileset, int tilewidth, int tileheight, HashMap<String, String> properties) {
		super(getAutoTexture(Integer.parseInt(properties.get(TmxMapLoader.PROPERTY_TEXTURE_GID)), tileset, tilewidth, tileheight));
	}

	public static TextureRegion getAutoTexture(int gid, Texture tileset, int tileW, int tileH) {
		int tilesetwidth = tileset.getWidth() / tileW;
		
		return new TextureRegion(tileset, ((gid-1) % tilesetwidth) * tileW, ((gid-1) / tilesetwidth) * tileH, tileW, tileH);
	}

	@Override
	public void update(float delta) {
		// NOP
	}
}
