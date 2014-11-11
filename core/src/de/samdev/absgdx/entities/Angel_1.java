package de.samdev.absgdx.entities;

import de.samdev.absgdx.Textures;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionBox;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionCircle;
import de.samdev.absgdx.framework.layer.GameLayer;

public class Angel_1 extends Entity {
	
	public Angel_1() {
		super(Textures.tex_Angel, 6, 12);
	}
	
	@Override
	public void onLayerAdd(GameLayer layer) {
		setPosition(39.0f, 21.0f);
		
		addCollisionGeo(3.25f, 10f, new CollisionCircle(1.5f)); // head
		
		addCollisionGeo(3.25f, 9f, new CollisionBox(3.0f, 1f)); // shoulders
		
		addCollisionGeo(3.25f, 7f, new CollisionBox(3.0f, 3f)); // torso
		
		addCollisionGeo(1.25f, 7.25f, new CollisionBox(1.0f, 2.5f)); // left wing
		addCollisionGeo(5.25f, 7.25f, new CollisionBox(1.0f, 2.5f)); // right wing
		
		addCollisionGeo(3.15f, 2.75f, new CollisionBox(5.0f, 5.5f)); // podest
	}

	@Override
	public void beforeUpdate(float delta) {
		//
	}

}
