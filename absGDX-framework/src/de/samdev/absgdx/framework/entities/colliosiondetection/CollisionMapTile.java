package de.samdev.absgdx.framework.entities.colliosiondetection;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

/**
 * A single Tile in the CollisionMap
 */
public class CollisionMapTile {
	/**
	 * the geometries in this tile
	 */
	public final List<CollisionGeometry> geometries = new ArrayList<CollisionGeometry>();
}
