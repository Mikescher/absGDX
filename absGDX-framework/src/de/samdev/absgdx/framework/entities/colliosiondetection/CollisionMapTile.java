package de.samdev.absgdx.framework.entities.colliosiondetection;

import java.util.ArrayList;
import java.util.List;

/**
 * A single Tile in the CollisionMap
 */
public class CollisionMapTile {
	/**
	 * the geometries in this tile
	 */
	public final List<CollisionGeometry> geometries = new ArrayList<CollisionGeometry>();
}
