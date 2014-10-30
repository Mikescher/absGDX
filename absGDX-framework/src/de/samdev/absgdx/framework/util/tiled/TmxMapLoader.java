package de.samdev.absgdx.framework.util.tiled;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.zip.DataFormatException;

import org.apache.commons.lang3.reflect.ConstructorUtils;

import nu.xom.ParsingException;

import com.badlogic.gdx.files.FileHandle;

import de.samdev.absgdx.framework.map.EmptyTile;
import de.samdev.absgdx.framework.map.Tile;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.framework.util.exceptions.ConstructorNotFoundException;

public class TmxMapLoader extends TmxParser {

	private HashMap<Integer, Constructor<? extends Tile>> tileMapping = new HashMap<Integer, Constructor<? extends Tile>>();
	private HashMap<Integer, Constructor<? extends Tile>> extendedTileMapping = new HashMap<Integer, Constructor<? extends Tile>>();
	
	private TileMap map = null;
	
	public TmxMapLoader(FileHandle levelfile) {
		super(levelfile.readString());
	}

	public TmxMapLoader(String xmlContent) {
		super(xmlContent);
	}
	
	public void addMapping(int gid, Class<? extends Tile> tileclass) {
		HashMap<String, String> dummy = new HashMap<String, String>();

		Constructor<? extends Tile> constructor_empty = ConstructorUtils.getAccessibleConstructor(tileclass);
		Constructor<? extends Tile> constructor_single = ConstructorUtils.getAccessibleConstructor(tileclass, dummy.getClass());

		if (constructor_empty != null)
			tileMapping.put(gid, constructor_empty);
		if (constructor_single != null)
			extendedTileMapping.put(gid, constructor_single);
	}
	
	public TileMap start() throws ConstructorNotFoundException {
		parse();
		
		return this.map;
	}
	
	@Override
	public void parse() throws ConstructorNotFoundException {
		try {
			this.map = null;
			
			super.parse();
		} catch (ParsingException e) {
			this.map = null;
		} catch (IOException e) {
			this.map = null;
		} catch (DataFormatException e) {
			this.map = null;
		}
	}
	
	@Override
	protected void handleTile(int gid, int layer, int posX, int posY, HashMap<String, String> propertiesMap) throws ConstructorNotFoundException {
		if (this.map == null) {
			int w = Integer.parseInt(propertiesMap.get("[absGDX]-map_width"));
			int h = Integer.parseInt(propertiesMap.get("[absGDX]-map_height"));
			
			map = new TileMap(w, h);
			
			for (int x = 0; x < w; x++) {
				for (int y = 0; y < h; y++) {
					map.setTile(x, y, new EmptyTile());
				}
			}
		}
		
		try {
			if (extendedTileMapping.get(gid) != null) {
				map.setTile(posX, posY, extendedTileMapping.get(gid).newInstance(propertiesMap));
			} else if (tileMapping.get(gid) != null) {
				map.setTile(posX, posY, tileMapping.get(gid).newInstance());
			} else {
				throw new ConstructorNotFoundException("No suitable constructor for GID:" + gid + " found");
			}
		} catch (InstantiationException e) {
			throw new ConstructorNotFoundException(e);
		} catch (IllegalAccessException e) {
			throw new ConstructorNotFoundException(e);
		} catch (IllegalArgumentException e) {
			throw new ConstructorNotFoundException(e);
		} catch (InvocationTargetException e) {
			throw new ConstructorNotFoundException(e);
		}
	}

}
