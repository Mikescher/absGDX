package de.samdev.absgdx.framework.map;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.util.tiled.TmxMapLoader;

/**
 * This tile gets its texture automatically from a tileset combined with the GID (from a TMX file)
 * 
 * I gets the GID from the properties map and calculates the tileset position back from it.
 * 
 * This class is mostly useful in combination with the TmxMapLoader
 */
public abstract class AutoTile extends Tile {
	
	/**
	 * The propertiesMap this Tile was initialized with
	 */
	public final HashMap<String, String> properties;
	
	/**
	 * Creates a new AutoTile
	 * 
	 * @param tileset the tileset
	 * @param tilewidth the width of a single tile (in the tileset, in pixels)
	 * @param tileheight the height of a single tile (in the tileset, in pixels)
	 * @param properties the properties map - must contain the GID
	 */
	public AutoTile(Texture tileset, int tilewidth, int tileheight, HashMap<String, String> properties) {
		super(getAutoTexture(Integer.parseInt(properties.get(TmxMapLoader.PROPERTY_TEXTURE_GID)), tileset, tilewidth, tileheight));
		
		this.properties = properties;
	}

	private static TextureRegion getAutoTexture(int gid, Texture tileset, int tileW, int tileH) {
		int tilesetwidth = tileset.getWidth() / tileW;
		
		return new TextureRegion(tileset, ((gid-1) % tilesetwidth) * tileW, ((gid-1) / tilesetwidth) * tileH, tileW, tileH);
	}

	@Override
	public void update(float delta) {
		// NOP
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return false;
	}
}
