package de.samdev.absgdx.tests.dummy;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.map.Tile;

public class DummyTile extends Tile {

	public HashMap<String, String> properties = null;
	
	public boolean canHardCollide = false;
	public boolean canCollide = true;
	
	public DummyTile() {
		super((TextureRegion)null);
	}

	public DummyTile(HashMap<String, String> properties) {
		super((TextureRegion)null);
		
		this.properties = properties;
	}

	@Override
	public void update(float delta) {
		// NOP
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return canHardCollide;
	}

	@Override
	public boolean canCollideWith(CollisionGeometryOwner other) {
		return canCollide;
	}

}
