package de.samdev.absgdx.framework.entities.colliosiondetection;

import java.util.ListIterator;


/**
 * A CollisionMap holds all CollisionGeometries and orders them in a grid structure
 *
 */
public class CollisionMap {

	/** the grid width */
	public final int width;
	/** the grid height */
	public final int height;
	
	/** the grid where the geometries are stored*/
	public final CollisionMapTile[][] map;

	/** if an geometry doesn't fit in the grid it is stored here*/
	public final CollisionMapTile[][] outerBorder = new CollisionMapTile[3][3];
	
	/**
	 * Creates a new CollisionMap
	 * 
	 * @param width the grid width
	 * @param height the grid height
	 */
	public CollisionMap(int width, int height) {
		super();
		
		this.width = width;
		this.height = height;
		
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
		return removeGeometry(g, g.center.x, g.center.y);
	}

	private boolean removeGeometry(CollisionGeometry g, float px, float py) {
		int rad = (int) Math.floor(g.getRadius());
		int centerX = (int) px;
		int centerY = (int) py;
		
		boolean success = true;
		
		for (int x = -rad; x <= rad; x++) {
			for (int y = -rad; y <= rad; y++) {
				boolean singlesuccess;
				
				if (centerX+x >= width) {
					if (centerY+y >= height) {
						singlesuccess = outerBorder[2][2].geometries.remove(g);
					} else if (centerY+y < 0) {
						singlesuccess = outerBorder[2][0].geometries.remove(g);
					} else {
						singlesuccess = outerBorder[2][1].geometries.remove(g);
					}
				} else if (centerX+x < 0) {
					if (centerY+y >= height) {
						singlesuccess = outerBorder[0][2].geometries.remove(g);
					} else if (centerY+y < 0) {
						singlesuccess = outerBorder[0][0].geometries.remove(g);
					} else {
						singlesuccess = outerBorder[0][1].geometries.remove(g);
					}
				} else if (centerY+y >= height) {
					if (centerX+x >= width) {
						singlesuccess = outerBorder[2][2].geometries.remove(g);
					} else if (centerX+x < 0) {
						singlesuccess = outerBorder[0][2].geometries.remove(g);
					} else {
						singlesuccess = outerBorder[1][2].geometries.remove(g);
					}
				} else if (centerY+y < 0) {
					if (centerX+x >= width) {
						singlesuccess = outerBorder[2][0].geometries.remove(g);
					} else if (centerX+x < 0) {
						singlesuccess = outerBorder[0][0].geometries.remove(g);
					} else {
						singlesuccess = outerBorder[1][0].geometries.remove(g);
					}
				} else {
					singlesuccess = map[centerX+x][centerY+y].geometries.remove(g);					
				}
				
				success &= singlesuccess;
			}
		}
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
		int rad = (int) Math.ceil(g.getRadius());
		int px = (int) g.center.x;
		int py = (int) g.center.y;
		
		for (int x = -rad; x <= rad; x++) {
			for (int y = -rad; y <= rad; y++) {
				if (px+x >= width) {
					if (py+y >= height) {
						outerBorder[2][2].geometries.add(g);
					} else if (py+y < 0) {
						outerBorder[2][0].geometries.add(g);
					} else {
						outerBorder[2][1].geometries.add(g);
					}
				} else if (px+x < 0) {
					if (py+y >= height) {
						outerBorder[0][2].geometries.add(g);
					} else if (py+y < 0) {
						outerBorder[0][0].geometries.add(g);
					} else {
						outerBorder[0][1].geometries.add(g);
					}
				} else if (py+y >= height) {
					if (px+x >= width) {
						outerBorder[2][2].geometries.add(g);
					} else if (px+x < 0) {
						outerBorder[0][2].geometries.add(g);
					} else {
						outerBorder[1][2].geometries.add(g);
					}
				} else if (py+y < 0) {
					if (px+x >= width) {
						outerBorder[2][0].geometries.add(g);
					} else if (px+x < 0) {
						outerBorder[0][0].geometries.add(g);
					} else {
						outerBorder[1][0].geometries.add(g);
					}
				} else {
					map[px+x][py+y].geometries.add(g);					
				}
			}
		}
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
		if ((int)prevCenterX == (int)geo.getCenterX() && (int)prevCenterY == (int)geo.getCenterY())
			return true; // everything is ok
		
		boolean success = removeGeometry(geo, prevCenterX, prevCenterY);
		addGeometry(geo);
		
		return success;
	}
}
