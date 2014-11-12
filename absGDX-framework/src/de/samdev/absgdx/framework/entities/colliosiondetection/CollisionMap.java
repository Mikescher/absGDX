package de.samdev.absgdx.framework.entities.colliosiondetection;

import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.math.ShapeMath;


/**
 * A CollisionMap holds all CollisionGeometries and orders them in a grid structure
 *
 */
public class CollisionMap {

	/** the grid width */
	public final int width;
	/** the grid height */
	public final int height;
	/** the scaling mapTiles -> collisionTiles. This value is exponential (2^n)*/
	public final int expTileScale;
	
	/** the grid where the geometries are stored*/
	public final CollisionMapTile[][] map; //TODO What about different tile sizes ?

	/** if an geometry doesn't fit in the grid it is stored here*/
	public final CollisionMapTile[][] outerBorder = new CollisionMapTile[3][3];
	
	private int geometryCount = 0;
	
	/**
	 * Creates a new CollisionMap with an scale of 1 (expScale = 0)
	 * 
	 * @param mapwidth the grid width
	 * @param mapheight the grid height
	 */
	public CollisionMap(int mapwidth, int mapheight) {
		this(mapwidth, mapheight, 0);
	}
	
	/**
	 * Creates a new CollisionMap
	 * 
	 * The expScale is exponential. This means:
	 * 0 : MapTiles == CollisionTiles
	 * 1 : CollisionTiles are 2 times bigger
	 * 2 : CollisionTiles are 4 times bigger
	 * -1: CollisionTiles are 2 times smaller
	 * etc
	 * 
	 * @param mapwidth the grid width
	 * @param mapheight the grid height
	 * @param expScale the ratio collisionMapTileSize / mapTileSize in the form [2^n]
	 */
	public CollisionMap(int mapwidth, int mapheight, int expScale) {
		super();
		
		this.expTileScale = expScale;
		if (expScale < 0) {
			this.width =  (int) Math.ceil(mapwidth  * 1d * (2 << -(expScale + 1)));
			this.height = (int) Math.ceil(mapheight * 1d * (2 << -(expScale + 1)));			
		} else if (expScale == 0) {
			this.width = mapwidth;
			this.height = mapheight;			
		} else {
			this.width =  (int) Math.ceil(mapwidth  * 1d  / (2 << (expScale - 1)));
			this.height = (int) Math.ceil(mapheight * 1d  / (2 << (expScale - 1)));
		}
		
		this.map = new CollisionMapTile[width][height];
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				this.map[x][y] = new CollisionMapTile();
			}
		}
		
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (x == 1 && y == 1) continue;
				this.outerBorder[x][y] = new CollisionMapTile();
			}
		}
	}
	
	/**
	 * Removes all Geometries that are included in a list
	 * 
	 * @param gList the geometries to remove
	 * @return true if successful
	 */
	public boolean removeGeometries(ListIterator<CollisionGeometry> gList) {
		boolean success = true;
		
		while(gList.hasNext()) {
			boolean singlesuccess = removeGeometry(gList.next());
			
			success &= singlesuccess;
		}
		
		return success;
	}
	
	/**
	 * Removes a geometry from the map
	 * 
	 * @param g the geometry
	 * @return true of successful
	 */
	public boolean removeGeometry(CollisionGeometry g) {
		return removeGeometry(g, g.getCenterX(), g.getCenterY());
	}

	private boolean removeGeometry(CollisionGeometry g, float px, float py) {
		int rad = getTileRadius(g.getRadius());
		int centerX = getTileX(px);
		int centerY = getTileY(py);
		
		boolean success = true;
		
		for (int x = -rad; x <= rad; x++) {
			for (int y = -rad; y <= rad; y++) {
				boolean singlesuccess = getCollisionTile(centerX + x, centerY + y).geometries.remove(g);
				
				success &= singlesuccess;
			}
		}
		
		if (success) geometryCount--;
		
		return success;
	}
	
	/**
	 * Adds all Geometries that are included in a list
	 * 
	 * @param gList the geometries to add
	 */
	public void addGeometries(ListIterator<CollisionGeometry> gList) {
		while(gList.hasNext()) {
			addGeometry(gList.next());
		}
	}
	
	/**
	 * Adds a geometry to the map
	 * 
	 * @param g the geometry
	 */
	public void addGeometry(CollisionGeometry g) {
		int rad = getTileRadius(g.getRadius());
		int px = getTileX(g.getCenterX());
		int py = getTileY(g.getCenterY());
		
		for (int x = -rad; x <= rad; x++) {
			for (int y = -rad; y <= rad; y++) {
				getCollisionTile(px+x, py+y).geometries.add(g);
			}
		}
		
		geometryCount++;
	}
	
	/**
	 * Call this after you have moved a Geometry to update its representation
	 * 
	 * @param prevCenterX the X position before the movement
	 * @param prevCenterY the Y position before the movement
	 * @param geo the geometry (already moved)
	 * @return true if successful
	 */
	public boolean moveGeometry(float prevCenterX, float prevCenterY, CollisionGeometry geo) {
		if (getTileX(prevCenterX) == getTileX(geo.getCenterX()) && getTileY(prevCenterY) == getTileY(geo.getCenterY()))
			return true; // everything is ok
		
		boolean success = removeGeometry(geo, prevCenterX, prevCenterY);
		addGeometry(geo);
		
		return success;
	}

	/**
	 * Returns if any geometry in the list is colliding with an other geometry
	 * 
	 * @param g the geometry-list to test
	 * @return true if there is an collision
	 */
	public boolean isColliding(List<CollisionGeometry> g) {
		return getFirstCollider(g) != null;
	}

	/**
	 * Returns if the geometry is colliding with an other geometry
	 * 
	 * @param g the geometry-list to test
	 * @return true if there is an collision
	 */
	public boolean isColliding(CollisionGeometry g) {
		return getFirstCollider(g) != null;
	}
	
	/**
	 * Returns the first other geometry found that collides with one of the list
	 * 
	 * @param g the geometry-list to test
	 * @return the first colliding geometry or null
	 */
	public CollisionGeometry getFirstCollider(List<CollisionGeometry> g) {
		for (CollisionGeometry geo : g) {
			CollisionGeometry result = getFirstCollider(geo);
			if (result != null)
				return result;
		}
		return null;
	}
	
	/**
	 * Returns the first other geometry found that collides with this one
	 * 
	 * @param g the geometry to test
	 * @return the first colliding geometry or null
	 */
	public CollisionGeometry getFirstCollider(CollisionGeometry g) {
		int rad = getTileRadius(g.getRadius());
		int px = getTileX(g.getCenterX());
		int py = getTileY(g.getCenterY());
		
		for (int x = -rad; x <= rad; x++) {
			for (int y = -rad; y <= rad; y++) {
				for (CollisionGeometry other : getCollisionTile(px+x, py+y).geometries) {
					if (other == g) continue;
					if (other.owner == g.owner) continue;
					
					float dx = g.getCenterX() - other.getCenterX();
					float dy = g.getCenterY() - other.getCenterY();
					
					float dr = g.getRadius() + other.getRadius();
					
					if (dx*dx + dy*dy < dr*dr && canCollide(g, other) && ShapeMath.doGeometriesIntersect(g, other)) { // Shortcut Evaluation - yay
						return other;
					}
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Returns all geometries that collide with the ones in the list
	 * 
	 * @param g the geometry-list to test
	 * @return a Set of all colliding geometries
	 */
	public Set<CollisionGeometry> getColliders(List<CollisionGeometry> g) {
		Set<CollisionGeometry> result = new HashSet<CollisionGeometry>();
		
		for (CollisionGeometry geo : result) {
			result.addAll(getColliders(geo));
		}
		
		return result;
	}
	
	/**
	 * Returns all geometries that collide with this one
	 * 
	 * @param g the geometry to test
	 * @return a Set of all colliding geometries
	 */
	public Set<CollisionGeometry> getColliders(CollisionGeometry g) {
		int rad = getTileRadius(g.getRadius());
		int px = getTileX(g.getCenterX());
		int py = getTileY(g.getCenterY());
		
		Set<CollisionGeometry> result = new HashSet<CollisionGeometry>();
		
		for (int x = -rad; x <= rad; x++) {
			for (int y = -rad; y <= rad; y++) {
				for (CollisionGeometry other : getCollisionTile(px+x, py+y).geometries) {
					if (other == g) continue;
					if (other.owner == g.owner) continue;
					
					float dx = g.getCenterX() - other.getCenterX();
					float dy = g.getCenterY() - other.getCenterY();
					
					float dr = g.getRadius() + other.getRadius();
					
					if (dx*dx + dy*dy < dr*dr && canCollide(g, other) && ShapeMath.doGeometriesIntersect(g, other)) { // Shortcut Evaluation - yay
						result.add(other);
					}
				}
			}
		}
		
		return result;
	}
	
	private int getTileRadius(float radius) {
		if (expTileScale < 0) {
			return (int) Math.ceil(radius * 1d * (2 << -(expTileScale + 1)));	
		} else if (expTileScale == 0) {
			return (int) Math.ceil(radius);
		} else {
			return (int) Math.ceil(radius * 1d  / (2 << (expTileScale - 1)));
		}
	}
	
	private int getTileX(float x) {
		if (expTileScale < 0) {
			return (int) (x * 1d * (2 << -(expTileScale + 1)));		
		} else if (expTileScale == 0) {
			return (int) x;
		} else {
			return (int) (x * 1d  / (2 << (expTileScale - 1)));
		}
	}
	
	private int getTileY(float y) {
		if (expTileScale < 0) {
			return (int) (y * 1d * (2 << -(expTileScale + 1)));		
		} else if (expTileScale == 0) {
			return (int) y;
		} else {
			return (int) (y * 1d  / (2 << (expTileScale - 1)));
		}
	}
	
	private CollisionMapTile getCollisionTile(int x, int y) {
		if (x >= width) {
			if (y >= height) {
				return outerBorder[2][2];
			} else if (y < 0) {
				return outerBorder[2][0];
			} else {
				return outerBorder[2][1];
			}
		} else if (x < 0) {
			if (y >= height) {
				return outerBorder[0][2];
			} else if (y < 0) {
				return outerBorder[0][0];
			} else {
				return outerBorder[0][1];
			}
		} else if (y >= height) {
			if (x >= width) {
				return outerBorder[2][2];
			} else if (x < 0) {
				return outerBorder[0][2];
			} else {
				return outerBorder[1][2];
			}
		} else if (y < 0) {
			if (x >= width) {
				return outerBorder[2][0];
			} else if (x < 0) {
				return outerBorder[0][0];
			} else {
				return outerBorder[1][0];
			}
		} else {
			return map[x][y];					
		}
	}
	
	/**
	 * Get the amount of registered CollisionGeometries
	 * 
	 * @return
	 */
	public int getGeometryCount() {
		return geometryCount;
	}
	
	private boolean canCollide(CollisionGeometry a, CollisionGeometry b) {
		if (a.owner == null || b.owner == null) return true;
		
		return a.owner.canCollideWith(b.owner) || b.owner.canCollideWith(a.owner);
	}

	/**
	 * Gets the current scaling in the representation x:y (e.g. 1:4)
	 * 
	 * @return
	 */
	public String getScaleString() {
		return (expTileScale < 0) ? ((int)Math.pow(2, -expTileScale) + ":1") : ("1:" + (int)Math.pow(2, expTileScale));
	}

	/**
	 * Get the map dimensions (width x height)
	 * 
	 * @return
	 */
	public Vector2 getDimensions() {
		return new Vector2(width, height);
	}
}
