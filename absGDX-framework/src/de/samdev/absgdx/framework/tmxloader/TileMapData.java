package de.samdev.absgdx.framework.tmxloader;

import java.util.ArrayList;
import java.util.HashMap;

public class TileMapData {
	
	public static final long GID_MASK = 0x3fffffff;
	
	// Map data fields 
	
	public String name;
	public int height, width;
	public int tilewidth, tileheight;
	public String orientation;
	
	public ArrayList<TileSet> tilesets;
	public ArrayList<TMXObject> objects;
	public ArrayList<Layer> layers;

	static class TileSet{
		public String name;
		
		public int firstGID;
		public int tileWidth, tileHeight;
		
		public String ImageFilename;
		public int imageWidth, imageHeight;
		
		// HashMap<GID, Properties<String, String>>
		public HashMap<String, HashMap<String, String>> properties;		
	}
	
	static class Layer{
		/*
		  Holds the tile placement data for a layer of the tilemap
		 */
		
		public String name;
		
		public long[][] tiles;
		public int width, height;
		public double opacity;
		
		HashMap<String, String> properties;
		
	}
	
	static class TMXObject{
		String name;
		String type;
		int x, y;
		int width, height;
		
		String objectGroup;
	}
	
	public long getGIDAt(int x, int y){
		return ((layers.get(0).tiles[x][y]) & GID_MASK);
	}
	
	public long getGIDAt(int x, int y, int layerIndex)
	{
		return((layers.get(layerIndex).tiles[y][x]) & GID_MASK);
		
	}
	
	public Long getLocalID(long GID){
		long currentFirstGID;
		for(int i = tilesets.size() - 1; i >= 0; i--){
			currentFirstGID = tilesets.get(i).firstGID;
			if(currentFirstGID <= GID) return new Long(GID - currentFirstGID);
		}
		return null;
		
	}
	
	public Integer getTileSetIndex(long GID){
		
		long currentFirstGID;
		for(int i = tilesets.size() - 1; i >= 0; i--){
			currentFirstGID = tilesets.get(i).firstGID;
			if(currentFirstGID <= GID) return new Integer(i);
		}
		return null;
		
	}
	
	public Integer getTileSetIndex(String name){
		// get the index of a tileset by name
		
		for(int i = tilesets.size() -1; i >= 0; i--){
			if(name.equals(tilesets.get(i).name)) return new Integer(i);
		}
		
		return null;
	}
	
	public Integer getLayerIndex(String name){
		// get the index of a layer by name
		for(int i = 0; i < layers.size(); i++){
			if(layers.get(i).name.equals(name)) return new Integer(i);
		}
		return null;
	}
	
	
	public TileMapData(){
		tilesets = new ArrayList<TileSet>();
		objects = new ArrayList<TMXObject>();
		layers = new ArrayList<Layer>();
	}
	
}
