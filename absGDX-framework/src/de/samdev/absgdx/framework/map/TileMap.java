package de.samdev.absgdx.framework.map;

import de.samdev.absgdx.framework.AgdxGame;

public class TileMap {

	private final AgdxGame owner;
	
	public final int width;
	public final int height;
	
	public TileMap(AgdxGame owner, int width, int height) {
		super();
		
		this.owner = owner;
		this.width = width;
		this.height = height;
	}

}
