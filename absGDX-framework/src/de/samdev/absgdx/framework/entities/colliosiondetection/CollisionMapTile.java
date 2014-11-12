package de.samdev.absgdx.framework.entities.colliosiondetection;

import java.util.ArrayList;
import java.util.List;

import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;

/**
 * A single Tile in the CollisionMap
 */
public class CollisionMapTile {
	/**
	 * the geometries in this tile
	 */
	public final List<CollisionGeometry> geometries = new ArrayList<CollisionGeometry>();
}
