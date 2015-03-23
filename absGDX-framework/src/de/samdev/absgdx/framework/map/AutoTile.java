package de.samdev.absgdx.framework.map;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
		super(getAutoTexture(Integer.parseInt(properties.get(TmxMapLoader.PROPERTY_TEXTURE_GID)), tileset, tilewidth, tileheight, 0, 0, 0, 0));
		
		this.properties = properties;
	}
	
	/**
	 * Creates a new AutoTile
	 * 
	 * @param tileset the tileset
	 * @param tilewidth the width of a single tile (in the tileset, in pixels)
	 * @param tileheight the height of a single tile (in the tileset, in pixels)
	 * @param offsetX The left offset of the tileset in the file
	 * @param offsetY The top offset of the tileset in the file
	 * @param gapX the gap between the tiles in X direction
	 * @param gapY the gap between the tiles in Y direction
	 * @param properties the properties map - must contain the GID
	 */
	public AutoTile(Texture tileset, int tilewidth, int tileheight, int offsetX, int offsetY, int gapX, int gapY, HashMap<String, String> properties) {
		super(getAutoTexture(Integer.parseInt(properties.get(TmxMapLoader.PROPERTY_TEXTURE_GID)), tileset, tilewidth, tileheight, offsetX, offsetY, gapX, gapY));
		
		this.properties = properties;
	}

	private static TextureRegion getAutoTexture(int gid, Texture tileset, int tileW, int tileH, int offsetX, int offsetY, int gapX, int gapY) {
		int tilesetwidth = tileset.getWidth() / tileW;
		
		int px = ((gid-1) % tilesetwidth) * (tileW + gapX) + offsetX;
		int py = ((gid-1) / tilesetwidth) * (tileH + gapY) + offsetY;
		
		return new TextureRegion(tileset, px, py, tileW, tileH);
	}
}
