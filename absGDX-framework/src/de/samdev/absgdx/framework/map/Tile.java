package de.samdev.absgdx.framework.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionListener;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;

/**
 * A single Tile in a Tiled map
 *
 */
public abstract class Tile implements CollisionListener, CollisionGeometryOwner {
	private final TextureRegion texture;
	
	/**
	 * Creates a new Tile
	 * 
	 * @param region the Texture to use for this tile
	 */
	public Tile(TextureRegion region) {
		super();
		
		this.texture = region;
	}

	/**
	 * Creates a new Tile
	 * 
	 * @param tex the Texture to use for this tile
	 */
	public Tile(Texture tex) {
		super();
		
		this.texture = new TextureRegion(tex);
	}

	/**
	 * Gets the current texture - the return value can change every cycle, don't cache this
	 * 
	 * @return the texture
	 */
	public TextureRegion getTexture() {
		return texture;
	}
	
	/**
	 * Update the Tile
	 * 
	 * @param delta the time since the last update (in ms) - can be averaged over he last few cycles
	 */
	public abstract void update(float delta);

	@Override
	public boolean canCollideWith(CollisionGeometryOwner other) {
		return true;
	}

	@Override
	@Deprecated
	public void onActiveCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		throw new UnsupportedOperationException("Tile caused active collision");
	}

	@Override
	public void onPassiveCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// NOP - free to override
	}

	@Override
	@Deprecated
	public void onActiveMovementCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		throw new UnsupportedOperationException("Tile caused active movement collision");
	}

	@Override
	public void onPassiveMovementCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// NOP - free to override
	}
}
