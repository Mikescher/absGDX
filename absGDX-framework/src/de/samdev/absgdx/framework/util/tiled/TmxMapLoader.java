package de.samdev.absgdx.framework.util.tiled;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.zip.DataFormatException;

import org.apache.commons.lang3.reflect.ConstructorUtils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

import de.samdev.absgdx.framework.map.EmptyTile;
import de.samdev.absgdx.framework.map.Tile;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.framework.util.exceptions.TmxMapParsingException;

/**
 * An Loader for tmx files, loads a tmx and gives a TileMap
 *
 */
public class TmxMapLoader extends TmxParser {

	private HashMap<Integer, Constructor<? extends Tile>> tileMapping = new HashMap<Integer, Constructor<? extends Tile>>();
	private HashMap<Integer, Constructor<? extends Tile>> extendedTileMapping = new HashMap<Integer, Constructor<? extends Tile>>();
	
	private TileMap map = null;
	
	/**
	 * Creates a new Parser based on the tmx file in the Filehandle
	 * 
	 * @param levelfile
	 */
	public TmxMapLoader(FileHandle levelfile) {
		super(levelfile.readString());
	}

	/**
	 * Creates a new Parser based on the xml content given by parameter
	 * 
	 * @param xmlContent
	 */
	public TmxMapLoader(String xmlContent) {
		super(xmlContent);
	}
	
	/**
	 * Adds a mapping between a GID and a Tile Class
	 * 
	 * The class must 
	 *  - be a subclass of "Tile"
	 *  - and have an empty constructor (0 parameters)
	 *  - and/or have an 1-parameter constructor for the properties ( 1 parameter = Hashmap<String, String> ) 
	 * 
	 * if the class has an constructor with the properties-map this one is used, 
	 * otherwise the empty constructor is used 
	 * 
	 * @param gid
	 * @param tileclass
	 */
	public void addMapping(Integer gid, Class<? extends Tile> tileclass) {
		HashMap<String, String> dummy = new HashMap<String, String>();

		Constructor<? extends Tile> constructor_empty = ConstructorUtils.getAccessibleConstructor(tileclass);
		Constructor<? extends Tile> constructor_single = ConstructorUtils.getAccessibleConstructor(tileclass, dummy.getClass());

		if (constructor_empty != null)
			tileMapping.put(gid, constructor_empty);
		if (constructor_single != null)
			extendedTileMapping.put(gid, constructor_single);
	}
	
	/**
	 * Adds a default mapping - this class is used when no other mapping fits
	 * 
	 * @param tileclass
	 */
	public void addDefaultMapping(Class<? extends Tile> tileclass) {
		addMapping(null, tileclass);
	}
	
	/**
	 * Starts the parsing and returns the created map
	 * 
	 * Throws TmxMapParsingException if the constructor is not found or an other parsing problem occured
	 * 
	 * @return the parsed TileMap
	 * @throws TmxMapParsingException
	 */
	public TileMap start() throws TmxMapParsingException {
		parse();
		
		return this.map;
	}
	
	@Override
	public void parse() throws TmxMapParsingException {
		try {
			this.map = null;
			
			super.parse();
		} catch (IOException e) {
			throw new TmxMapParsingException(e);
		} catch (DataFormatException e) {
			throw new TmxMapParsingException(e);
		} catch (GdxRuntimeException e) {
			throw new TmxMapParsingException(e);
		}
	}
	
	@Override
	protected void handleTile(int gid, int layer, int posX, int posY, HashMap<String, String> propertiesMap) throws TmxMapParsingException {
		if (this.map == null) {
			int w = Integer.parseInt(propertiesMap.get(PROPERTY_MAP_WIDTH));
			int h = Integer.parseInt(propertiesMap.get(PROPERTY_MAP_HEIGHT));
			
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
			} else if (extendedTileMapping.get(null) != null) {
				map.setTile(posX, posY, extendedTileMapping.get(null).newInstance(propertiesMap));
			} else if (tileMapping.get(null) != null) {
				map.setTile(posX, posY, tileMapping.get(null).newInstance());
			} else {
				throw new TmxMapParsingException("No suitable constructor for GID:" + gid + " found");
			}
		} catch (InstantiationException e) {
			throw new TmxMapParsingException(e);
		} catch (IllegalAccessException e) {
			throw new TmxMapParsingException(e);
		} catch (IllegalArgumentException e) {
			throw new TmxMapParsingException(e);
		} catch (InvocationTargetException e) {
			throw new TmxMapParsingException(e);
		}
	}

}
