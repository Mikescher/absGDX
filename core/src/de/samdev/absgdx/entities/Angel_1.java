package de.samdev.absgdx.entities;

import de.samdev.absgdx.Textures;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionCircle;

public class Angel_1 extends Entity {
	
	public Angel_1() {
		super(Textures.tex_Angel, 6, 12);
		
		setPosition(39.0f, 21.0f);
		
		addCollisionGeo(3f, 9f, new CollisionCircle(3f));
	}

	@Override
	public void beforeUpdate(float delta) {
		//speed.x = 0.001f;
	}

}
