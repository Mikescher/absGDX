package de.samdev.absgdx.framework.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.layer.GameLayer;

/**
 * This is an simplifying subclass of Entity - it implements all abstract methods so you don't have to :)
 *
 */
public class SimpleEntity extends Entity {

	/**
	 * Creates a new Entity ( on position (0|0) )
	 * 
	 * @param texture the texture
	 * @param w the boundary box width
	 * @param h the boundary box height
	 */
	public SimpleEntity(Texture texture, float w, float h) {
		super(texture, w, h);
	}

	/**
	 * Creates a new Entity ( on position (0|0) )
	 * 
	 * @param textures the animation
	 * @param w the boundary box width
	 * @param h the boundary box height
	 */
	public SimpleEntity(TextureRegion textures, float w, float h) {
		super(textures, w, h);
	}

	/**
	 * Creates a new Entity ( on position (0|0) )
	 * 
	 * @param textures the animation
	 * @param animationDuration The duration for one *full* cycle (all frames) 
	 * @param w the boundary box width
	 * @param h the boundary box height
	 */
	public SimpleEntity(TextureRegion[] textures, float animationDuration, float w, float h) {
		super(textures, animationDuration, w, h);
	}

	@Override
	public void onActiveCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// NOP
	}

	@Override
	public void onPassiveCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// NOP
	}

	@Override
	public void onActiveMovementCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// NOP
	}

	@Override
	public void onPassiveMovementCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// NOP
	}

	@Override
	public boolean canCollideWith(CollisionGeometryOwner other) {
		return false;
	}

	@Override
	public void beforeUpdate(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLayerAdd(GameLayer layer) {
		// TODO Auto-generated method stub

	}

}
